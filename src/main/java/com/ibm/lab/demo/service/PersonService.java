package com.ibm.lab.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.lab.demo.model.Person;
import com.ibm.lab.demo.repository.PersonRepository;

@Service
public class PersonService {
	@Autowired
	private PersonRepository personRepository;
	
	public Person create(Person p) {
		return personRepository.save(p);
	}
	
	public Person create(String firstName, String lastName, int age) {
		return personRepository.save(new Person(firstName, lastName, age));
	}
	
	public List<Person> getAll() {
		return personRepository.findAll();
	}
	
	public Person getByFirstName(String firstName) {
		return personRepository.findByFirstName(firstName);
	}
	
	public Person getByLastName(String firstName) {
		return personRepository.findByLastName(firstName);
	}
	
	public Person update(Person p) {
		return update(p.getFirstName(), p.getLastName(), p.getAge());
	}
	public Person update(String firstName, String lastName, int age) {
		Person p = personRepository.findByFirstName(firstName);
		
		p.setLastName(lastName);
		p.setAge(age);
		
		return personRepository.save(p);
	}
	
	public void deleteAll() {
		personRepository.deleteAll();
	}
	
	public Person delete(String firstName) {
		Person p = personRepository.findByFirstName(firstName);
		
		personRepository.delete(p);
		
		return p;
	}
}
