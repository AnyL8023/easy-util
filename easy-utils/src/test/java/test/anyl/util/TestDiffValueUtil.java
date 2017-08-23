package test.anyl.util;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.anyl.util.DiffObjectUtil;
import com.anyl.util.DiffObjectUtil.DiffValue;

import test.anyl.entity.Student;

public class TestDiffValueUtil {

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
	public void diffInteger() throws CloneNotSupportedException, IllegalArgumentException, IllegalAccessException{
		Student new_s = (Student) old_s.clone();
		new_s.setAge(19);
		List<DiffValue> diffs = DiffObjectUtil.diff(Student.class, old_s, new_s);
		assertEquals("age", diffs.get(0).getField().getName());
		assertEquals(20, diffs.get(0).getOldObj());
		assertEquals(19, diffs.get(0).getNewObj());
	}
	
	@Test
	public void diffDate() throws CloneNotSupportedException, IllegalArgumentException, IllegalAccessException{
		Student new_s = (Student) old_s.clone();
		new_s.setBirthday(new Date(2008,8,8));
		List<DiffValue> diffs = DiffObjectUtil.diff(Student.class, old_s, new_s);
		assertEquals("birthday", diffs.get(0).getField().getName());
		assertEquals(new Date(2017,8,23), diffs.get(0).getOldObj());
		assertEquals(new Date(2008,8,8), diffs.get(0).getNewObj());
	}
	
	@Test
	public void diffBoolean() throws CloneNotSupportedException, IllegalArgumentException, IllegalAccessException{
		Student new_s = (Student) old_s.clone();
		new_s.setIsFale(false);
		List<DiffValue> diffs = DiffObjectUtil.diff(Student.class, old_s, new_s);
		assertEquals("isFale", diffs.get(0).getField().getName());
		assertEquals(true, diffs.get(0).getOldObj());
		assertEquals(false, diffs.get(0).getNewObj());
	}
	
	@Test
	public void diffLong() throws CloneNotSupportedException, IllegalArgumentException, IllegalAccessException{
		Student new_s = (Student) old_s.clone();
		new_s.setMoney(200000l);
		List<DiffValue> diffs = DiffObjectUtil.diff(Student.class, old_s, new_s);
		assertEquals("money", diffs.get(0).getField().getName());
		assertEquals(100000l, diffs.get(0).getOldObj());
		assertEquals(200000l, diffs.get(0).getNewObj());
	}
	
	@Test
	public void diffString() throws CloneNotSupportedException, IllegalArgumentException, IllegalAccessException{
		Student new_s = (Student) old_s.clone();
		new_s.setName("李四");
		List<DiffValue> diffs = DiffObjectUtil.diff(Student.class, old_s, new_s);
		assertEquals("name", diffs.get(0).getField().getName());
		assertEquals("张三", diffs.get(0).getOldObj());
		assertEquals("李四", diffs.get(0).getNewObj());
	}
	
	@Test
	public void diffDouble() throws CloneNotSupportedException, IllegalArgumentException, IllegalAccessException{
		Student new_s = (Student) old_s.clone();
		new_s.setTall(160.5);
		List<DiffValue> diffs = DiffObjectUtil.diff(Student.class, old_s, new_s);
		assertEquals("tall", diffs.get(0).getField().getName());
		assertEquals(180.5, diffs.get(0).getOldObj());
		assertEquals(160.5, diffs.get(0).getNewObj());
	}
}
