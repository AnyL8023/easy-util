package com.anyl.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	
	/**
	 * 将List按照指定的key放入到Map集合中
	 * @author AnyL8023
	 * @param list
	 * @param fieldKey
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static <T> Map<Object,T> convertListToMap(List<T> list,String fieldKey) throws IllegalArgumentException, IllegalAccessException{
		if(null == list || null == fieldKey){
			return null;
		}
		Map<Object,T> map = new HashMap<Object, T>();
		for(T obj:list){
			Field[] fields = obj.getClass().getDeclaredFields();
			for(Field field:fields){
				if(field.getName().equals(fieldKey)){
					field.setAccessible(true);
					map.put(field.get(obj), obj);
				}
			}
		}
		return map;
	}
	
	/**
	 * 将Map转为List
	 * @author AnyL8023
	 * @param map
	 * @return
	 */
	public static <T> List<T> convertMapToList(Map<Object,T> map){
		if(null == map){
			return null;
		}
		
		List<T> list = new ArrayList<T>();
		for(T t:map.values()){
			list.add(t);
		}
		return list;
	}

	
	/**
	 * 从List中根据field字段抽取数据
	 * @author AnyL8023
	 * @param map
	 * @return
	 */
	public static Set extractValue(List list,String fieldKey) throws IllegalArgumentException, IllegalAccessException{
		if(null == list || null == fieldKey){
			return null;
		}
		Set set = new HashSet();
		for(Object obj:list){
			Field[] fields = obj.getClass().getDeclaredFields();
			for(Field field:fields){
				if(field.getName().equals(fieldKey)){
					field.setAccessible(true);
					set.add(field.get(obj));
				}
			}
		}
		return set;
	}
}
