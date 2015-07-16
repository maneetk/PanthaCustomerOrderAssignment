package com.assignment.service;

import java.util.List;

import com.assignment.domain.Order;

public interface OrderService {
	
	public Order fetchOrderData(String orderId) throws Exception;
	
	public Order addOrder(Order order)  throws Exception;
	
	public Order updateOrderData(String orderId, Order order) throws Exception;
	
	public void deleteOrder(String orderId) throws Exception;

	public List<Order> searchOrders(String customerId) throws Exception;
	
	public List<Order> getAllOrders() throws Exception;
}
