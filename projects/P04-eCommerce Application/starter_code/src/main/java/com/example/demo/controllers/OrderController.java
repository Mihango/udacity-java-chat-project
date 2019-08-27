package com.example.demo.controllers;

import java.util.List;

import com.example.demo.model.persistence.EcommerceUser;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.persistence.UserOrder;
import com.example.demo.model.persistence.repositories.OrderRepository;
import com.example.demo.model.persistence.repositories.UserRepository;

@Log4j2
@RestController
@RequestMapping("/api/order")
public class OrderController {
	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	
	@PostMapping("/submit/{username}")
	public ResponseEntity<UserOrder> submit(@PathVariable String username) {
		log.info("submit new order for user {}", username);
		EcommerceUser ecommerceUser = userRepository.findByUsername(username);
		if(ecommerceUser == null) {
			log.error("user {} not found on submit order", username);
			return ResponseEntity.notFound().build();
		}
		UserOrder order = UserOrder.createFromCart(ecommerceUser.getCart());
		orderRepository.save(order);
		log.error("created new order by {}", username);
		return ResponseEntity.ok(order);
	}
	
	@GetMapping("/history/{username}")
	public ResponseEntity<List<UserOrder>> getOrdersForUser(@PathVariable String username) {
		log.info("user {} order history", username);
		EcommerceUser ecommerceUser = userRepository.findByUsername(username);
		if(ecommerceUser == null) {
			log.error("user {} not found on history request", username);
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(orderRepository.findByEcommerceUser(ecommerceUser));
	}
}
