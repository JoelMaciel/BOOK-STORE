package br.com.joel.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.joel.exceptions.ResourceNotFoundException;
import br.com.joel.model.Person;
import br.com.joel.repositories.PersonRepository;
import jakarta.transaction.Transactional;

@Service
public class PersonService {

	private Logger logger = Logger.getLogger(PersonService.class.getName());

	@Autowired
	private PersonRepository personRepository;

	public List<Person> findAll() {
		logger.info("Finding all people");
		
		return personRepository.findAll();
	}

	public Person findById(Long personId) {

		logger.info("Finding one person !");

		var person = new Person();
		person.setFirstName("Joel Maciel");
		person.setLastName("Viana");
		person.setAddress("Caucauia - Ceará");
		person.setGender("Male");

		return personRepository.findById(personId)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID !"));
	}

	@Transactional
	public Person create(Person person) {
		logger.info("Creating one person!");

		return personRepository.save(person);
	}

	@Transactional
	public Person update(Person person) {
		logger.info("Update one person");
		var newPerson = personRepository.findById(person.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID !"));

		newPerson.setFirstName(person.getFirstName());
		newPerson.setLastName(person.getLastName());
		newPerson.setAddress(person.getAddress());
		newPerson.setGender(person.getGender());

		return personRepository.save(newPerson);
	}

	public void delete(Long personId) {
		logger.info("Delete one person");

		var person = personRepository.findById(personId)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID !"));

		personRepository.delete(person);
	}

}
