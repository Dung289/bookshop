package com.example.bookshop.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bookshop.models.User;
import com.example.bookshop.serviceImpl.UserServiceImpl;

import jakarta.servlet.http.HttpSession;


@Controller
public class LoginController {
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
	public String login(Model model, @RequestParam String gmail, @RequestParam String password, HttpSession session) {
		Optional<User> opUser = userServiceImpl.getUserByGmailAndPassWord(gmail, password);
		if (opUser.isPresent()) {
			session.setAttribute("user", opUser.get());
			this.setUserId(opUser.get().getId());
			return "redirect:/book/";
		} else {
			model.addAttribute("error", "Sai thông tin đăng nhập");
			return "loginForm";
		}
	}
	
	@GetMapping("/register")
	public String showRegisterPage() {
		return "registerForm";
	}
	
	@PostMapping("/register")
	public String register(Model model, @ModelAttribute User user) {
		user.setRole("user");
		user.setImageName("image_init.jpg");
		Optional<User> opUser = userServiceImpl.getUserByGmail(user.getGmail());
		if (opUser.isPresent()) {
			model.addAttribute("error", "Gmail đã được đăng ký");
			return ("registerForm");
		} else {
			userServiceImpl.updateUser(user);
			return "redirect:/login";
		}	
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		return "redirect:/login";
	}
	
	@GetMapping("/changepassword")
	public String showChangePasswordPage() {
		return "changePasswordForm";
	}
	
	@PutMapping("/changepassword")
	public String changePassword(Model model, @RequestParam("gmail") String gmail,@RequestParam("password") String password, 
								@RequestParam("newpassword") String newpassword, @RequestParam("confirmpassword") String confirmpassword) {
		Optional<User> opUser = userServiceImpl.getUserByGmailAndPassWord(gmail, password);
		if (opUser.isPresent()) {
			if(newpassword.compareTo(confirmpassword) == 0) {
				User user = opUser.get();
				user.setPassword(newpassword);
				userServiceImpl.updateUser(user);
				return "loginForm";
			} else {
				model.addAttribute("error", "Mật khẩu xác nhận không chính xác");
				return "changePasswordForm";
			}
		} else {
			model.addAttribute("error", "Gmail hoặc mật khẩu không chính xác");
			return "changePasswordForm";
		}
	}
}
