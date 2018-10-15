package com.javacodegeeks.hibernateexample;

import com.javacodegeeks.hibernateexample.model.Person;
import com.javacodegeeks.hibernateexample.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class DataJpaApp implements CommandLineRunner {

	private static final Logger LOG = LoggerFactory.getLogger("JCG");

	@Autowired
	private PersonService service;

	public static void main(String[] args) {
		SpringApplication.run(DataJpaApp.class, args);
	}

	@Override
	public void run(String... strings) {

		LOG.info("Current objects in DB: {}", service.countPersons());

		Person person = service.createPerson(new Person("Shubham", 23));
		LOG.info("Person created in DB : {}", person);

		LOG.info("Current objects in DB: {}", service.countPersons());

		List<Person> fuzzySearchedPersons = service.fuzzySearchPerson("Shubha");
		LOG.info("Founds objects in fuzzy search: {}", fuzzySearchedPersons.get(0));

		List<Person> wildSearchedPersons = service.wildCardSearchPerson("hub");
		LOG.info("Founds objects in wildcard search: {}", wildSearchedPersons.get(0));

		person.setName("Programmer");
		Person editedPerson = service.editPerson(person);
		LOG.info("Person edited in DB  : {}", person);

		//test4
		//service.deletePerson(person);
		//LOG.info("After deletion, count: {}", service.countPersons());
	}
}
