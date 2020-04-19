package com.ibm.lab.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.lab.demo.model.Person;
import com.ibm.lab.demo.service.PersonService;


@RestController
public class PersonController {

	private Logger logger = LoggerFactory.getLogger(PersonController.class);
	@Autowired
	PersonService personService;
	
	@GetMapping("/persons/{firstName}")
	public Person getPerson(@PathVariable String firstName) {
		logger.info("Getting record:{}", firstName);
		return personService.getByFirstName(firstName);
	}
	
	@GetMapping("/persons")
	public List<Person> getAll() {
		logger.info("Getting all records:");
		return personService.getAll();
	}
	
	@PostMapping("/persons")
	public Person create(@RequestBody Person person) {
		logger.info("create record:{}", person);
		return personService.create(person);
	}
	
	@PutMapping("/persons")
	public Person update(@RequestBody Person person) {
		logger.info("Update record:{}", person);
		return personService.update(person);
	}
	
	@DeleteMapping("/persons/{firstName}")
	public Person delete(@PathVariable String firstName) {
		logger.info("deleted record:{}", firstName);
		return personService.delete(firstName);
	}
	
	@DeleteMapping("/persons")
	public String deleteAll(@PathVariable String firstName) {
		personService.deleteAll();
		
		return "Deleted all records";
	}
}
