package com.example.bookshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.bookshop.models.Order;
import com.example.bookshop.models.User;

@Service
public interface OrderService {
	List<Order> getAllOrders();
	List<Order> getAllByUser(User user);
	Optional<Order> getOrderById(Long id);
	void updateOrder(Order order);
	void deleteOrderById(Long id);
}
