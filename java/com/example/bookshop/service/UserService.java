package com.example.bookshop.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.bookshop.models.User;

@Service
public interface UserService {
	void updateUser(User user);
	Optional<User> getUserById(Long id);
	Optional<User> getUserByGmailAndPassWord(String gmail, String password);
	Optional<User> getUserByGmail(String gmail);
}
