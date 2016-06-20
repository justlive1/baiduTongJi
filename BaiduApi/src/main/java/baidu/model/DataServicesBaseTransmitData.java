package baidu.model;

import lombok.Data;

/**
 * 百度统计数据服务请求信息
 * 
 * @author WB
 *
 */
@Data
public class DataServicesBaseTransmitData<T> {

	/**
	 * 认证信息 必填
	 */
	private AuthTransmitData header;
	/**
	 * api 请求 必填
	 */
	private DataServiceBodyTransmitData<T> body;
}
