package com.javacodegeeks.hibernateexample.service;

import com.javacodegeeks.hibernateexample.model.Person;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface PersonService {

    Person createPerson(Person person);
    Person getPerson(Long id);
    Person editPerson(Person person);
    void deletePerson(Person person);
    void deletePerson(Long id);
    List<Person> getAllPersons(int pageNumber, int pageSize);

    @GetMapping("/all")
    List<Person> getAllPersons();
    long countPersons();
    List<Person> fuzzySearchPerson(String term);
    List<Person> wildCardSearchPerson(String term);
}
