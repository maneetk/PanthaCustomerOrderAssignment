package com.assignment.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.assignment.domain.Customer;

public interface CustomerService {
	
	public Customer fetchCustomerData(String customerId) throws Exception;
	
	public Customer addCustomer(Customer customer) throws Exception;
	
	public Customer updateCustomerData(String customerId, Customer customer) throws Exception;
	
	public void deleteCustomer(String customerId) throws Exception;

	public Page<Customer> searchCustomerData(String searchParameter, Pageable pageable) throws Exception;
}
