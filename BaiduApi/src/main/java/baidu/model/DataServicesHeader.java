package baidu.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * 数据服务的消息头
 * @author WB
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@RequiredArgsConstructor
public class DataServicesHeader extends AbstractHeader {
	/**
	 * 必填，唯一标识符，与登录时 一致
	 */
	@NonNull
	private String uuid;
	/**
	 * 必填，成功登录后获取的用户 id（ucid）
	 */
	@NonNull
	private String userId;
	/**
	 * 选填，请求定位符，可用于定 位请求
	 */
	private String tracker;

	@Override
	public List<Header> getHeaders() {
		List<Header> headers = new ArrayList<>();
		headers.add(new BasicHeader("UUID", uuid));
		headers.add(new BasicHeader("USERID", userId));
		if (tracker != null)
			headers.add(new BasicHeader("tracker", tracker));
		return headers;
	}

}
