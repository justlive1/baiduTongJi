package baidu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 预登录阶段 请求信息
 * @author WB
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PreLoginTransmitData {
	/**
	 * client操作系统
	 */
	private String osVersion;
	/**
	 * client载体类型
	 */
	private String deviceType;
	/**
	 * client版本
	 */
	private String clientVersion;
}