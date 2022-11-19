package br.com.joel.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.joel.model.Person;
import br.com.joel.services.PersonService;

@RestController
@RequestMapping("/persons")
public class PersonController {

	@Autowired
	private PersonService personService;
	
	@GetMapping
	public List<Person> findAll() {
		return  personService.findAll();
	}
	
	@GetMapping(value = "/{personId}")
	public Person findById(@PathVariable(value = "personId") Long personId) {

		return personService.findById(personId);
	}
	
	@PostMapping
	public Person create(@RequestBody Person person) {
		personService.create(person);
		return person;
	}
	
	@PutMapping("/{personId}")
	public Person update(@RequestBody Person person) {
		personService.update(person);
		return person;
	}
	
	@DeleteMapping("/{personId}")
	public void delete(@PathVariable("personId") Long personId) {
		personService.delete(personId);
	}

}





