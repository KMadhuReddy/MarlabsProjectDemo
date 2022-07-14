package com.madhu.demo.model;

import javax.persistence.Id;

public class Person {
	@Id
	private int PersonId;
	private String Name;
	private int Age;

	public int getPersonId() {
		return PersonId;
	}

	public void setPersonId(int personId) {
		PersonId = personId;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getAge() {
		return Age;
	}

	public void setAge(int age) {
		Age = age;
	}

	@Override
	public String toString() {
		return "Person [Personid=" + PersonId + ", name=" + Name + ", Age=" + Age + "]";
	}

	public Person(int personId, String name, int age) {
		super();
		PersonId = personId;
		Name = name;
		Age = age;
	}

}