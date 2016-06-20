package baidu.model;

import baidu.annotation.JsonFormat;
import lombok.Data;

/**
 * 百度统计服务响应信息
 * 
 * @author WB
 *
 */
@Data
@JsonFormat
public class DataServiceAcceptData<T> {
	/**
	 * 响应头
	 */
	private ResHeader header;
	/**
	 * api 响应数据
	 */
	@JsonFormat
	private DataServiceBodyAcceptData<T> body;


}
