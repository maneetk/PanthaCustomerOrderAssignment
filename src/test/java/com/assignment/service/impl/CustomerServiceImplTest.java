package com.assignment.service.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import com.assignment.domain.Customer;
import com.assignment.repository.CustomerRepository;

public class CustomerServiceImplTest {

	private CustomerServiceImpl serviceImpl;

	@Before
	public void setUp() throws Exception {
		serviceImpl = new CustomerServiceImpl();
	}

	@Test
	public void shouldAddCustomer() throws Exception{
		Customer customer = mock(Customer.class);
		when(customer.getEmailId()).thenReturn("email");
		CustomerRepository customerRepository = mock(CustomerRepository.class);
		serviceImpl.setCustomerRepository(customerRepository);
		Customer customer2 = mock(Customer.class);
		when(customerRepository.save(customer)).thenReturn(customer2);
		assertEquals(customer2, serviceImpl.addCustomer(customer));
		
		
	}

}
