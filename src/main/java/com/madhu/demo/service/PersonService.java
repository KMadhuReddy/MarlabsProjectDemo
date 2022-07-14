package com.madhu.demo.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.madhu.demo.model.Person;

@Service
public class PersonService implements IPersonService {
	private static List<Person> Persons = new ArrayList<Person>();
	static {
		Person Person1 = new Person(1, "Madhu", 30);
		Person Person2 = new Person(2, "Prabhanshu", 60);
		Person Person3 = new Person(3, "Nayak", 70);
		Persons.add(Person1);
		Persons.add(Person2);
		Persons.add(Person3);
	}

	public void addPerson(Person Person) {
		Persons.add(Person);
	}

	public void editPerson(Person Person, int PersonId) {
		Person record = getPersonById(PersonId);
		Persons.remove(record);
		Person.setPersonId(PersonId);
		Persons.add(Person);
	}

	public String deletePerson(int PersonId) {
		Person record = getPersonById(PersonId);
		Persons.remove(record);
		return "The Person details deleted: " + PersonId;
	}

	public Person getPersonById(int PersonId) {
		return Persons.stream().filter(b -> b.getPersonId() == PersonId).findFirst().get();
	}

	public Person getPersonByName(String Name) {
		return Persons.stream().filter(person -> person.getName().equals(Name)).findAny().orElse(null);
	}

	public Person getPersonByAge(int Age) {
		return Persons.stream().filter(b -> b.getAge() == Age).findFirst().get();
	}

	public List<Person> getAllPerson() {
		return Persons;
	}

}