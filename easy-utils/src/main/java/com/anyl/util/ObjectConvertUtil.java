package com.anyl.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class ObjectConvertUtil {

	/**
	 * 将对象转化为Map数据
	 * @author AnyL8023
	 * @param obj
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static Map<String, Object> convertMap(Object obj) throws IllegalArgumentException, IllegalAccessException {
		Map<String, Object> map = new HashMap<String, Object>();
		Field[] fields = obj.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			map.put(field.getName(), field.get(obj));
		}
		return map;
	}
	
	/**
	 * 将Map里面的数据状为一个对象
	 * @author AnyL8023
	 * @param map
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public static Object convertObject(Map<String, Object> map, Class<?> clazz) throws Exception {
		Object obj = clazz.newInstance();
		Field[] declaredFields = obj.getClass().getDeclaredFields();
		for (Field field : declaredFields) {
			int mod = field.getModifiers();
			if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
				continue;
			}
			field.setAccessible(true);
			field.set(obj, map.get(field.getName()));
		}
		return obj;
	}
}
