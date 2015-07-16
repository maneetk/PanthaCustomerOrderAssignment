package com.assignment.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.domain.Customer;
import com.assignment.service.CustomerService;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
	
	private CustomerService customerService;
	
	@Autowired
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.GET)
	public Customer fetchCustomerData(@PathVariable("id") String customerId) throws Exception{
		return customerService.fetchCustomerData(customerId);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Customer addCustomer(@RequestBody Customer customer) throws Exception{
		return customerService.addCustomer(customer);
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.PUT)
	public Customer updateCustomerData(@PathVariable("id") String customerId, 
			@RequestBody Customer customer) throws Exception {
		return customerService.updateCustomerData(customerId, customer);
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.DELETE)
	public void deleteCustomer(@PathVariable("id") String customerId) throws Exception {
		
		customerService.deleteCustomer(customerId);
		
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public Page<Customer> searchCustomerData(
			@RequestParam(value="name", defaultValue="") String searchParameter,
			@RequestParam(value="page", defaultValue="0") String page,
			@RequestParam(value="size", defaultValue="20") String size) throws Exception {
		PageRequest pageRequest = new PageRequest(Integer.parseInt(page), Integer.parseInt(size));
		return customerService.searchCustomerData(searchParameter, pageRequest);
	}
	
}
