package com.javacodegeeks.hibernateexample.repository;

import com.javacodegeeks.hibernateexample.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
