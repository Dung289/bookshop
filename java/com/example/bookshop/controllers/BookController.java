package com.example.bookshop.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.bookshop.dtos.BookDTO;
import com.example.bookshop.models.Book;
import com.example.bookshop.serviceImpl.BookServiceImpl;
import com.example.bookshop.serviceImpl.CategoryServiceImpl;

@Controller
@RequestMapping("/book")
public class BookController {
	public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/bookImages";
	
	@Autowired
	BookServiceImpl bookServiceImpl;
	
	@Autowired
	CategoryServiceImpl categoryServiceImpl;
	
	@GetMapping("/")
	private String getAllBooks(Model model) {
		model.addAttribute("books", bookServiceImpl.getAllBooks());
//		return "bookTable";
		return "bookList";
	}
	
	@GetMapping("/{id}")
	private String getBookById(Model model, @PathVariable Long id) {
		Optional<Book> opBook = bookServiceImpl.getBookById(id);
		if (opBook.isPresent()) {
			Book book = opBook.get();
			BookDTO bookDTO = new BookDTO();
			bookDTO.setId(book.getId());
			bookDTO.setTitle(book.getTitle());
			bookDTO.setAuthor(book.getAuthor());
			bookDTO.setCategoryId(book.getCategory().getId());
			bookDTO.setDescription(book.getDescription());
			bookDTO.setNumPage(book.getNumPage());
			bookDTO.setPrice(book.getPrice());
			bookDTO.setQuantitySold(book.getQuantitySold());
			bookDTO.setImageName(book.getImageName());
			
			model.addAttribute("bookDTO", bookDTO);
		} else {
			model.addAttribute("bookDTO", new BookDTO());
		}
		model.addAttribute("categories", categoryServiceImpl.getAllCategories());
		model.addAttribute("id", id);
//		return "bookForm";
		return "bookDetail";
	}
	
	@PostMapping("/save/")
	private String addBook(@ModelAttribute("bookDTO") BookDTO bookDTO,
						   @RequestParam("bookImage") MultipartFile fileBookName,
						   @RequestParam("imgName") String imgName) throws IOException {
		Book book = new Book();
		book.setId(bookDTO.getId());
		book.setTitle(bookDTO.getTitle());
		book.setAuthor(bookDTO.getAuthor());
		book.setCategory(categoryServiceImpl.getCategoryById(bookDTO.getCategoryId()).get());
		book.setDescription(bookDTO.getDescription());
		book.setPrice(bookDTO.getPrice());
		book.setNumPage(bookDTO.getNumPage());
		book.setQuantitySold(0);
		String imageUUID;
		if(!fileBookName.isEmpty()) {
			imageUUID=fileBookName.getOriginalFilename();
			Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
			Files.write(fileNameAndPath, fileBookName.getBytes());
		} else {
			imageUUID = imgName;
		}
		book.setImageName(imageUUID);
		bookServiceImpl.updateBook(book);
		categoryServiceImpl.updateCategory(book.getCategory());
		return "redirect:/book/";
		
	}
	
	@PutMapping("/save/{id}")
	private String updateBook(@ModelAttribute("bookDTO") BookDTO bookDTO,
						   @RequestParam("bookImage") MultipartFile fileBookName,
						   @RequestParam("imgName") String imgName) throws IOException {
		Book book = new Book();
		book.setId(bookDTO.getId());
		book.setTitle(bookDTO.getTitle());
		book.setAuthor(bookDTO.getAuthor());
		book.setCategory(categoryServiceImpl.getCategoryById(bookDTO.getCategoryId()).get());
		book.setDescription(bookDTO.getDescription());
		book.setPrice(bookDTO.getPrice());
		book.setNumPage(bookDTO.getNumPage());
		book.setQuantitySold(0);
		String imageUUID;
		if(!fileBookName.isEmpty()) {
			imageUUID=fileBookName.getOriginalFilename();
			Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
			Files.write(fileNameAndPath, fileBookName.getBytes());
		} else {
			imageUUID = imgName;
		}
		book.setImageName(imageUUID);
		bookServiceImpl.updateBook(book);
		categoryServiceImpl.updateCategory(book.getCategory());
		return "redirect:/book/";
		
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteProduct(@PathVariable Long id) {
		bookServiceImpl.deleteBookById(id);
		return "redirect:/book/";
	}
}
