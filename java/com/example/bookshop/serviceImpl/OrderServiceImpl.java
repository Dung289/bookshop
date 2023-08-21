package com.example.bookshop.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bookshop.models.Order;
import com.example.bookshop.models.User;
import com.example.bookshop.repositories.OrderRepository;
import com.example.bookshop.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	OrderRepository orderRepository;
	
	@Override
	public List<Order> getAllOrders() {
		// TODO Auto-generated method stub
		return orderRepository.findAll();
	}

	@Override
	public List<Order> getAllByUser(User user) {
		// TODO Auto-generated method stub
		return orderRepository.findAllByUser(user);
	}

	@Override
	public Optional<Order> getOrderById(Long id) {
		// TODO Auto-generated method stub
		return orderRepository.findById(id);
	}

	@Override
	public void updateOrder(Order order) {
		orderRepository.save(order);
		
	}

	@Override
	public void deleteOrderById(Long id) {
		orderRepository.deleteById(id);
		
	}

}
