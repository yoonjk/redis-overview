package com.ibm.lab.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ibm.lab.demo.model.Person;

@Repository
public interface PersonRepository extends MongoRepository<Person, String> {
	public Person findByFirstName(String firstName);
	public Person findByLastName(String lastName);
	public List<Person> findByAge(int age);
}
