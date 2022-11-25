package br.com.joel.controllers;

import java.util.List;

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

import br.com.joel.data.vo.v1.PersonVO;
import br.com.joel.data.vo.v2.PersonVOV2;
import br.com.joel.services.PersonService;
import br.com.joel.util.MediaType;

@RestController
@RequestMapping("/api/persons/v1")
public class PersonController {

	@Autowired
	private PersonService personService;

	@GetMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
			MediaType.APPLICATION_YML})
	public List<PersonVO> findAll() {
		return personService.findAll();
	}

	@GetMapping(value = "/{personId}", produces = {MediaType.APPLICATION_JSON, 
			MediaType.APPLICATION_XML , MediaType.APPLICATION_YML})
	public PersonVO findById(@PathVariable(value = "personId") Long personId) {

		return personService.findById(personId);
	}

	@PostMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML ,
			MediaType.APPLICATION_YML})
	public PersonVO create(@RequestBody PersonVO personVO) {
		return personService.create(personVO);
		
	}
	
	@PostMapping(value = "/v2", produces = {MediaType.APPLICATION_JSON,
			MediaType.APPLICATION_XML , MediaType.APPLICATION_YML})
	public PersonVOV2 createV2(@RequestBody PersonVOV2 personV2) {
		return personService.createV2(personV2);
	}

	@PutMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
			MediaType.APPLICATION_YML})
	public ResponseEntity<PersonVO> update(@RequestBody PersonVO personVO) {
		personService.update(personVO);

		return ResponseEntity.status(HttpStatus.OK).body(personVO);
	}

	@DeleteMapping("/{personId}")
	public ResponseEntity<Void> delete(@PathVariable("personId") Long personId) {
		personService.delete(personId);

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
