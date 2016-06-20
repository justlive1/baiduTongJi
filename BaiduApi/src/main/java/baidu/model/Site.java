package baidu.model;

import lombok.Data;

@Data
public class Site {
	/**
	 * 站点id
	 */
	private Integer siteid;
	/**
	 * 是否是子目录
	 */
	private Integer type;
	/**
	 * 规则
	 */
	private Config[] conf;
}
