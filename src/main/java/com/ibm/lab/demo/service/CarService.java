package com.ibm.lab.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.ibm.lab.demo.model.Car;
import com.ibm.lab.demo.model.Person;
import com.ibm.lab.demo.repository.CarRepository;

@Service 
public class CarService {
//    @Autowired
//    MongoTemplate mongoTemplate;
// 
//    final String COLLECTION = "cars";
    
    @Autowired
    CarRepository carRepository;
    
	public Car create(Car car) {
		return carRepository.save(car);
	}
	
	public List<Car> getAll() {
		return carRepository.findAll();
	}
	
	public Car findByModel(String model) {
		return carRepository.findByModel(model);
	}
	
	public Car update(Car car) {
		Car c = carRepository.findByModel(car.getModel());
		
		c.setBrand(car.getBrand());
		c.setHorsePower(car.getHorsePower());
		c.setModel(car.getModel());
		
		return carRepository.save(c);
	}
	
	public void deleteAll() {
		carRepository.deleteAll();
	}
	
	public Car delete(String model) {
		Car car= carRepository.findByModel(model);
		
		carRepository.delete(car);
		
		return car;
	}
}
