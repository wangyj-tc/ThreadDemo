package com.coop.model;
/**
 * 个人信息类，由于线程之间的不可见性，
 * 导致这个类并非是线程安全的
 * @author yongjian.wang
 *
 */
public class Person {
	
	private String name;
	
	private Integer age;

	public Person(String name, Integer age) {
		this.name = name;
		this.age = age;
	}

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
	
	

}
