package com.madhu.demo.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.madhu.demo.model.Person;
import com.madhu.demo.service.PersonService;

@RestController
@RequestMapping(value = "/Person")
public class PersonController {
	@Autowired
	private PersonService Personservice;

	@GetMapping(value = { "/Persons", "/Persons/{PersonId}", "/Persons/Name/{Name}", "/Persons/Age/{Age}" })
	public ResponseEntity<?> getPersonByProperty(@PathVariable Map<String, String> pathVariableMap) {

		ResponseEntity<?> response = null;
		try {
			int PersonId = isEmpty(pathVariableMap.get("PersonId")) ? Integer.parseInt(pathVariableMap.get("PersonId"))
					: 0;
			String Name = pathVariableMap.get("Name");
			int Age = isEmpty(pathVariableMap.get("Age")) ? Integer.parseInt(pathVariableMap.get("Age")) : 0;
			List<Person> list = Personservice.getAllPerson();

			if (PersonId != 0) {
				response = new ResponseEntity<>(Personservice.getPersonById(PersonId), HttpStatus.OK);
			}

			else if ((Name) != null) {
				response = new ResponseEntity<Person>(Personservice.getPersonByName(Name), HttpStatus.OK);
			}

			else if (Age != 0) {
				response = new ResponseEntity<>(Personservice.getPersonByAge(Age), HttpStatus.OK);
			}

			else if (list != null && !list.isEmpty()) {
				list.sort((s1, s2) -> s1.getName().compareTo(s2.getName()));
				response = new ResponseEntity<List<Person>>(list, HttpStatus.OK);
			}

			else {
				response = new ResponseEntity<String>("No Persons details Found", HttpStatus.OK);
			}

		} catch (Exception e) {

			response = new ResponseEntity<String>("Unable to Fetch Person details", HttpStatus.INTERNAL_SERVER_ERROR); // 500
			e.printStackTrace();
		}
		return response;
	}

	private boolean isEmpty(String str) {
		if (str != null && str.length() > 0)
			return true;
		return false;
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