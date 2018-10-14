package com.javacodegeeks.hibernateexample.service;

import com.javacodegeeks.hibernateexample.model.Person;
import com.javacodegeeks.hibernateexample.repository.PersonDAL;
import com.javacodegeeks.hibernateexample.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
@Controller
@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PersonDAL personDAL;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository, PersonDAL personDAL) {
        this.personRepository = personRepository;
        this.personDAL = personDAL;
    }

    @Override
    public Person createPerson(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Person getPerson(Long id) {
        return personRepository.findOne(id);
    }

    @Override
    public Person editPerson(Person person) {
        return personRepository.save(person);
    }

    @Override
    public void deletePerson(Person person) {
        personRepository.delete(person);
    }

    @Override
    public void deletePerson(Long id) {
        personRepository.delete(id);
    }

    @Override
    public List<Person> getAllPersons(int pageNumber, int pageSize) {
        return personRepository.findAll(new PageRequest(pageNumber, pageSize)).getContent();
    }

    @Override
    public @ResponseBody List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @Override
    public long countPersons() {
        return personRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Person> fuzzySearchPerson(String term) {
        return personDAL.fuzzySearchPerson(term);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Person> wildCardSearchPerson(String term) {
        return personDAL.wildCardSearchPerson(term);
    }
}
