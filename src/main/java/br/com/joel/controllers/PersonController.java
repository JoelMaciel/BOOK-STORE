package br.com.joel.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.joel.model.Person;
import br.com.joel.services.PersonServices;

@RestController
@RequestMapping("/persons")
public class PersonController {

	@Autowired
	private PersonServices personServices;
	
	@GetMapping
	public List<Person> findAll() {
		return  personServices.findAll();
	}
	
	@GetMapping(value = "/{personId}")
	public Person findById(@PathVariable(value = "personId") String personId) {

		return personServices.findById(personId);
	}

}
