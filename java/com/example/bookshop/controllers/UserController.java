package com.example.bookshop.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.example.bookshop.models.User;
import com.example.bookshop.serviceImpl.UserServiceImpl;

@Controller
@RequestMapping("/user")
public class UserController {
	public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/userImages";
	
	
	@Autowired
	UserServiceImpl userServiceImpl;
	@GetMapping("/infor")
	public String getMyInfor(Model model, @SessionAttribute(required = false) User user) {
		if (user != null) {
			model.addAttribute("user", user);
			return "myInfor";
		} else {
			return "loginForm";
		}
	}
	
	@PutMapping("/save/{id}")
	public String updateUser(@ModelAttribute("user") User user1,
							 @RequestParam("userImage") MultipartFile fileUserName,
							 @RequestParam("imgName") String imgName, 
							 @SessionAttribute(required = false) User user) throws IOException {
		user.setFirstName(user1.getFirstName());
		user.setLastName(user1.getLastName());
		String imageUUID;
		if(!fileUserName.isEmpty()) {
			imageUUID=fileUserName.getOriginalFilename();
			Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
			Files.write(fileNameAndPath, fileUserName.getBytes());
		} else {
			imageUUID = imgName;
		}
		user.setImageName(imageUUID);
		userServiceImpl.updateUser(user);
		return "redirect:/book/";
	}
}
