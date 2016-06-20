package baidu.model;

import lombok.Data;

/**
 * 查询相关参数
 * 
 * @author WB
 *
 */
@Data
public class ProfileQueryTramsmitData {

	/**
	 * 转化名<br>
	 * name 和 url 必填其一
	 */
	private String name;
	/**
	 * 转化页面<br>
	 * name 和 url 必填其一<br>
	 * 取值范围： 字符和 SQL 通配符<br>
	 * % 替代一个或多个字符<br>
	 * _ 仅替代一个字符<br>
	 * [charlist] 字符列中的任何单一字符<br>
	 * [^charlist]或者[!charlist] 不在字符列中的任何单一字符
	 */
	private String url;
}
