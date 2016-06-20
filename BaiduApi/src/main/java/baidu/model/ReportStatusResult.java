package baidu.model;

import lombok.Data;
/**
 * Report 服务 getstatus 响应数据
 */
@Data
public class ReportStatusResult {

	private String result_id;
	/**
	 * status为生成状态（0：无效，1：报告结果生成中，2：生成失败，3已生成、可下载）
	 */
	private Integer status;
	
	private String message;
	/**
	 * 只有当status为3的时候result_url才非空<br>
	 * 下载报告结果的URL地址
	 */
	private String result_url;
}
