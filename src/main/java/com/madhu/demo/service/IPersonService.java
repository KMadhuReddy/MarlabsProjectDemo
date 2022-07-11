package com.madhu.demo.service;

import java.util.List;

import com.madhu.demo.model.Person;

public interface IPersonService {
	public void addPerson(Person Person);

	public void editPerson(Person Person, int PersonId);

	public String deletePerson(int PersonId);

	public Person getPersonById(int PersonId);

	public Person getPersonByName(String Name);

	public Person getPersonByAge(int Age);

	public List<Person> getAllPerson();

}