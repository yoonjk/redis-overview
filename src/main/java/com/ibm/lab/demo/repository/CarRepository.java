package com.ibm.lab.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.ibm.lab.demo.model.Car;

public interface CarRepository extends MongoRepository<Car, String> {
    public Car findByModel(String model);
    public List<Car> findByBrand(String brand);
    @Query("{'horsePower': {$lt: ?0}}")
    public List<Car> findCarsWithHorsePowerLessThan(int horsePower);
}
