package baidu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 登出 请求信息
 * @author WB
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoLogoutTransmitData {

	/**
	 * 用户 id 
	 */
	private Long ucid;
	/**
	 * 会话 id 
	 */
	private String st;
	
}
