package com.ibm.lab.demo.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.ibm.lab.demo.model.Account;
import com.ibm.lab.demo.repository.AccountRepository;
import com.mongodb.WriteResult;
import com.mongodb.client.result.UpdateResult;

@Service 
public class AccountService {
	@Autowired
	AccountRepository accountRepository;
   
	@Autowired
	MongoTemplate mongoTemplate;
	
	private Logger logger = LoggerFactory.getLogger(AccountService.class);
			
    public List<Account> findAll() {        
        return accountRepository.findAll();
    }
    
    public Account findByActNo(String name) {   
    	Account acc =  accountRepository.findByActNo(name);
    	
        return acc;
    }
    

    public Account create(Account acc) {        
        return accountRepository.save(acc);
    }
    
    public Account updateAccount(Account account) {         	
    	logger.info("updateAccount=>input acc:{}, result:{}", account.getActNo());
    	
    	Query query = new Query();
    	query.addCriteria(Criteria.where("actNo").is(account.getActNo()));
    	Update update = new Update();
    	update.set("amount", account.getAmount());
    	
    	UpdateResult writeResult = mongoTemplate.updateFirst(query, update, Account.class);
    	
    	logger.info("updateAccount=>UpdateResult acc:{}, result:{}", writeResult);
    	
        return accountRepository.findByActNo(account.getActNo());
    }
    
    public Account delete(String actNo) {   
    	Account acc = accountRepository.findByActNo(actNo);
    	
    	logger.info("updateAccount:{}", acc);
    	
    	accountRepository.delete(acc);
    	
        return acc;
    } 
    
    public void deleteAll() {   
    	accountRepository.deleteAll();
    }
}
