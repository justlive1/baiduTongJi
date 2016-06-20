package baidu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * 登录阶段 请求信息
 * @author WB
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class DoLoginTransmitData {

	/**
	 * 用户密码
	 */
	@NonNull
	private String password;
	/**
	 * 验证码
	 */
	private String imageCode;
	/**
	 * 验证码会话id
	 */
	private String imageSsid;
}
