package test.anyl.util;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

import com.anyl.util.ObjectConvertUtil;

import test.anyl.entity.Student;

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
}
