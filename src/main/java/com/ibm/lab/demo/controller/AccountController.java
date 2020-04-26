package com.ibm.lab.demo.controller;

import java.util.List;
import java.util.Optional;

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

import com.ibm.lab.demo.model.Account;
import com.ibm.lab.demo.service.AccountService;

@RestController
public class AccountController {
	private Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	@Autowired
	AccountService accountService;
	
	@Cacheable(value="account")
    @GetMapping("/accounts")
    public List<Account> getAccounts() {
       
        return accountService.findAll();
    }
    
    @Cacheable(value="account", key="#actNo")
    @GetMapping("/accounts/{actNo}")
    public Account getAccount(@PathVariable String actNo) {

        logger.info("getAccounts:{}", actNo);
        return accountService.findByActNo(actNo);
    }
    
    @Cacheable(value="account", key="#account.actNo")
    @PostMapping("/accounts")    
    public Account createAccount(@RequestBody Account account) {

        logger.info("getAccounts:{}", account);
        return accountService.create(account);
    }
    
    @CachePut(value="account",key="#account.actNo")
    @PutMapping("/accounts")    
    public Account updateAccount(@RequestBody Account account) {

        logger.info("getBooks:{}", account);
        return accountService.updateAccount(account);
    }
    
    @CacheEvict(value="account",key="#actNo")
    @DeleteMapping("/accounts/{actNo}")
    public Account deleteAccountNo(@PathVariable String actNo) {

        logger.info("getAccounts:{}", actNo);
        return accountService.delete(actNo);
    }
    
    
    @CacheEvict(value="account", allEntries=true)
    @DeleteMapping("/accounts")
    public String deleteAll() {

        logger.info("deleteAll accounts");
        accountService.deleteAll();
        return "Deleted all records...";
    }
}
