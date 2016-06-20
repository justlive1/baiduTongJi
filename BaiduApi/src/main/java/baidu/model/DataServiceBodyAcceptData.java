package baidu.model;

import baidu.annotation.JsonFormat;
import lombok.Data;

/**
 *  百度统计服务响应信息 body
 * @author WB
 *
 * @param <T>
 */
@Data
@JsonFormat
public class DataServiceBodyAcceptData <T>{
	
	@JsonFormat(true)
	private T responseData;
	
	
	@Override
	public String toString(){
		return responseData.toString();
	}
}
