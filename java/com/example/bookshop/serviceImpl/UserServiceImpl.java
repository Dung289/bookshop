package com.example.bookshop.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bookshop.models.User;
import com.example.bookshop.repositories.UserRepository;
import com.example.bookshop.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserRepository userRepository;

	@Override
	public void updateUser(User user) {
		userRepository.save(user);
		
	}

	
	@Override
	public Optional<User> getUserById(Long id) {
		return userRepository.findById(id);
	}

	
	@Override
	public Optional<User> getUserByGmailAndPassWord(String gmail, String password) {
		return userRepository.findUserByGmailAndPassword(gmail,password);
	}


	@Override
	public Optional<User> getUserByGmail(String gmail) {
		// TODO Auto-generated method stub
		return userRepository.findByGmail(gmail);
	}
	
}
