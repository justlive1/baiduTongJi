package baidu.model;

import lombok.Data;

@Data
public class TransInfo {
	
	private String name;
	/**
	 * 为转化记录方式（0 代表：按 PV，1 代表：按访问次数，2 代表： 按参数，如：订单转化）
	 */
	private Integer mode;
	/**
	 * 预期收入
	 */
	private Integer expt_revenue;
	/**
	 * 预期转化率
	 */
	private Integer expt_trans_rate;
	/**
	 * 是否是必须经过路径
	 */
	private Integer is_path_necessary;
	/**
	 * 转化的状态（0：正常，1：暂停，2： 删除）
	 */
	private Integer status;
}
