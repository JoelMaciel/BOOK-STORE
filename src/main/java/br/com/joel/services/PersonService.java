package br.com.joel.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.joel.data.vo.v1.PersonVO;
import br.com.joel.exceptions.ResourceNotFoundException;
import br.com.joel.mapper.DozerMapper;
import br.com.joel.model.Person;
import br.com.joel.repositories.PersonRepository;

@Service
public class PersonService {

	private Logger logger = Logger.getLogger(PersonService.class.getName());

	@Autowired
	private PersonRepository personRepository;

	public List<PersonVO> findAll() {
		logger.info("Finding all people");

		return DozerMapper.parseListObjects(personRepository.findAll(), PersonVO.class);
	}

	public PersonVO findById(Long personId) {

		logger.info("Finding one PersonVO !");

		var person = personRepository.findById(personId)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID !"));
		return DozerMapper.parseObject(person, PersonVO.class);
	}

	@Transactional
	public PersonVO create(PersonVO personVo) {
		logger.info("Creating one PersonVO!");

		var person = DozerMapper.parseObject(personVo, Person.class);
		var newPersonVo = DozerMapper.parseObject(personRepository.save(person), PersonVO.class);
		return newPersonVo;
	}

	@Transactional
	public PersonVO update(PersonVO personVo) {
		logger.info("Update one PersonVO");

		var person = personRepository.findById(personVo.getPersonId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID !"));

		person.setFirstName(personVo.getFirstName());
		person.setLastName(personVo.getLastName());
		person.setAddress(personVo.getAddress());
		person.setGender(personVo.getGender());

		var newPersonVo = DozerMapper.parseObject(personRepository.save(person), PersonVO.class);

		return newPersonVo;
	}

	public void delete(Long personId) {
		logger.info("Delete one PersonVO");

		var PersonVO = personRepository.findById(personId)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID !"));

		personRepository.delete(PersonVO);
	}

}
