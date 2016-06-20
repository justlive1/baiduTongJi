package baidu.model;

import lombok.Data;

/**
 * 账号下管理的站点、子目录信息
 * 
 * @author WB
 *
 */
@Data
public class ProfileSitesAcceptData {

	/**
	 * 站点
	 */
	private Site[] sites;

}
