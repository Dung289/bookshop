package com.example.bookshop.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bookshop.models.Book;
import com.example.bookshop.models.Review;
import com.example.bookshop.repositories.ReviewRepository;
import com.example.bookshop.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService{
	
	@Autowired
	ReviewRepository reviewRepository;

	@Override
	public List<Review> getAllByBook(Book book) {
		return reviewRepository.findAllByBook(book);
	}

	@Override
	public void update(Review review) {
		reviewRepository.save(review);
		
	}

}
