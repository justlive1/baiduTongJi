package baidu.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import baidu.model.AuthTransmitData;
import baidu.model.DataServiceAcceptData;
import baidu.model.DataServiceBodyTransmitData;
import baidu.model.DataServicesBaseTransmitData;
import baidu.model.DataServicesHeader;
import baidu.model.DoLoginAcceptData;
import baidu.model.DoLoginTransmitData;
import baidu.model.DoLogoutAcceptData;
import baidu.model.DoLogoutTransmitData;
import baidu.model.LoginBaseTransmitData;
import baidu.model.LoginHeader;
import baidu.model.PreLoginAcceptData;
import baidu.model.PreLoginTransmitData;
import baidu.model.ProfileQueryTramsmitData;
import baidu.model.ProfileSitesAcceptData;
import baidu.model.ProfileTransInfoAcceptData;
import baidu.model.ReportQueryAcceptData;
import baidu.model.ReportQueryResult;
import baidu.model.ReportQueryTransmitData;
import baidu.model.ReportStatusAcceptData;
import baidu.service.BaiduStatisticsService;
import baidu.utils.BaiduDispatchUtil;

@Service
public class BaiduStatisticsServiceImpl implements BaiduStatisticsService {

	@Value("${baidu.api.token}")
	private String token;
	@Value("${baidu.api.uuid}")
	private String uuid;
	@Value("${baidu.api.siteid}")
	private String siteId;
	@Value("${baidu.api.username}")
	private String username;
	@Value("${baidu.api.password}")
	private String password;
	@Value("${baidu.security.rsa.key}")
	private String rsaKey;
	@Value("${baidu.url.login}")
	private String loginUrl;
	@Value("${baidu.url.dataapi}")
	private String apiUrl;

	public static final String PRELOGIN = "preLogin";
	public static final String DOLOGIN = "doLogin";
	public static final String DOLOGOUT = "doLogout";

	private BaiduDispatchUtil baiduUtil;

	@PostConstruct
	private void init() {

		baiduUtil = BaiduDispatchUtil.getInstance(rsaKey);

	}

	@Override
	public PreLoginAcceptData preLogin() {

		LoginBaseTransmitData<PreLoginTransmitData> pre = new LoginBaseTransmitData<PreLoginTransmitData>(username,
				token, PRELOGIN, uuid);
		pre.setRequest(new PreLoginTransmitData("Windows", "pad", "0.1"));

		LoginHeader header = new LoginHeader(uuid, "1");

		return baiduUtil.security(loginUrl, header, pre, PreLoginAcceptData.class);
	}

	@Override
	public DoLoginAcceptData doLogin(PreLoginAcceptData preLoginData) {

		if (preLoginData.getNeedAuthCode()) {
			throw new RuntimeException("百度统计登陆异常，请联系百度管理员");
		}

		LoginBaseTransmitData<DoLoginTransmitData> data = new LoginBaseTransmitData<DoLoginTransmitData>(username,
				token, DOLOGIN, uuid);
		data.setRequest(new DoLoginTransmitData(password));

		LoginHeader header = new LoginHeader(uuid, "1");

		return baiduUtil.security(loginUrl, header, data, DoLoginAcceptData.class);
	}

	@Override
	public DoLogoutAcceptData logout(DoLoginAcceptData loginData) {

		LoginBaseTransmitData<DoLogoutTransmitData> data = new LoginBaseTransmitData<DoLogoutTransmitData>(username,
				token, DOLOGOUT, uuid);
		data.setRequest(new DoLogoutTransmitData(loginData.getUcid(), loginData.getSt()));

		LoginHeader header = new LoginHeader(uuid, "1");

		return baiduUtil.security(loginUrl, header, data, DoLogoutAcceptData.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public DataServiceAcceptData<ProfileSitesAcceptData> getSites(String uuid, String userId) {

		DataServicesBaseTransmitData<Object> data = new DataServicesBaseTransmitData<Object>();

		AuthTransmitData dataHeader = new AuthTransmitData(username, uuid, token, "1");
		data.setHeader(dataHeader);

		DataServiceBodyTransmitData<Object> body = new DataServiceBodyTransmitData<>(
				DataServiceBodyTransmitData.SEVICE_NAME_PROFILE, DataServiceBodyTransmitData.METHOD_PROFILE_GETSITES);
		data.setBody(body);

		DataServicesHeader header = new DataServicesHeader(this.uuid, userId);

		return baiduUtil.communicate(apiUrl, header, data, DataServiceAcceptData.class, ProfileSitesAcceptData.class);

	}

	@SuppressWarnings("unchecked")
	@Override
	public DataServiceAcceptData<ProfileTransInfoAcceptData> getTransInfo(String uuid, String userId,
			ProfileQueryTramsmitData query) {

		DataServicesBaseTransmitData<ProfileQueryTramsmitData> data = new DataServicesBaseTransmitData<ProfileQueryTramsmitData>();

		AuthTransmitData dataHeader = new AuthTransmitData(username, uuid, token, "1");
		data.setHeader(dataHeader);

		DataServiceBodyTransmitData<ProfileQueryTramsmitData> body = new DataServiceBodyTransmitData<ProfileQueryTramsmitData>(
				DataServiceBodyTransmitData.SEVICE_NAME_PROFILE,
				DataServiceBodyTransmitData.METHOD_PROFILE_GETTRANSINFO, query);
		data.setBody(body);

		DataServicesHeader header = new DataServicesHeader(this.uuid, userId);

		return baiduUtil.communicate(apiUrl, header, data, DataServiceAcceptData.class,
				ProfileTransInfoAcceptData.class);

	}

	@SuppressWarnings("unchecked")
	@Override
	public DataServiceAcceptData<ReportQueryAcceptData> getReportQuery(String uuid, String userId,
			ReportQueryTransmitData query) {
		DataServicesBaseTransmitData<ReportQueryTransmitData> data = new DataServicesBaseTransmitData<ReportQueryTransmitData>();

		AuthTransmitData dataHeader = new AuthTransmitData(username, uuid, token, "1");
		data.setHeader(dataHeader);

		DataServiceBodyTransmitData<ReportQueryTransmitData> body = new DataServiceBodyTransmitData<ReportQueryTransmitData>(
				DataServiceBodyTransmitData.SEVICE_NAME_REPORT, DataServiceBodyTransmitData.METHOD_REPORT_QUERY, query);
		data.setBody(body);

		DataServicesHeader header = new DataServicesHeader(this.uuid, userId);

		return baiduUtil.communicate(apiUrl, header, data, DataServiceAcceptData.class, ReportQueryAcceptData.class);

	}

	@SuppressWarnings("unchecked")
	@Override
	public DataServiceAcceptData<ReportStatusAcceptData> getReportStatus(String uuid, String userId, String resultId) {

		DataServicesBaseTransmitData<ReportQueryResult> data = new DataServicesBaseTransmitData<ReportQueryResult>();

		AuthTransmitData dataHeader = new AuthTransmitData(username, uuid, token, "1");
		data.setHeader(dataHeader);

		DataServiceBodyTransmitData<ReportQueryResult> body = new DataServiceBodyTransmitData<ReportQueryResult>(
				DataServiceBodyTransmitData.SEVICE_NAME_REPORT, DataServiceBodyTransmitData.METHOD_REPORT_GETSTATUS,
				new ReportQueryResult(resultId));
		data.setBody(body);

		DataServicesHeader header = new DataServicesHeader(this.uuid, userId);
		header.setTracker("_sys");

		return baiduUtil.communicate(apiUrl, header, data, DataServiceAcceptData.class, ReportStatusAcceptData.class);

	}

}
