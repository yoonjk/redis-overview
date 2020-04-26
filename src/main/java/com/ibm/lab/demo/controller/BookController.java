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

import com.ibm.lab.demo.model.Book;
import com.ibm.lab.demo.service.BookService;

@RestController
public class BookController {
	private Logger logger = LoggerFactory.getLogger(BookController.class);
	
	@Autowired
	BookService bookService;
	
    @Cacheable(keyGenerator = "customKeyGenerator")
    @GetMapping("/books")
    public List<Book> getBooks() {
       
        return bookService.findAll();
    }
    
    @Cacheable(value="book", key="#author")
    @GetMapping("/books/{author}")
    public Book getBook(@PathVariable String author) {

        logger.info("getBooks:{}", author);
        return bookService.findByAuthor(author);
    }
    
    @Cacheable(value="book", key="#book.author")
    @PostMapping("/books")    
    public Book createBook(@RequestBody Book book) {

        logger.info("getBooks:{}", book);
        return bookService.create(book);
    }
    
    @CachePut(value="book",key="book#author")
    @PutMapping("/books")    
    public Book updateBook(@RequestBody Book book) {

        logger.info("getBooks:{}", book);
        return bookService.updateBook(book);
    }
    
    @CacheEvict(value="book",key="#author")
    @DeleteMapping("/books/{author}")
    public Book deleteBook(@PathVariable String author) {

        logger.info("getBooks:{}", author);
        return bookService.delete(author);
    }
    
    @CacheEvict(allEntries=true, keyGenerator = "customKeyGenerator")
    @DeleteMapping("/books")
    public String deleteAll() {

        logger.info("deleteAll books");
        bookService.deleteAll();
        return "Deleted all records...";
    }
}
