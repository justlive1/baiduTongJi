package baidu.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * 登录操作必填请求
 * @author WB
 *
 * @param <T>
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class LoginBaseTransmitData<T>{
	/**
	 * 用户名
	 */
	@NonNull
	private String username;
	/**
	 * Drapi权限码
	 */
	@NonNull
	private String token;
	/**
	 * 登陆阶段
	 */
	@NonNull
	private String functionName;
	@NonNull
	private String uuid;
	/**
	 * 请求内容
	 */
	private T request;
	
}
