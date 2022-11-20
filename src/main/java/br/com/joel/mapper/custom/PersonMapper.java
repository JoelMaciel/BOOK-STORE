package br.com.joel.mapper.custom;

import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.joel.data.vo.v2.PersonVOV2;
import br.com.joel.model.Person;

@Service
public class PersonMapper {

	public PersonVOV2 convertEntityToVo(Person person) {
		var personV2 = new PersonVOV2();
		personV2.setPersonId(person.getPersonId());
		personV2.setAddress(person.getAddress());
		personV2.setBirthDay(new Date());
		personV2.setFirstName(person.getFirstName());
		personV2.setLastName(person.getLastName());
		personV2.setGender(person.getGender());
		
		return personV2;
	}
	
	public Person convertVoToEntity(PersonVOV2 personV2) {
		var person = new Person();
		person.setPersonId(personV2.getPersonId());
		person.setAddress(personV2.getAddress());
		person.setFirstName(personV2.getFirstName());
		person.setLastName(personV2.getLastName());
		person.setGender(personV2.getGender());
		
		return person;
	}
	
	
}
