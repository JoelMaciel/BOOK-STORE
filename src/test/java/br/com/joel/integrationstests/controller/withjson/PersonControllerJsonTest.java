package br.com.joel.integrationstests.controller.withjson;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.joel.configs.TestConfig;
import br.com.joel.integrationstests.testcontainers.AbstractIntegrationTest;
import br.com.joel.integrationstests.vo.PersonVO;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(OrderAnnotation.class)
public class PersonControllerJsonTest extends AbstractIntegrationTest{
	
	private static RequestSpecification specification;
	private static ObjectMapper objectMapper;
	
	private static PersonVO person;
	
	
	@BeforeAll
	public static void setUp() {
		objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		
		person = new PersonVO();
	}

	@Test
	@Order(1)
	public void testCreate() throws JsonMappingException, JsonProcessingException {
		
		mockPerson();
		
		specification = new RequestSpecBuilder()
				.addHeader(TestConfig.HEADER_PARAM_ORIGIN, TestConfig.ORIGIN_JOEL)
				.setBasePath("/api/persons/v1")
				.setPort(TestConfig.SERVER_PORT)
				.addFilter(new RequestLoggingFilter(LogDetail.ALL))
				.addFilter(new ResponseLoggingFilter(LogDetail.ALL))
				.build();
		
		var content =
				given().spec(specification)
				.contentType(TestConfig.CONTENT_TYPE_JSON)
				.body(person)
				.when()
				.post()
				.then()
				.statusCode(200)
				.extract()
				.body()
				.asString();
		
		PersonVO createdPerson = objectMapper.readValue(content, PersonVO.class);
		person = createdPerson;
		
		assertNotNull(createdPerson);
		
		assertNotNull(createdPerson.getPersonId());
		assertNotNull(createdPerson.getFirstName());
		assertNotNull(createdPerson.getLastName());
		assertNotNull(createdPerson.getAddress());
		assertNotNull(createdPerson.getGender());
		
		assertTrue(createdPerson.getPersonId() > 0);
		
		assertEquals("Joel", createdPerson.getFirstName());
		assertEquals("Maciel", createdPerson.getLastName());
		assertEquals("Olavo Bilac", createdPerson.getAddress());
		assertEquals("Male", createdPerson.getGender());
	}
	
	@Test
	@Order(2)
	public void testCreateWithWrongOrigin() throws JsonMappingException, JsonProcessingException {
		
		mockPerson();
		
		specification = new RequestSpecBuilder()
				.addHeader(TestConfig.HEADER_PARAM_ORIGIN, TestConfig.ORIGIN_MACIEL)
				.setBasePath("/api/persons/v1")
				.setPort(TestConfig.SERVER_PORT)
				.addFilter(new RequestLoggingFilter(LogDetail.ALL))
				.addFilter(new ResponseLoggingFilter(LogDetail.ALL))
				.build();
		
		var content =
			given().spec(specification)
				.contentType(TestConfig.CONTENT_TYPE_JSON)
					.body(person)
				.when()
					.post()
				.then()
					.statusCode(403)
						.extract()
						.body()
							.asString();
		
		
		assertNotNull(content);
		
		assertEquals("Invalid CORS request", content);
	}
	
	@Test
	@Order(3)
	public void testFindById() throws JsonMappingException, JsonProcessingException {
		
		mockPerson();
		
		specification = new RequestSpecBuilder()
				.addHeader(TestConfig.HEADER_PARAM_ORIGIN, TestConfig.ORIGIN_JOEL)
				.setBasePath("/api/persons/v1")
				.setPort(TestConfig.SERVER_PORT)
				.addFilter(new RequestLoggingFilter(LogDetail.ALL))
				.addFilter(new ResponseLoggingFilter(LogDetail.ALL))
				.build();
		
		var content =
				given().spec(specification)
				.contentType(TestConfig.CONTENT_TYPE_JSON)
					.pathParam("personId", person.getPersonId())
					.when()
						.get("{personId}")
					.then()
						.statusCode(200)
						.extract()
						.body()
							.asString();
		
		PersonVO findIdPerson = objectMapper.readValue(content, PersonVO.class);
		person = findIdPerson;
		
		assertNotNull(findIdPerson);
		
		assertNotNull(findIdPerson.getPersonId());
		assertNotNull(findIdPerson.getFirstName());
		assertNotNull(findIdPerson.getLastName());
		assertNotNull(findIdPerson.getAddress());
		assertNotNull(findIdPerson.getGender());
		
		assertTrue(findIdPerson.getPersonId() > 0);
		
		assertEquals("Joel", findIdPerson.getFirstName());
		assertEquals("Maciel", findIdPerson.getLastName());
		assertEquals("Olavo Bilac", findIdPerson.getAddress());
		assertEquals("Male", findIdPerson.getGender());
	}
	
	
	@Test
	@Order(4)
	public void testFindByIdWithWrongOrigin() throws JsonMappingException, JsonProcessingException {
		
		mockPerson();
		
		specification = new RequestSpecBuilder()
				.addHeader(TestConfig.HEADER_PARAM_ORIGIN, TestConfig.ORIGIN_MACIEL)
				.setBasePath("/api/persons/v1")
				.setPort(TestConfig.SERVER_PORT)
					.addFilter(new RequestLoggingFilter(LogDetail.ALL))
					.addFilter(new ResponseLoggingFilter(LogDetail.ALL))
				.build();
		
		var content =
				given().spec(specification)
				.contentType(TestConfig.CONTENT_TYPE_JSON)
					.pathParam("personId", person.getPersonId())
				.when()
					.get("{personId}")
				.then()
					.statusCode(403)
						.extract()
						.body()
							.asString();
		
        assertNotNull(content);
		
		assertEquals("Invalid CORS request", content);
		
	}

	private void mockPerson() {
		person.setFirstName("Joel");
		person.setLastName("Maciel");
		person.setAddress("Olavo Bilac");
		person.setGender("Male");
		
	}

}
