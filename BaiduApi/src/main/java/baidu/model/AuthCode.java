package baidu.model;

import lombok.Data;

@Data
public class AuthCode {
	/**
	 * 图片格式
	 */
	private String imgtype;
	/**
	 * 图片二进制内容
	 */
	private String imgdata;
	/**
	 * 图片会话id
	 */
	private String imgssid;
}
