package com.example.bookshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.bookshop.models.Book;

@Service
public interface BookService {
	List<Book> getAllBooks();
	Optional<Book> getBookById(Long id);
	void deleteBookById(Long id);
	void updateBook(Book book);
}
