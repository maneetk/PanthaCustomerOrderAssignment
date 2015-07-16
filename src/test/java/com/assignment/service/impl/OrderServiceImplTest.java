package com.assignment.service.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import com.assignment.domain.Order;
import com.assignment.repository.OrderRepository;

public class OrderServiceImplTest {

	private OrderServiceImpl serviceImpl;

	@Before
	public void setUp() throws Exception {
		serviceImpl = new OrderServiceImpl();
	}

	@Test
	public void shouldAddOrder() throws Exception {
		
		Order orderMock = mock(Order.class);
		when(orderMock.getOrderItem()).thenReturn("OI");
		when(orderMock.getCustomerId()).thenReturn("CI");
		
		OrderRepository orderRepository = mock(OrderRepository.class);
		Order orderMock2 = mock(Order.class);
		when(orderRepository.save(orderMock)).thenReturn(orderMock2);
		serviceImpl.setOrderRepository(orderRepository);
		
		assertEquals(orderMock2, serviceImpl.addOrder(orderMock));
	}
	
	@Test(expected=Exception.class)
	public void shouldNotAddOrderAndThrowOrderItemException() throws Exception{
		Order orderMock = mock(Order.class);
		serviceImpl.addOrder(orderMock);
	}

	@Test(expected=Exception.class)
	public void shouldNotAddOrderAndThrowCustomerIdExcpetion() throws Exception{
		Order orderMock = mock(Order.class);
		when(orderMock.getOrderItem()).thenReturn("OI");
		serviceImpl.addOrder(orderMock);
	}
}
