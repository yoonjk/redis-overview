package com.ibm.lab.demo.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cars")
public class Car implements Serializable {
    @Id
    private String id;
    private String brand;
    private String model;
    private int horsePower;
 
    public Car() {}
    
    public Car(String brand, String model, int horsePower) {
        super();
        this.brand = brand;
        this.model = model;
        this.horsePower = horsePower;
    }
 
    public String getId() {
        return id;
    }
 
    public void setId(String id) {
        this.id = id;
    }
 
    public String getBrand() {
        return brand;
    }
 
    public void setBrand(String brand) {
        this.brand = brand;
    }
 
    public String getModel() {
        return model;
    }
 
    public void setModel(String model) {
        this.model = model;
    }
 
    public int getHorsePower() {
		return horsePower;
	}

	public void setHorsePower(int horsePower) {
		this.horsePower = horsePower;
	}

	@Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Id:- " + getId() + ", Brand:- " + getBrand() + ", Model:- " + getModel() + ", horsePower=" + horsePower );
        return str.toString();
    }
}
