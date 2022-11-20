package br.com.joel.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.joel.data.vo.v1.PersonVO;
import br.com.joel.exceptions.ResourceNotFoundException;
import br.com.joel.repositories.PersonRepository;
import jakarta.transaction.Transactional;

@Service
public class PersonService {

	private Logger logger = Logger.getLogger(PersonService.class.getName());

	@Autowired
	private PersonRepository personRepository;

	public List<PersonVO> findAll() {
		logger.info("Finding all people");
		
		return personRepository.findAll();
	}

	public PersonVO findById(Long personId) {

		logger.info("Finding one PersonVO !");

		var PersonVO = new PersonVO();
		PersonVO.setFirstName("Joel Maciel");
		PersonVO.setLastName("Viana");
		PersonVO.setAddress("Caucauia - Ceará");
		PersonVO.setGender("Male");

		return personRepository.findById(personId)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID !"));
	}

	@Transactional
	public PersonVO create(PersonVO personVo) {
		logger.info("Creating one PersonVO!");

		return personRepository.save(personVo);
	}

	@Transactional
	public PersonVO update(PersonVO personVo) {
		logger.info("Update one PersonVO");
		
		var newPerson = personRepository.findById(personVo.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID !"));

		newPerson.setFirstName(personVo.getFirstName());
		newPerson.setLastName(personVo.getLastName());
		newPerson.setAddress(personVo.getAddress());
		newPerson.setGender(personVo.getGender());

		return personRepository.save(newPerson);
	}

	public void delete(Long personId) {
		logger.info("Delete one PersonVO");

		var PersonVO = personRepository.findById(personId)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID !"));

		personRepository.delete(PersonVO);
	}

}
