package br.com.joel.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import br.com.joel.model.Person;

@Service
public class PersonServices {
	
	private final AtomicLong counter = new AtomicLong();
	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	public List<Person> findAll() {
		List<Person> persons = new ArrayList<>();
		
		for (int i = 0; i < 8; i++) {
			Person person = mockPerson(i);
			persons.add(person);
		}
		return persons;
	}

	public Person findById(String personId) {
		
		logger.info("Finding one person !");
		
		var person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName("Joel Maciel");
		person.setLastName("Viana");
		person.setAddress("Caucauia - Ceará");
		person.setGender("Male");
		
		return person;
	}
	
	private Person mockPerson(int i) {
		
		logger.info("Finding all people !");
		
		var person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName("Person name " + i);
		person.setLastName("Last name " + i);
		person.setAddress("Some address in Brasil " + i);
		person.setGender("Male");
		return person;
	}

}






