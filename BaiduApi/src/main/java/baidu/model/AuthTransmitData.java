package baidu.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * 认证信息
 * 
 * @author WB
 *
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class AuthTransmitData {
	/**
	 * 用户名 必填
	 */
	@NonNull
	private String username;
	/**
	 * 成功登录后获取的会 话 id(st) 必填
	 */
	@NonNull
	private String password;
	/**
	 * api 权限码 必填
	 */
	@NonNull
	private String token;
	/**
	 * 被操作用户名 选填（一期无效）
	 */
	private String target;
	/**
	 * oauth access token 选填（一期无效）
	 */
	private String accessToken;
	/**
	 * 账户类型 必填 <br>
	 * 1：站长账号 <br>
	 * 2：凤巢账号 <br>
	 * 3：联盟账号<br>
	 * 4：哥伦布账号
	 */
	@NonNull
	private String account_type;
}
