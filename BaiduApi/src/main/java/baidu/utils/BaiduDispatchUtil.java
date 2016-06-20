package baidu.utils;

import java.io.IOException;
import java.io.InputStream;
import java.security.Key;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.lang.ArrayUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import baidu.model.AbstractHeader;

public class BaiduDispatchUtil {

	private static final Logger logger = LoggerFactory.getLogger(BaiduDispatchUtil.class);

	private static final int MAX_MSG_SIZE = 4096;

	private String rsaKey;

	private CloseableHttpClient httpclient;

	public static BaiduDispatchUtil getInstance(String rsaKey) {

		BaiduDispatchUtil util = new BaiduDispatchUtil();
		util.rsaKey = rsaKey;

		SSLContext sslcontext;
		try {
			sslcontext = SSLContext.getInstance("TLS");

			X509TrustManager tm = new X509TrustManager() {
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}

				public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
				}

				public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
				}
			};
			sslcontext.init(null, new TrustManager[] { tm }, null); // SSL协议

			// Allow TLSv1 protocol only
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" },
					null, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			// build httpclient
			logger.info("create httpclient ...");
			Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory> create()
					.register("http", PlainConnectionSocketFactory.getSocketFactory()).register("https", sslsf).build();
			PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(registry);
			connManager.setDefaultMaxPerRoute(50);
			connManager.setMaxTotal(100);
			util.httpclient = HttpClients.custom().setConnectionManager(connManager).build();

		} catch (Exception e) {
			logger.error("", e);
		}

		return util;

	}

	public <T> T security(String url, AbstractHeader header, Object data, Class<T> clazz) {
		try {
			// 消息头
			List<Header> headers = header.getHeaders();
			// 组装消息体
			String json = JacksonUtil.toJSONString(data);
			
			logger.info("json,{}",json);
			
			byte[] zip = this.makeRsaAndGzip(json);

			HttpEntity in = this.httpsPostBinary(url, headers, zip);

			return readResponse(in, clazz);

		} catch (ClientInternalException e) {

			throw e;

		} catch (Exception e) {
			logger.error("百度api连接出错", e);
		}

		return null;
	}

	public <T> T communicate(String url, AbstractHeader header, Object data, Class<T> clazz, Class<?> generic) {
		try {
			// 消息头
			List<Header> headers = header.getHeaders();
			// 组装消息体
			String json = JacksonUtil.toJSONString(data);
			
			byte[] zip = json.getBytes("utf-8");

			HttpEntity in = this.httpsPostBinary(url, headers, zip);

			return readResponse(in, clazz, generic);

		} catch (ClientInternalException e) {

			throw e;

		} catch (Exception e) {
			logger.error("百度api连接出错", e);
		}

		return null;
	}

	/**
	 * 压缩
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	private byte[] makeRsaAndGzip(String json) throws Exception {
		Key publicKey = RSAUtil.getPublicKey(rsaKey);
		byte[] zip = GZipUtil.gzipString(json);
		zip = RSAUtil.encryptByPublicKey(zip, publicKey);
		return zip;
	}

	/**
	 * http(s)通信
	 * 
	 * @param uri
	 * @param headers
	 * @param buf
	 * @return
	 */
	private HttpEntity httpsPostBinary(String uri, List<Header> headers, byte[] buf) {
		if ("".equals(uri) || uri == null) {
			logger.error("https post url is null");
			return null;
		}

		logger.debug("url is " + uri);
		HttpPost httpPost = new HttpPost(uri); // 请求地址
		// http head
		if (headers != null && !headers.isEmpty()) {
			for (Header header : headers) {
				httpPost.addHeader(header);
			}
		}
		CloseableHttpResponse response = null;
		try {
			if (buf.length > 0) {
				ByteArrayEntity bae = new ByteArrayEntity(buf, ContentType.create("binary", "UTF-8"));
				httpPost.setEntity(bae);
			}
			logger.info("HttpResponse is opening ... ");
			response = httpclient.execute(httpPost);

			if (response != null) {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					return entity;
				}
			}
		} catch (Exception e) {
			logger.error("", e);
		} finally {
			if (response != null) {
				// 关闭CloseableHttpResponse
				try {
					response.close();
					logger.info("HttpResponse closed ");
				} catch (IOException e) {
					logger.error("", e);
				}
			}
		}
		return null;
	}

	/**
	 * 解码通讯返回
	 * 
	 * @param in
	 * @param t
	 * @return
	 */
	private <T> T readResponse(HttpEntity entity, Class<T> t) {
		InputStream in = null;
		try {
			in = entity.getContent();
			byte[] b = new byte[8];
			if (in.read(b) != 8) {
				throw new ClientInternalException("Server response is invalid.");
			}
			if (b[1] != 0) {
				throw new ClientInternalException("Server returned an error code: " + b[1]);
			}
			int total = 0, k = 0;
			b = new byte[MAX_MSG_SIZE];
			while (total < MAX_MSG_SIZE) {
				k = in.read(b, total, MAX_MSG_SIZE - total);
				if (k < 0)
					break;
				total += k;
			}
			if (total == MAX_MSG_SIZE) {
				throw new ClientInternalException("Server returned message too large.");
			}
			byte[] zip = ArrayUtils.subarray(b, 0, total);
			zip = GZipUtil.unGzip(zip);
			return JacksonUtil.readObj(new String(zip, "UTF-8"), t);
		} catch (IOException e) {
			throw new ClientInternalException(e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					throw new ClientInternalException(e);
				}
			}
		}

	}

	private <T> T readResponse(HttpEntity entity, Class<T> t, Class<?> generic) {
		try {
			String json = EntityUtils.toString(entity);
			logger.info(json);
			return JacksonUtil.readObj(json, t, generic);
		} catch (Exception e) {
			throw new ClientInternalException(e);
		}
	}

}
