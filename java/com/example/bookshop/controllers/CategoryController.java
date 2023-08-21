package com.example.bookshop.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.bookshop.models.Category;
import com.example.bookshop.serviceImpl.CategoryServiceImpl;

@Controller
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	CategoryServiceImpl categoryServiceImpl;
	
	@GetMapping("/")
	public String getAllCategories(Model model) {
		model.addAttribute("categories", categoryServiceImpl.getAllCategories());
		return "categoryTable";
	}
	
	@GetMapping("/{id}")
	public String getCategoryById(Model model, @PathVariable int id) {
		Optional<Category> opCategory = categoryServiceImpl.getCategoryById(id);
		if(opCategory.isPresent()) {
			Category category = opCategory.get();
			model.addAttribute("category", category);
		} else {
			Category category = new Category();
			model.addAttribute("category", category);
		}
		
		return "categoryForm";
	}
	
	@PostMapping("/save/{id}")
	private String addCategory(@ModelAttribute("category") Category category) {
		categoryServiceImpl.updateCategory(category);
		return "redirect:/category/";
	}
	
	@PutMapping("/save/{id}")
	private String editCategoryById(@ModelAttribute("category") Category category) {
		categoryServiceImpl.updateCategory(category);
		return "redirect:/category/";
	}
	
//	@DeleteMapping("/delete/{id}")
//	public String deleteCategory(@PathVariable int id) {
//		categoryServiceImpl.deleteCategoryById(id);
//		return "redirect:/category/";
//	}
}
