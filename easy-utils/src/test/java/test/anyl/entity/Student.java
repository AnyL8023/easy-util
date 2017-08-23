package test.anyl.entity;

import java.util.Date;

public class Student implements Cloneable {
	private String name;
	private Integer age;
	private Long money;
	private Double tall;
	private Boolean isFale;
	private Date birthday;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Long getMoney() {
		return money;
	}
	public void setMoney(Long money) {
		this.money = money;
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
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
	
}
