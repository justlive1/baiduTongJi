package baidu.model;

import lombok.Data;

/**
 * 预登录响应信息
 * @author WB
 *
 */
@Data
public class PreLoginAcceptData {

	/**
	 * 是否需要校验码
	 */
	private Boolean needAuthCode;
	/**
	 * 校验码内容
	 */
	private AuthCode authCode;
	
	
}
