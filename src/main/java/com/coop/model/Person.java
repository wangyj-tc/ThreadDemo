package com.coop.model;
/**
 * ������Ϣ�࣬�����߳�֮��Ĳ��ɼ��ԣ�
 * ��������ಢ�����̰߳�ȫ��
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
