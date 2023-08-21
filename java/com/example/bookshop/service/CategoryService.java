package com.example.bookshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.bookshop.models.Category;

@Service
public interface CategoryService {
	List<Category> getAllCategories();
	Optional<Category> getCategoryById(int id);
	void deleteCategoryById(int id);
	void updateCategory(Category category);
}
