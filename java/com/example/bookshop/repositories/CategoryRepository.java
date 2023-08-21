package com.example.bookshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bookshop.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
