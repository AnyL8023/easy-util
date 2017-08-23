package test.anyl.util;

import static org.junit.Assert.assertEquals;

import java.util.Date;import java.util.Map;

import org.junit.Assert;
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
}
