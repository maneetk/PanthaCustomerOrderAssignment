package com.assignment.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.domain.Order;
import com.assignment.repository.OrderRepository;
import com.assignment.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	private OrderRepository orderRepository;
	
	@Autowired
	public void setOrderRepository(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}
	
	
	/**
	 * @param orderId
	 * @return
	 * @throws Exception
	 * This method fetches order data based on the order id
	 */
	@Override
	public Order fetchOrderData(String orderId) throws Exception {
		try{
			return orderRepository.findOne(orderId);
		}
		catch(Exception e){
			throw new Exception("Exception in fetching order data for the order id : " + 
					orderId + " :" + e.getMessage());
		}
	}

	/**
	 * @param order
	 * @return
	 * @throws Exception
	 * This method adds order with the data provided.
	 * No order can be created without a customer id and an order item
	 *  it throws an exception in that case
	 */
	@Override
	public Order addOrder(Order order) throws Exception{
		try{
			if(order.getOrderItem()==null){
				throw new Exception("Cannot update the order without an order item");
			}
			if(order.getCustomerId()==null){
				throw new Exception("Cannot update the order for the order item : + " + 
					order.getOrderItem()  + "without a customer id");
			}
			return orderRepository.save(order);
		}
		catch(Exception e){
			throw new Exception("Exception in adding data for the order item : " + 
					order.getOrderItem() + " -> " + e.getMessage());
		}
		
	}

	
	/**
	 * @param orderId, order
	 * @return
	 * @throws Exception
	 * This method updates the order with the data provided.
	 * No order can be updated without a customer id and an order item
	 *  it throws an exception in that case
	 */
	@Override
	public Order updateOrderData(String orderId, Order order) throws Exception {
		try{
			Order existingOrder = orderRepository.findOne(orderId);
			if(existingOrder == null){
				throw new Exception("No customer found with order id: " + orderId);
			}
			else{
				if(order.getOrderItem()==null){
					throw new Exception("Cannot update the order without an order item for the order id: "
							+ orderId);
				}
				if(order.getCustomerId()==null){
					throw new Exception("Cannot update the order for the order id : + " + 
						orderId  + "without a customer id");
				}
				order.setId(orderId);
				existingOrder = orderRepository.save(order);
				return existingOrder;
			}
		}
		catch(Exception e){
			throw new Exception("Exception in updating order data for the order id : " + 
					orderId + " :" + e.getMessage());
		}
		
	}

	/**
	 * @param orderId
	 * @throws Exception
	 * This methid deletes the order if exists
	 */
	@Override
	public void deleteOrder(String orderId) throws Exception {
		try{
			Order existingOrder = orderRepository.findOne(orderId);
			if(existingOrder == null){
				throw new Exception("No customer found with order id: " + orderId);
			}
			else{
				orderRepository.delete(existingOrder);
			}
		}
		catch(Exception e){
			throw new Exception("Exception in deleting order data for the order id : " + 
					orderId + " :" + e.getMessage());
		}
		
	}

	/**
	 * @param customerId
	 * @return
	 * @throws Exception
	 * This method searches for the orders associated with the provided customer id
	 */
	@Override
	public List<Order> searchOrders(String customerId) throws Exception {
		try{
			return orderRepository.findByCustomerId(customerId);
		}
		catch(Exception e){
			throw new Exception("Exception in searching order data for the customer id : " + 
					customerId + " :" + e.getMessage());
		}
		
	}

	/**
	 * @return
	 * @throws Exception
	 * This method returns all the orders
	 */
	@Override
	public List<Order> getAllOrders() throws Exception {
		return orderRepository.findAll();
	}

}
