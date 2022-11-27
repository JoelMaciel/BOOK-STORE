package br.com.joel.junittests.mockito.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.joel.junittests.mapper.mocks.MockPerson;
import br.com.joel.model.Person;
import br.com.joel.repositories.PersonRepository;
import br.com.joel.services.PersonService;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {
	
	MockPerson mockPerson;
	
	@InjectMocks
	private PersonService personService;
	
	@Mock
	PersonRepository personRepository;
	
	@BeforeEach
	void setUpMocks() throws Exception {
		mockPerson = new MockPerson();
		MockitoAnnotations.openMocks(this);
		
	}
	
	@Test
	void testFindById() {
		Person person = mockPerson.mockEntity(1);
		person.setPersonId(1L);
		
		when(personRepository.findById(1L)).thenReturn(Optional.of(person));
		
		var result = personService.findById(1L);
		assertNotNull(result);
		assertNotNull(result.getPersonId());
		assertNotNull(result.getLinks());
		System.out.println(result.toString());
		assertTrue(result.toString().contains("links: [</api/persons/v1/1>;rel=\"self\"]"));
		assertEquals("Addres Test1", result.getAddress());
		assertEquals("First Name Test1", result.getFirstName());
		assertEquals("Last Name Test1", result.getLastName());
		assertEquals("Female", result.getGender());
		
		
	}
	
	@Test
	void testFindAll() {
		fail("Not yet implemented");  
	}
	
	
	
	@Test
	void testCreate() {
		fail("Not yet implemented");
	}
	
	@Test
	void testUpdate() {
		fail("Not yet implemented");
	}
	
	@Test
	void testDelete() {
		fail("Not yet implemented");
	}

}





