package com.ibm.lab.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.ibm.lab.demo.model.Book;
import com.ibm.lab.demo.repository.BookRepository;

@Service 
public class BookService {
	@Autowired
	BookRepository bookRepository;
   
    public List<Book> findAll() {        
        return bookRepository.findAll();
    }
    
    public Book findByAuthor(String author) {        
        return bookRepository.findByAuthor(author);
    }

    public Book create(Book book) {        
        return bookRepository.save(book);
    }
    
    public Book updateBook(Book book) {     
    	Book b = bookRepository.findByAuthor(book.getAuthor());
    	
    	b.setAuthor(book.getAuthor());
    	b.setTitle(book.getTitle());
    	
        return bookRepository.save(b);
    }
    
    public Book delete(String author) {   
    	Book book = bookRepository.findByAuthor(author);
    	bookRepository.delete(book);
    	
        return book;
    }
    
    public void deleteAll() {   
    	bookRepository.deleteAll();
    }
}
