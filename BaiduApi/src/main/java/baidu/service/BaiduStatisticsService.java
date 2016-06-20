package baidu.service;

import baidu.model.DataServiceAcceptData;
import baidu.model.DoLoginAcceptData;
import baidu.model.DoLogoutAcceptData;
import baidu.model.PreLoginAcceptData;
import baidu.model.ProfileQueryTramsmitData;
import baidu.model.ProfileSitesAcceptData;
import baidu.model.ProfileTransInfoAcceptData;
import baidu.model.ReportQueryAcceptData;
import baidu.model.ReportQueryTransmitData;
import baidu.model.ReportStatusAcceptData;

/**
 * 百度统计服务
 * 
 * @author WB
 *
 */
public interface BaiduStatisticsService {

	/**
	 * 预登录接口<br>
	 * 用户登录前获知是否需要校验码和校验码信息
	 * 
	 * @return
	 */
	public PreLoginAcceptData preLogin();

	/**
	 * 登录
	 * 
	 * @param preLoginData
	 *            预登录响应信息
	 * @return
	 */
	public DoLoginAcceptData doLogin(PreLoginAcceptData preLoginData);

	/**
	 * 登出
	 * 
	 * @param loginData
	 *            登录响应信息
	 * @return
	 */
	public DoLogoutAcceptData logout(DoLoginAcceptData loginData);

	/**
	 * 
	 * 获取账号下管理的站点、子目录信息。
	 * 
	 * @param uuid
	 *            唯一标识符，与登录时 一致
	 * @param userId
	 *            成功登录后获取的用户 ucid
	 * @return
	 */
	public DataServiceAcceptData<ProfileSitesAcceptData> getSites(String uuid, String userId);

	/**
	 * 
	 * 获取账号下管理的转化信息。
	 * 
	 * @param uuid
	 *            唯一标识符，与登录时 一致
	 * @param userId
	 *            成功登录后获取的用户 ucid
	 * @param query
	 *            查询参数
	 * @return
	 */
	@Deprecated
	public DataServiceAcceptData<ProfileTransInfoAcceptData> getTransInfo(String uuid, String userId,
			ProfileQueryTramsmitData query);

	/**
	 * 
	 * 根据请求时提供的报告查询条件，<br>
	 * 返回一个 result_id，<br>
	 * 可以根据此 result_id <br>
	 * 使用 getstatus 方法查询报告生成状态
	 * 
	 * @param uuid
	 *            唯一标识符，与登录时 一致
	 * @param userId
	 *            成功登录后获取的用户 ucid
	 * @param query
	 *            查询参数
	 * @return
	 */
	public DataServiceAcceptData<ReportQueryAcceptData> getReportQuery(String uuid, String userId,
			ReportQueryTransmitData query);

	/**
	 * 
	 * 使用query得到的result_id查询报告生成状态，<br>
	 * 当状态为已生成、可下载时，同时获得报告生成结果的下载地址。
	 * 
	 * @param uuid
	 *            唯一标识符，与登录时 一致
	 * @param userId
	 *            成功登录后获取的用户 ucid
	 * @param resultId
	 *            查询参数
	 * @return
	 */
	public DataServiceAcceptData<ReportStatusAcceptData> getReportStatus(String uuid, String userId, String resultId);

}
