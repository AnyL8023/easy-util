package test.anyl.entity;

import java.util.Date;

public class Student3 implements Cloneable {
	private String name1;
	private Integer age1;
	private Long money1;
	private Double tall;
	private Boolean isFale;
	private String birthday;
	
	public String getName1() {
		return name1;
	}
	public void setName1(String name1) {
		this.name1 = name1;
	}
	public Integer getAge1() {
		return age1;
	}
	public void setAge1(Integer age1) {
		this.age1 = age1;
	}
	public Long getMoney1() {
		return money1;
	}
	public void setMoney1(Long money1) {
		this.money1 = money1;
	}
	public Double getTall() {
		return tall;
	}
	public void setTall(Double tall) {
		this.tall = tall;
	}
	public Boolean getIsFale() {
		return isFale;
	}
	public void setIsFale(Boolean isFale) {
		this.isFale = isFale;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	@Override
	public String toString() {
		return "Student3 [name1=" + name1 + ", age1=" + age1 + ", money1=" + money1 + ", tall=" + tall + ", isFale="
				+ isFale + ", birthday=" + birthday + "]";
	}
	
}
