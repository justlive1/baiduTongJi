package baidu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Profile服务的请求信息
 * 
 * @author WB
 *
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class DataServiceBodyTransmitData <T>{

	public static final String SEVICE_NAME_PROFILE="profile";
	public static final String SEVICE_NAME_REPORT="report";
	public static final String METHOD_PROFILE_GETSITES="getsites";
	public static final String METHOD_PROFILE_GETTRANSINFO="get_trans_info";
	public static final String METHOD_REPORT_QUERY="query";
	public static final String METHOD_REPORT_GETSTATUS="getstatus";
	/**
	 * 服务名 必填 <br>
	 * profile
	 */
	@NonNull
	private String serviceName;
	/**
	 * 方法名 必填<br>
	 * getsites<br>
	 * get_trans_info
	 */
	@NonNull
	private String methodName;
	/**
	 * 查询相关参<br>
	 * 仅当方法名为 getsites 不填写
	 */
	private T parameterJSON;
}
