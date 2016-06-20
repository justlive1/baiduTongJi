package baidu.model;

import lombok.Data;

/**
 * report 服务的报告查询条件
 * 
 * @author WB
 *
 */
@Data
public class ReportQueryTransmitData {

	/**
	 * reportid int 查询报告的 id 必填<br>
	 * 取值范围： 1：受访页面报告
	 */
	private Integer reportid;
	/**
	 * siteid int 站点的 siteid 必填 <br>
	 * 取值范围： 用户名下的 siteid，可通过profileService 的 getsites 方法查询
	 */
	private Integer siteid;
	/**
	 * start_time string 开始时间 必填 <br>
	 * 格式为 YYYYmmddHHiiss，<br>
	 * 例如： 20130218000000 不大于当前时间
	 */
	private String start_time;
	/**
	 * end_time string 结束时间 必填 <br>
	 * 格式为 YYYYmmddHHiiss，<br>
	 * 例如： 20130218000000 不大于当前时间 不小于 start_time <br>
	 * end_time 与 start_time 间隔不超过一年
	 */
	private String end_time;
	/**
	 * dimensions 数组 groupby 的维度 选填，<br>
	 * 默认 pageid 取值范围： pageid
	 */
	private String[] dimensions;
	/**
	 * metrics 数组 查询的指标 必填<br>
	 * 取值范围： pageviews 浏览量（PV） <br>
	 * visitors 访客数（UV）<br>
	 * ips IP 数 <br>
	 * entrances 入口页次数 <br>
	 * outwards 贡献下游流量 <br>
	 * exits 退出页次数 <br>
	 * stayTime 平均停留时长 <br>
	 * exitRate 退出率
	 */
	private String[] metrics;
	/**
	 * filters 数组 过滤条件 选填 <br>
	 * fromType=1 直达<br>
	 * 2 搜索引擎 <br>
	 * 3 外部链接 <br>
	 * newVisitor=1 新访客 <br>
	 * 2 老访客
	 */
	private String[] filters;
	/**
	 * sort 数组 排序条件 选填 <br>
	 * 排序的指标必须在查询的指标中 排序方式可以是 asc 正序（默认值） desc 逆序
	 */
	private String[] sort;
	/**
	 * start_index int 开始游标 必填，<br>
	 * ≥0
	 */
	private Integer start_index;

	/**
	 * max_result int 最大返回记录数 必填<br>
	 * 取值范围[0, 10000]
	 */
	private Integer max_result;
}
