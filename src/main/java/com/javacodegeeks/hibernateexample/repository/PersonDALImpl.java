package com.javacodegeeks.hibernateexample.repository;

import com.javacodegeeks.hibernateexample.model.Person;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.springframework.stereotype.Repository;
import org.hibernate.search.query.dsl.QueryBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class PersonDALImpl implements PersonDAL {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Person> fuzzySearchPerson(String term) {

        FullTextEntityManager fullTextEntityManager =
                org.hibernate.search.jpa.Search.
                        getFullTextEntityManager(entityManager);

        QueryBuilder queryBuilder =
                fullTextEntityManager.getSearchFactory()
                        .buildQueryBuilder().forEntity(Person.class).get();

        Query fuzzyQuery = queryBuilder
                .keyword()
                .fuzzy()
                .withEditDistanceUpTo(2)
                .withPrefixLength(0)
                .onField("name")
                .matching(term)
                .createQuery();

        FullTextQuery jpaQuery =
                fullTextEntityManager.createFullTextQuery(fuzzyQuery, Person.class);

        return jpaQuery.getResultList();
    }

    @Override
    public List<Person> wildCardSearchPerson(String term) {
        FullTextEntityManager fullTextEntityManager =
                org.hibernate.search.jpa.Search.
                        getFullTextEntityManager(entityManager);

        QueryBuilder queryBuilder =
                fullTextEntityManager.getSearchFactory()
                        .buildQueryBuilder().forEntity(Person.class).get();

        Query wildcardQuery = queryBuilder
                .keyword()
                .wildcard()
                .onField("name")
                .matching("*" + term + "*")
                .createQuery();


        FullTextQuery jpaQuery =
                fullTextEntityManager.createFullTextQuery(wildcardQuery, Person.class);

        return jpaQuery.getResultList();
    }
}
