package baidu.model;

import java.util.List;

import org.apache.http.Header;

/**
 * 消息头的抽象类
 * @author WB
 *
 */
public abstract class AbstractHeader {

	public abstract List<Header> getHeaders();
}
