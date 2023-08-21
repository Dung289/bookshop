package com.example.bookshop.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bookshop.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findUserByGmailAndPassword(String gmail, String password);
	Optional<User> findByGmail(String gmail);
}
