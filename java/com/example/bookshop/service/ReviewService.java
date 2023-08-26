package com.example.bookshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.bookshop.models.Book;
import com.example.bookshop.models.Review;

@Service
public interface ReviewService {
	List<Review> getAllByBook(Book book);
	void update(Review review);
}
