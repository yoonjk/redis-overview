package com.ibm.lab.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ibm.lab.demo.model.Book;

public interface BookRepository extends MongoRepository<Book, Integer> {
	public Book findByAuthor(String author);
}
