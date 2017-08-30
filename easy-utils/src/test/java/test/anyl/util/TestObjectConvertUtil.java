package test.anyl.util;

import static org.junit.Assert.assertEquals;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.anyl.util.ObjectConvertUtil;

import test.anyl.entity.Student;
import test.anyl.entity.Student2;
import test.anyl.entity.Student3;

public class TestObjectConvertUtil {

	private Student old_s = new Student();
	{
		old_s.setAge(20);
		old_s.setBirthday(new Date(2017,8,23));
		old_s.setIsFale(true);
		old_s.setMoney(100000l);
		old_s.setName("张三");
		old_s.setTall(180.5);
	}
	
	@Test
	public void convertMap() throws IllegalArgumentException, IllegalAccessException{
		Map<String,Object> datas = ObjectConvertUtil.convertMap(old_s);
		assertEquals(20, datas.get("age"));
		assertEquals(new Date(2017,8,23), datas.get("birthday"));
		assertEquals(true, datas.get("isFale"));
		assertEquals(100000l, datas.get("money"));
		assertEquals(180.5, datas.get("tall"));
	}
	@Test
	public void convertObject() throws Exception{
		Map<String,Object> datas = ObjectConvertUtil.convertMap(old_s);
		Student student = (Student) ObjectConvertUtil.convertObject(datas, Student.class);
		assertEquals(old_s.getAge(), student.getAge());
		assertEquals(old_s.getBirthday(), student.getBirthday());
		assertEquals(old_s.getIsFale(), student.getIsFale());
		assertEquals(old_s.getMoney(), student.getMoney());
		assertEquals(old_s.getName(), student.getName());
		assertEquals(old_s.getTall(), student.getTall());
	}
	
	@Test
	public void convertObject2() throws Exception{
		Map<String,Object> datas = ObjectConvertUtil.convertMap(old_s);
		Student3 student = (Student3) ObjectConvertUtil.convertObject(datas, Student3.class);
		assertEquals(null, student.getAge1());
		assertEquals(null, student.getBirthday());
		assertEquals(old_s.getIsFale(), student.getIsFale());
		assertEquals(null, student.getMoney1());
		assertEquals(null, student.getName1());
		assertEquals(old_s.getTall(), student.getTall());
	}
	
	
	@Test
	public void convertObjectMap() throws Exception{
		Map<String,Object> datas = ObjectConvertUtil.convertMap(old_s);
		Map<String,String> maps = new HashMap<String,String>();
		maps.put("name1", "name");
		maps.put("age1", "age2");
		Student3 s = (Student3) ObjectConvertUtil.convertObject(datas, Student3.class, maps);
		assertEquals(null, s.getAge1());
		assertEquals(null, s.getBirthday());
		assertEquals(old_s.getIsFale(), s.getIsFale());
		assertEquals(null, s.getMoney1());
		assertEquals(old_s.getName(), s.getName1());
		assertEquals(old_s.getTall(), s.getTall());
		
	}
	
	@Test
	public void convertListToMapString() throws CloneNotSupportedException, IllegalArgumentException, IllegalAccessException{
		List<Student> list = new ArrayList<Student>();
		Student student1 = (Student) old_s.clone();
		student1.setName("李四");
		Student student2 = (Student) old_s.clone();
		student2.setName("王五");
		Student student3 = (Student) old_s.clone();
		student3.setName("赵六");
		list.add(student1);
		list.add(student2);
		list.add(student3);
		Map<Object, Student> map = ObjectConvertUtil.convertListToMap(list, "name");
		assertEquals(student1,map.get("李四"));
		assertEquals(student2,map.get("王五"));
		assertEquals(student3,map.get("赵六"));
	}
	
	@Test
	public void convertListToMapInteger() throws CloneNotSupportedException, IllegalArgumentException, IllegalAccessException{
		List<Student> list = new ArrayList<Student>();
		Student student1 = (Student) old_s.clone();
		student1.setAge(21);
		Student student2 = (Student) old_s.clone();
		student2.setAge(22);
		Student student3 = (Student) old_s.clone();
		student3.setAge(23);
		list.add(student1);
		list.add(student2);
		list.add(student3);
		Map<Object, Student> map = ObjectConvertUtil.convertListToMap(list, "age");
		assertEquals(student1,map.get(21));
		assertEquals(student2,map.get(22));
		assertEquals(student3,map.get(23));
	}
	
	@Test
	public void convertMapToList() throws CloneNotSupportedException{
		Map<Object,Student> map = new HashMap<Object,Student>();
		Student student1 = (Student) old_s.clone();
		student1.setName("李四");
		Student student2 = (Student) old_s.clone();
		student2.setName("王五");
		Student student3 = (Student) old_s.clone();
		student3.setName("赵六");
		map.put("李四", student1);
		map.put("王五", student2);
		map.put("赵六", student3);
		
		List<Student> datas = ObjectConvertUtil.convertMapToList(map);
		Assert.assertTrue(datas.contains(student1));
		Assert.assertTrue(datas.contains(student2));
		Assert.assertTrue(datas.contains(student3));
	}
	
	@Test
	public void extractValue() throws CloneNotSupportedException, IllegalArgumentException, IllegalAccessException{
		List<Student> list = new ArrayList<Student>();
		Student student1 = (Student) old_s.clone();
		student1.setName("李四");
		Student student2 = (Student) old_s.clone();
		student2.setName("王五");
		Student student3 = (Student) old_s.clone();
		student3.setName("赵六");
		list.add(student1);
		list.add(student2);
		list.add(student3);
		
		Set<Object> keyValues = ObjectConvertUtil.extractValue(list, "name");
		Assert.assertTrue(keyValues.contains("李四"));
		Assert.assertTrue(keyValues.contains("王五"));
		Assert.assertTrue(keyValues.contains("赵六"));
		Assert.assertFalse(keyValues.contains("张三"));
	}
	
	@Test
	public void copy() throws IllegalArgumentException, IllegalAccessException{
		Student2 student = new Student2();
		ObjectConvertUtil.copy(old_s, student);
		assertEquals(old_s.getAge(), student.getAge());
		Assert.assertNull(student.getBirthday());
		assertEquals(old_s.getIsFale(), student.getIsFale());
		assertEquals(old_s.getMoney(), student.getMoney());
		assertEquals(old_s.getName(), student.getName());
		assertEquals(old_s.getTall(), student.getTall());
	}
}
