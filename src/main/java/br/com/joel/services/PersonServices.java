package br.com.joel.services;

import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import br.com.joel.model.Person;

@Service
public class PersonServices {
	
	private final AtomicLong counter = new AtomicLong();
	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
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

}






