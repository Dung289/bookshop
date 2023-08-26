package com.example.bookshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.example.bookshop.models.Book;
import com.example.bookshop.models.Review;
import com.example.bookshop.models.User;
import com.example.bookshop.serviceImpl.BookServiceImpl;
import com.example.bookshop.serviceImpl.ReviewServiceImpl;

@Controller
@RequestMapping("/review")
public class ReviewController {
	
	@Autowired
	BookServiceImpl bookServiceImpl;
	
	@Autowired
	ReviewServiceImpl reviewServiceImpl;
	
	@PostMapping("/addReview/{idBook}")
	public String addReview(Model model, @PathVariable Long idBook, @SessionAttribute(required = false) User user,
							@RequestParam("rate") int rate, @RequestParam("comment") String comment) {
		if(user != null) {
			Book book = bookServiceImpl.getBookById(idBook).get();
			Review review = new Review();
			review.setBook(book);
			review.setUser(user);
			review.setComment(comment);
			review.setRate(rate);
			reviewServiceImpl.update(review);
			return "redirect:/book/" + idBook;	
		}
		else {
			model.addAttribute("error", "Vui lòng đăng nhập để có thể đánh giá");
			return "loginForm";
		}
	}
	
}
