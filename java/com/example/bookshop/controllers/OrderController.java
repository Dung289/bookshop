package com.example.bookshop.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.bookshop.dtos.OrderDTO;
import com.example.bookshop.models.Order;
import com.example.bookshop.models.User;
import com.example.bookshop.serviceImpl.OrderServiceImpl;
import com.example.bookshop.serviceImpl.UserServiceImpl;

@Controller
public class OrderController {
	
	private UserController userController;
	
	
	public OrderController(UserController userController) {
		this.userController = userController;
	}


	@Autowired
	OrderServiceImpl orderServiceImpl;
	
	@Autowired
	UserServiceImpl userServiceImpl;
	
	@GetMapping("/order")
	public String getAllOrders(Model model) {
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
	}
	
	
	@GetMapping("/myorder")
	public String getMyOrders(Model model) {
		User user = userServiceImpl.getUserById(userController.getUserId()).get();
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
	}
}
