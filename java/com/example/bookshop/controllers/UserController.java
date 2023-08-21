package com.example.bookshop.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bookshop.models.User;
import com.example.bookshop.serviceImpl.UserServiceImpl;

@Controller
public class UserController {
	@Autowired
	UserServiceImpl userServiceImpl;
	
	private Long userId;
	
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@GetMapping("/login") 
	public String showLoginPage() {
		return "loginForm";
	}
	
	@PostMapping("/login") 
	public String login(Model model, @RequestParam String gmail, @RequestParam String password) {
		Optional<User> opUser = userServiceImpl.getUserByGmailAndPassWord(gmail, password);
		if (opUser.isPresent()) {
			this.setUserId(opUser.get().getId());
			return "redirect:/book/";
		} else {
			model.addAttribute("error", "Sai thông tin đăng nhập");
			return "loginForm";
		}
	}
	
	@GetMapping("/register")
	public String showRegisterPage() {
		return ("registerForm");
	}
	
	@PostMapping("/register")
	public String register(Model model, @ModelAttribute User user) {
		user.setRole("user");
		user.setImageName("");
		Optional<User> opUser = userServiceImpl.getUserByGmail(user.getGmail());
		if (opUser.isPresent()) {
			model.addAttribute("error", "Gmail đã được đăng ký");
			return ("registerForm");
		} else {
			userServiceImpl.updateUser(user);
			return "redirect:/login";
		}
		
	}
}
