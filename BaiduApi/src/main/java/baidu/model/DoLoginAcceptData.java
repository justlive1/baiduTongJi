package baidu.model;

import java.util.List;

import lombok.Data;

/**
 * 登录响应信息
 * @author WB
 *
 */
@Data
public class DoLoginAcceptData {

	/**
	 * 登录返回码<br>
	 * 0:成功<br>
	 * 134:强制修改密码<br>
	 * 135:用户被锁定<br>
	 * 3：登录 IP 被封禁<br>
	 * 133：用户不存在<br>
	 * 132：用户名或密码错误<br>
	 * 191：x需要回答密保问题<br>
	 * 其他 code 请联系管理员
	 */
	private Integer retcode;
	/**
	 * 错误信息
	 */
	private String retmsg;
	/**
	 * 用户id<br>
	 * 用户需记录该信息， 后续请求统计API需 要
	 */
	private Long ucid;
	/**
	 * 会话id<br>
	 * 用户需记录该信息， 后续请求统计API需 要
	 */
	private String st;
	/**
	 * 是否是 token 登陆用户
	 */
	private Integer istoken;
	/**
	 * 是否需要设置 Pin 码
	 */
	private Integer setpin;
	/**
	 * 安全问题列表
	 */
	private List<Question> questions;

	@Data
	public class Question {
		/**
		 * 安全问题 id
		 */
		private Integer qid;
		/**
		 * 安全问题字面
		 */
		private String content;
	}
}
