package baidu.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * webService接口中，部分数据返回结果中，某些属性值存储的是json字符串<br>
 * 通常在数据结构中，这部分属性将会采用对象的形式<br>
 * 该注解用于标识这些属性需要转换成指定的类<br>
 * 使用：对于包含此类属性的类，class和field上需要加上该注解<br>
 * 当数据结构为复杂类型，多层类嵌套时，仅当最底层 属性为json串时 value = true<br>
 * 仅支持单一泛型
 * @author WB
 *
 */
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JsonFormat {

	public boolean value() default false;
}
