package com.assignment.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.domain.Order;
import com.assignment.service.OrderService;

@RestController
@RequestMapping("/api/order")
public class OrderController{

	
	private OrderService orderService;
	
	@Autowired
	public void setOrderService(OrderService orderService){
		this.orderService = orderService;
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.GET)
	public Order fetchOrderData(@PathVariable("id") String orderId) throws Exception {
		return orderService.fetchOrderData(orderId);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Order addOrder(@RequestBody Order order) throws Exception {
		return orderService.addOrder(order);
	}

	@RequestMapping(value="{id}", method=RequestMethod.PUT)
	public Order updateOrderData(@PathVariable("id") String orderId, 
			@RequestBody Order order) throws Exception {
		return orderService.updateOrderData(orderId, order);
		
	}

	@RequestMapping(value="{id}", method=RequestMethod.DELETE)
	public void deleteOrder(@PathVariable("id") String orderId) throws Exception {
		orderService.deleteOrder(orderId);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Order> getAllOrders() throws Exception{
		return orderService.getAllOrders();
		
	}
}
