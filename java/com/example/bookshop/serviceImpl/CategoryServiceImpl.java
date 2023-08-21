package com.example.bookshop.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bookshop.models.Category;
import com.example.bookshop.repositories.CategoryRepository;
import com.example.bookshop.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	CategoryRepository categoryRepository;
	
	@Override
	public List<Category> getAllCategories() {
		// TODO Auto-generated method stub
		return categoryRepository.findAll();
	}

	@Override
	public void deleteCategoryById(int id) {
		categoryRepository.deleteById(id);
		
	}

	@Override
	public void updateCategory(Category category) {
		categoryRepository.save(category);
		
	}

	@Override
	public Optional<Category> getCategoryById(int id) {
		// TODO Auto-generated method stub
		return categoryRepository.findById(id);
	}

}
