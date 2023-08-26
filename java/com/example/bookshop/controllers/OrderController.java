package com.example.bookshop.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.example.bookshop.dtos.BookDTO;
import com.example.bookshop.dtos.OrderDTO;
import com.example.bookshop.models.Book;
import com.example.bookshop.models.Order;
import com.example.bookshop.models.User;
import com.example.bookshop.serviceImpl.BookServiceImpl;
import com.example.bookshop.serviceImpl.OrderServiceImpl;
import com.example.bookshop.serviceImpl.UserServiceImpl;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	
	@Autowired
	BookServiceImpl bookServiceImpl;

	@Autowired
	OrderServiceImpl orderServiceImpl;
	
	@Autowired
	UserServiceImpl userServiceImpl;
	
	@GetMapping("/")
	public String getAllOrders(Model model, @SessionAttribute(required = false) User user) {
		if (user != null && user.getRole().equals("admin")) {
			List<Order> orders = orderServiceImpl.getAllOrders();
			List<OrderDTO> orderDTOs = new ArrayList<OrderDTO>();
			for (Order order : orders) {
				OrderDTO orderDTO = new OrderDTO();
				orderDTO.setId(order.getId());
				orderDTO.setUserGmail(order.getUser().getGmail());
				orderDTO.setBookTitle(order.getBook().getTitle());
				orderDTO.setQuantity(order.getQuantity());
				orderDTO.setTotalCost(order.getBook().getPrice() * order.getQuantity());
				orderDTO.setUserFullname(order.getUser().getFirstName() + " " + order.getUser().getLastName());
				orderDTOs.add(orderDTO);
			}
			
			model.addAttribute("orderDTOs", orderDTOs);
			return "orderList";
		} else {
			return "redirect:/login";
		}
	}
	
	
	@GetMapping("/myorder")
	public String getMyOrders(Model model, @SessionAttribute(required = false) User user) {
		if(user != null) {
			List<Order> orders = orderServiceImpl.getAllByUser(user);
			List<OrderDTO> orderDTOs = new ArrayList<OrderDTO>();
			for (Order order : orders) {
				OrderDTO orderDTO = new OrderDTO();
				orderDTO.setId(order.getId());
				orderDTO.setUserGmail(order.getUser().getGmail());
				orderDTO.setBookTitle(order.getBook().getTitle());
				orderDTO.setQuantity(order.getQuantity());
				orderDTO.setTotalCost(order.getBook().getPrice() * order.getQuantity());
				orderDTO.setUserFullname(order.getUser().getFirstName() + " " + order.getUser().getLastName());
				orderDTOs.add(orderDTO);
			}
			model.addAttribute("orderDTOs", orderDTOs);
			return "orderList";
		} else {
			model.addAttribute("error", "Vui lòng đăng nhập để xem thông tin giỏ hàng của bạn");
			return "loginForm";
		}
	}
	
	@PostMapping("/addorder/{idBook}")
	public String addOrder(Model model, @RequestParam("quantity") int quantity, @PathVariable Long idBook, @SessionAttribute(required = false) User user) {
		if(user != null) {
			Book book = bookServiceImpl.getBookById(idBook).get();
			if (user.getBalance() - quantity * book.getPrice() >= 0) {
				Order order = new Order();
				order.setUser(user);
				order.setQuantity(quantity);
				order.setBook(book);
				book.setQuantitySold(book.getQuantitySold() + quantity);
				user.setBalance(user.getBalance() - quantity * book.getPrice());
				orderServiceImpl.updateOrder(order);
				return "redirect:/book/";
			} else {
				BookDTO bookDTO = new BookDTO();
				bookDTO.setId(book.getId());
				bookDTO.setTitle(book.getTitle());
				bookDTO.setAuthor(book.getAuthor());
				bookDTO.setDescription(book.getDescription());
				bookDTO.setPrice(book.getPrice());
				bookDTO.setImageName(book.getImageName());
				model.addAttribute("bookDTO", bookDTO);
				model.addAttribute("error", "số dư không đủ, vui lòng nạp thêm tiền vào tài khoản");
				return "bookDetail";
			}
		} else {
			model.addAttribute("error", "Vui lòng đăng nhập để có thể mua sách");
			return "loginForm";
		}
	}
}
