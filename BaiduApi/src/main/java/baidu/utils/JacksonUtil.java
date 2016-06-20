package baidu.utils;

import java.lang.reflect.Field;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import baidu.annotation.JsonFormat;

public class JacksonUtil {
	
	private static ObjectMapper mapper;

	private static Logger logger = LoggerFactory.getLogger(JacksonUtil.class);

	static {
		mapper = new ObjectMapper();
		mapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	public static <T> T readObj(String json, Class<T> clazz) {

		try {

			T obj = (T) mapper.readValue(json, clazz);

			return obj;

		} catch (Exception e) {
			logger.error("json转换异常", e);
		}
		return null;
	}

	public static <T> T readObj(String json, Class<T> clazz, Class<?> generic) {

		try {
			// 判断当前类是否存在需要转换的属性
			JsonFormat jsonFormat = clazz.getAnnotation(JsonFormat.class);

			T obj = (T) mapper.readValue(json, clazz);
			if (jsonFormat == null)
				return obj;

			convert(json, clazz, obj, generic);

			return obj;

		} catch (Exception e) {
			logger.error("json转换异常", e);
		}
		return null;
	}

	public static String toJSONString(Object object) {
		try {
			return mapper.writeValueAsString(object);
		} catch (Exception e) {
			logger.error("json转换异常", e);
		}
		return "{}";
	}

	private static <T> void convert(String json, Class<?> clazz, T obj, Class<?> generic) {

		// 获取需要转换的属性
		Field[] fields = FieldUtils.getAllFields(clazz);

		for (Field field : fields) {

			JsonFormat formatField = field.getAnnotation(JsonFormat.class);

			if (formatField == null) {
				continue;
			}

			try {

				field.setAccessible(true);

				if(field.get(obj)==null)continue;
				
				String fieldJson = field.get(obj).toString();

				logger.debug("fieldJson:{}", fieldJson);

				if (formatField.value()) {

					Object fieldObj = readObj(fieldJson, generic);

					field.set(obj, fieldObj);

				} else {

					convert(fieldJson, field.getType(), field.get(obj) ,generic);

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
