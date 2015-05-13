package edu.iis.mto.integrationtest.repository;

import static edu.iis.mto.integrationtest.repository.PersonBuilder.person;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import edu.iis.mto.integrationtest.model.Person;


public class PersonRepositoryIntegrationTest extends IntegrationTest {

	@Autowired
	private PersonRepository personRepository;

	

	@Test
	public void testSaveNewPersonAndCheckIsPersisted() {
        long count = personRepository.count();
		
		Person person = a(person().withId(count + 1)
				.withFirstName("Roberto").withLastName("Mancini"));
		personRepository.save( person );
		assertEquals(count + 1, personRepository.count());
		assertEquals("Mancini", personRepository.findOne(count + 1)
				.getLastName());
		
		personRepository.delete( person );
	}
	
	
	@Test
	public void testCanAccessDbAndGetTestData() {
		List<Person> foundTestPersons = personRepository.findAll();
		assertEquals(2, foundTestPersons.size());
	}

	private Person a(PersonBuilder builder) {
		return builder.build();
	}

}
