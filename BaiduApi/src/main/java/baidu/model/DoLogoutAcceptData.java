package baidu.model;

import lombok.Data;
/**
 * 登出响应信息
 * @author WB
 *
 */
@Data
public class DoLogoutAcceptData {

	/**
	 * 0：成功，1：失败 
	 */
	private Integer retcode;
	/**
	 * error 具体信息 
	 */
	private String retmsg;
}