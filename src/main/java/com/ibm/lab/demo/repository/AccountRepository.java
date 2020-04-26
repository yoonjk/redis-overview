package com.ibm.lab.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ibm.lab.demo.model.Account;

public interface AccountRepository extends MongoRepository<Account, String> {
	public Account findByActNo(String name);
}
