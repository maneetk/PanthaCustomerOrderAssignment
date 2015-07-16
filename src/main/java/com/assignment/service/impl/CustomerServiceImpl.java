package com.assignment.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.assignment.domain.Customer;
import com.assignment.repository.CustomerRepository;
import com.assignment.service.CustomerService;
import com.assignment.service.OrderService;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	private CustomerRepository customerRepository;
	
	private OrderService orderService;
	
	@Autowired
	public void setCustomerRepository(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	@Autowired
	public void setOrderService(OrderService orderService){
		this.orderService = orderService;
	}
	
	/**
	 * 
	 * @param customerId
	 * @return
	 * @throws Exception
	 * 
	 * This method fetches customer data based on the customer id
	 */
	@Override
	public Customer fetchCustomerData(String customerId) throws Exception {
		
		try{
			return customerRepository.findOne(customerId);
		}
		catch(Exception e){
			throw new Exception("Excpetion in fetching customer details for the "
					+ "customer with id: " + 
					customerId + " -> " + e.getMessage());
		}
	}

	/**
	 * 
	 * @param customer
	 * @return
	 * @throws Exception
	 * 
	 * This method adds customer with the data provided.
	 * No customer can be created without a customer id and it throws an exception in that case
	 */
	@Override
	public Customer addCustomer(Customer customer) throws Exception {
		
		try{
			if(customer.getEmailId()==null){
				throw new Exception("Cannot add customer without emailId" );
			}
			customer = customerRepository.save(customer);
			return customer;
		}
		catch(Exception e){
			throw new Exception("Exception in adding customer ->" + e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.assignment.customer.CustomerService#updateCustomerData(com.assignment.customer.Customer)
	 * 
	 * This updates the data provided in the new object.
	 * The PUT requests update the fields which are present. 
	 * The fields not supplied will be replaced with null. We can change this by checking for each field
	 * and setting them individually.
	 */
	@Override
	public Customer updateCustomerData(String customerId, Customer customer) throws Exception {
		
		try{
			Customer existingCustomer = customerRepository.findOne(customerId);
			if(existingCustomer == null){
				throw new Exception("No customer found with customer id: " + customerId);
			}
			else{
				if(customer.getEmailId()==null){
					throw new Exception("Cannot update customer with customer id: + " + customerId + 
							" without emailId" );
				}
				customer.setId(customerId);
				existingCustomer = customerRepository.save(customer);
				return existingCustomer;
			}
		}
		catch(Exception e){
			throw new Exception("Excpetion in updating customer data for the "
					+ "customer with id: " + 
					customerId + " -> " + e.getMessage());
		}
		
	}

	
	/**
	 * 
	 * @param customerId
	 * @throws Exception
	 * 
	 * Deletes a customer if exists
	 */
	@Override
	public void deleteCustomer(String customerId) throws Exception {
		
		try{
			Customer existingCustomer = customerRepository.findOne(customerId);
			if(existingCustomer == null){
				throw new Exception("No customer found with customer id: " + customerId);
			}
			else{
				customerRepository.delete(existingCustomer);
			}

		}
		catch(Exception e){
			throw new Exception("Excpetion in deleting customer with id: " + 
					customerId + " -> " + e.getMessage());
		}
	}

	/**
	 * 
	 * @param searchParameter
	 * @return
	 * @throws Exception
	 * 
	 * This method returns the list of customers matching the search criteria.
	 * It also finds orders placed by this customer on the basis of the customer id 
	 */
	@Override
	public Page<Customer> searchCustomerData(String searchParameter, Pageable pageable) throws Exception {
		try{
			Page<Customer> customerPage = 
					customerRepository.
						findByFirstNameContainsOrLastNameContainsOrEmailIdContainsAllIgnoreCase
						(searchParameter, searchParameter, searchParameter, pageable);
			for(Customer customer: customerPage){
				customer.setOrderList(orderService.searchOrders(customer.getId()));
			}
			return customerPage;
		}
		catch(Exception e){
			throw new Exception("Exception in searching customer data for the "
					+ "parameter: " + 
					searchParameter + "-> " + e.getMessage());
		}
	}

}
