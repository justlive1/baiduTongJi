package baidu.model;

import lombok.Data;

@Data
public class Config {
	/**
	 * 黑白名单（0：白名单，1：黑名单； type 为站点时，flag 为 0）
	 */
	private Integer flag;
	/**
	 * 示站点域名或者子目录配置的 url 规则
	 */
	private String pattern;
}
