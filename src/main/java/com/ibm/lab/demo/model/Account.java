package com.ibm.lab.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Account {
    @Id
    private String id;
    private String actNo;
    long amount;

    public Account() {}
    
    public void update(String name) {
        this.actNo = name;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getActNo() {
		return actNo;
	}

	public void setActNo(String name) {
		this.actNo = name;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}
}