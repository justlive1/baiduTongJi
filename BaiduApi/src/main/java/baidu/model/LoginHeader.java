package baidu.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 登录操作消息头<br>
 * 添加到 HTTP(S) Header
 * @author WB
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class LoginHeader extends AbstractHeader {
	/**
	 * 唯一标识
	 */
	private String uuid;
	/**
	 * 账户类型
	 */
	private String accountType;

	@Override
	public List<Header> getHeaders() {
		List<Header> headers = new ArrayList<>();
		headers.add(new BasicHeader("UUID", uuid));
		headers.add(new BasicHeader("account_type", accountType));
		return headers;
	}

}
