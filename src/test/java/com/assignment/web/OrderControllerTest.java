package com.assignment.web;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.assignment.domain.Order;
import com.assignment.service.OrderService;

public class OrderControllerTest {

	private static final String _123 = "123";
	private OrderController orderController;
	private OrderService orderServiceMock;

	@Before
	public void setUp () {
		orderController = new OrderController() ;
		orderServiceMock = Mockito.mock(OrderService.class);
		
	}
	
	@Test
	public void shouldFetchOrderData() throws Exception {
		
		Order order = Mockito.mock(Order.class);
		Mockito.when(orderServiceMock.fetchOrderData(_123)).thenReturn(order) ;
		
		orderController.setOrderService(orderServiceMock);
		Order actualOrder = orderController.fetchOrderData(_123) ;

		assertEquals(order, actualOrder);
		
		
	}

}
