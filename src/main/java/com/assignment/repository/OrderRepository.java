package com.assignment.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.assignment.domain.Order;

public interface OrderRepository extends MongoRepository<Order, String> {
	
	public List<Order> findByCustomerId(String customerId);

}
