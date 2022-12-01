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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/persons/v1")
@Tag(name = "People", description = "Endpoints for Managing People")
public class PersonController {

	@Autowired
	private PersonService personService;

	@GetMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
	@Operation(summary = "Finds all People", description = "Finds all People", tags = { "People" }, responses = {
			@ApiResponse(description = "Success", responseCode = "200",
					content = {
					@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PersonVO.class))) }),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content), })
	public List<PersonVO> findAll() {
		return personService.findAll();
	}

	@GetMapping(value = "/{personId}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
			MediaType.APPLICATION_YML })
	@Operation(summary = "Finds a Person", description = "Finds a Person", tags = { "People" }, responses = {
			@ApiResponse(description = "Success", responseCode = "200", 
					content = @Content(schema = @Schema(implementation = PersonVO.class))),
			@ApiResponse(description = "No content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content), })
	public PersonVO findById(@PathVariable(value = "personId") Long personId) {

		return personService.findById(personId);
	}

	@PostMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
	@Operation(summary = "Add a new Person",
	description = "Add a new Person by passing in a JSON, XML or YML representation of the person!",
	tags = { "People" },
	responses = {
			@ApiResponse(description = "Created", responseCode = "201",
					content = @Content(schema = @Schema(implementation = PersonVO.class))),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content), })
	public PersonVO create(@RequestBody PersonVO personVO) {
		return personService.create(personVO);

	}

	@PostMapping(value = "/v2", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
			MediaType.APPLICATION_YML })
	@Operation(summary = "Add a new Person",
	description = "Add a new PersonV2 by passing in a JSON, XML or YML representation of the person!",
	tags = { "People" },
	responses = {
			@ApiResponse(description = "Created", responseCode = "201",
					content = @Content(schema = @Schema(implementation = PersonVOV2.class))),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content), })
	public PersonVOV2 createV2(@RequestBody PersonVOV2 personV2) {
		return personService.createV2(personV2);
	}

	@PutMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
	@Operation(summary = "Updates a  Person",
	description = "Updates a Person by passing in a JSON, XML or YML representation of the person!",
	tags = { "People" },
	responses = {
			@ApiResponse(description = "Success", responseCode = "200",
					content = @Content(schema = @Schema(implementation = PersonVO.class))),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content), })
	public ResponseEntity<PersonVO> update(@RequestBody PersonVO personVO) {
		personService.update(personVO);

		return ResponseEntity.status(HttpStatus.OK).body(personVO);
	}

	@DeleteMapping("/{personId}")
	@Operation(summary = "Delete a Person",
	description = "Deletes a Person by passing in a JSON, XML or YML representation of the person!",
	tags = { "People" },
	responses = {
			@ApiResponse(description = "No Content", responseCode = "204",content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content), })
	public ResponseEntity<Void> delete(@PathVariable("personId") Long personId) {
		personService.delete(personId);

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
