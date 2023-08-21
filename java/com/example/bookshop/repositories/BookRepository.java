package com.example.bookshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bookshop.models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
}
