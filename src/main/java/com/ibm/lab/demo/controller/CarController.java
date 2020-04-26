package com.ibm.lab.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.lab.demo.model.Car;
import com.ibm.lab.demo.service.CarService;

import io.swagger.annotations.ApiOperation;

@RestController
public class CarController {
	private Logger logger = LoggerFactory.getLogger(CarController.class);
	
	@Autowired
	CarService carService;
	
	@Cacheable(value="cars", key="#car.model")
	@PostMapping("/cars")
	public Car create(@RequestBody Car car) {
		logger.info("create record:{}", car);
		return carService.create(car);
	}
	
	@ApiOperation(value = "Car 단건 조회", notes = "model로 Car 조회한다")
	@Cacheable(value="cars", key="#model")
	@GetMapping("/cars/{model}")
	public Car getCar(@PathVariable String model) {
		logger.info("Getting record:{}", model);
		return carService.findByModel(model);
	}
	
	@CacheEvict(value="cars", key="#model")
	@DeleteMapping("/cars/{model}")
	public Car delete(@PathVariable String model) {
		logger.info("deleted record:{}", model);
		return carService.delete(model);
	}
	
	@CacheEvict(value="cars", allEntries=true)
	@DeleteMapping("/cars")
	public String deleteAll() {
		logger.info("deleted all records");
		carService.deleteAll();
		
		return "Deleted all records";
	}
}
