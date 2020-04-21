package com.ibm.lab.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.lab.demo.model.Person;
import com.ibm.lab.demo.service.PersonService;

import io.swagger.annotations.ApiOperation;


@RestController
public class PersonController {

	private Logger logger = LoggerFactory.getLogger(PersonController.class);
	@Autowired
	PersonService personService;
	
	@ApiOperation(value = "회원 단건 조회", notes = "firstName로 회원을 조회한다")
	@Cacheable(value="person", key="#firstName")
	@GetMapping("/persons/{firstName}")
	public Person getPerson(@PathVariable String firstName) {
		logger.info("Getting record:{}", firstName);
		return personService.getByFirstName(firstName);
	}
	
	@Cacheable(value="person")
	@GetMapping("/persons")
	public List<Person> getAll() {
		logger.info("Getting all records:");
		return personService.getAll();
	}
	
	@Cacheable(value="person", key="person#firstName")
	@PostMapping("/persons")
	public Person create(@RequestBody Person person) {
		logger.info("create record:{}", person);
		return personService.create(person);
	}
	
	@CachePut(value="person", key="person#firstName")
	@PutMapping("/persons")
	public Person update(@RequestBody Person person) {
		logger.info("Update record:{}", person);
		return personService.update(person);
	}
	
	@CacheEvict(value="person", key="#firstName")
	@DeleteMapping("/persons/{firstName}")
	public Person delete(@PathVariable String firstName) {
		logger.info("deleted record:{}", firstName);
		return personService.delete(firstName);
	}
	
	@CacheEvict(value="persons", allEntries=true)
	@DeleteMapping("/persons")
	public String deleteAll() {
		logger.info("deleted all records");
		personService.deleteAll();
		
		return "Deleted all records";
	}
}
