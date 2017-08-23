package com.anyl.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 这个工具类主要是比较两个对象的新旧两个值。
 * 如果两个值不同则会被记录在一个列表中，列表的对象由字段,旧值,新值组成。
 * 获取之后可以方便的使用。
 * @author AnyL8023
 * 
 */
public class DiffObjectUtil {

	public static List<DiffValue> diff(Class clazz,Object oldObj,Object newObj) throws IllegalArgumentException, IllegalAccessException{
		if (null == oldObj || null == newObj){
			return null;
		}
		Map<Field,Object> oldDatas = getObjectValues(clazz, oldObj);
		Map<Field,Object> newDatas = getObjectValues(clazz, newObj);
		if (null == oldDatas || null == newDatas){
			return null;
		}
		Field[] fields = getFields(clazz);
		List<DiffValue> diffs = new ArrayList<DiffValue>();
		for(Field field:fields){
			Object oldValue = oldDatas.get(field);
			Object newValue = newDatas.get(field);
			if (null == oldValue && null == newValue){
				continue;
			}else if(null != oldValue && null != newValue){
				if(!oldValue.equals(newValue)){
					DiffValue diff = new DiffObjectUtil.DiffValue(field, oldValue, newValue);
					diffs.add(diff);
				}
			}else{
				DiffValue diff = new DiffObjectUtil.DiffValue(field, oldValue, newValue);
				diffs.add(diff);
			}
		}
		return diffs;
	}
	
	public static Field[] getFields(Class clazz){
		return clazz.getDeclaredFields();
	}
	
	public static Map<Field,Object> getObjectValues(Class clazz,Object obj) throws IllegalArgumentException, IllegalAccessException{
		if (obj == null)
			return null;
		if(!clazz.isInstance(obj)){
			return null;
		}
		Map<Field,Object> datas = new HashMap<Field,Object>();
		Field[] fields = obj.getClass().getDeclaredFields();
		for (int j = 0; j < fields.length; j++) {
			fields[j].setAccessible(true);
			datas.put(fields[j], fields[j].get(obj));
		}
		return datas;
	}
	
	public static class DiffValue{
		private Field field;
		private Object oldObj;
		private Object newObj;
		public DiffValue(Field field,Object oldObj,Object newObj){
			this.field = field;
			this.oldObj = oldObj;
			this.newObj = newObj;
		}
		public Field getField() {
			return field;
		}
		public void setField(Field field) {
			this.field = field;
		}
		public Object getOldObj() {
			return oldObj;
		}
		public void setOldObj(Object oldObj) {
			this.oldObj = oldObj;
		}
		public Object getNewObj() {
			return newObj;
		}
		public void setNewObj(Object newObj) {
			this.newObj = newObj;
		}
	}
}
