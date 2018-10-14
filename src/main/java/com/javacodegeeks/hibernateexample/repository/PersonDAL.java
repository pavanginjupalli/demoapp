package com.javacodegeeks.hibernateexample.repository;

import com.javacodegeeks.hibernateexample.model.Person;

import java.util.List;

public interface PersonDAL {
    List<Person> fuzzySearchPerson(String term);
    List<Person> wildCardSearchPerson(String term);
}
