package baidu.model;

import lombok.Data;

@Data
public class Failure {
	/**
	 * error code
	 */
	private Integer code;
	/**
	 * 错误信息
	 */
	private String message;
	/**
	 * 错误参数提示
	 */
	private String position;
	/**
	 * 内容
	 */
	private String content;
}
