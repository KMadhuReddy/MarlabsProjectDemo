package com.madhu.demo.Controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.madhu.demo.model.Person;
import com.madhu.demo.service.PersonService;


@RestController
public class PersonController {
	@Autowired
	private PersonService Personservice;


//	@GetMapping("/Persons")
//	public List<Person> getAllPerson() {
//		List<Person> allPersonList = Personservice.getAllPerson();
//		return allPersonList;
//	}

	@GetMapping("/Persons")
	public ResponseEntity<?> getAllEmployees() {
		ResponseEntity<?> response = null;
		try {
			List<Person> list = Personservice.getAllPerson();
			if (list != null && !list.isEmpty()) {
				list.sort((s1, s2) -> s1.getName().compareTo(s2.getName()));
				response = new ResponseEntity<List<Person>>(list, HttpStatus.OK);
			} else {
				response = new ResponseEntity<String>("No Persons details Found", HttpStatus.OK);
			}
		} catch (Exception e) {

			response = new ResponseEntity<String>("Unable to Fetch Person details",
					HttpStatus.INTERNAL_SERVER_ERROR); // 500
			e.printStackTrace();
		}
		return response;
	}

//	@GetMapping("/Persons/{PersonId}")
//	public Person getPersonById(@PathVariable String PersonId) {
//		Person PersonDetails = Personservice.getPersonById(Integer.parseInt(PersonId));  
//		return PersonDetails;
//	}
	@GetMapping("/Persons/{PersonId}")
	public ResponseEntity<?> getPersonById(int PersonId) {
		ResponseEntity<?> response = null;
		try {

			Person list = Personservice.getPersonById(PersonId);
			if (list != null) {
				response = new ResponseEntity<List<Person>>(HttpStatus.OK);
			} else {
				response = new ResponseEntity<String>("No Persons details Found with this Id:", HttpStatus.OK);
			}
		} catch (Exception e) {

			response = new ResponseEntity<String>("Unable to Fetch Person details please enter id as numeric value",
					HttpStatus.INTERNAL_SERVER_ERROR); // 500
			e.printStackTrace();
		}
		return response;
	}

//	@GetMapping("/Persons/Name/{Name}")
//	public Person getPersonByName(@PathVariable String Name) {
//		Person PersonDetails = Personservice.getPersonByName(Name.toString());
//		return PersonDetails;
//	}
	
	@GetMapping("/Persons/Name/{Name}")
	public ResponseEntity<?> getPersonByName(String Name) {
		ResponseEntity<?> response = null;
		try {

			Person list = Personservice.getPersonByName(Name);
			if (list != null) {
				
				response = new ResponseEntity<List<Person>>(HttpStatus.OK);
			} else {
				response = new ResponseEntity<String>("No Persons details Found with this Name:", HttpStatus.OK);
			}
		} catch (Exception e) {

			response = new ResponseEntity<String>("Unable to Fetch Person details with that name",
					HttpStatus.INTERNAL_SERVER_ERROR); // 500
			e.printStackTrace();
		}
		return response;
	}

	@GetMapping("/Persons/Age/{Age}")
	public Person getPersonByAge(@PathVariable String Age) {
		Person PersonDetails = Personservice.getPersonByAge(Integer.parseInt(Age));
		return PersonDetails;
	}

	@PostMapping(value = "/Persons")
	public void addPerson(@RequestBody Person Person) {
		Personservice.addPerson(Person);
	}

	@PutMapping(value = "/Persons/{PersonId}")
	public void editPerson(@RequestBody Person Person, @PathVariable String PersonId) {
		Personservice.editPerson(Person, Integer.parseInt(PersonId));
	}

	@DeleteMapping(value = "/Persons/{PersonId}")
	public void deletePerson(@RequestBody Person Person, @PathVariable String PersonId) {
		Personservice.deletePerson(Integer.parseInt(PersonId));
	}

}