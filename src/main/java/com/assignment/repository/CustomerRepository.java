package com.assignment.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.assignment.domain.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String>{
	
	public Page<Customer> findByFirstNameContainsOrLastNameContainsOrEmailIdContainsAllIgnoreCase
		(String firstName, String lastName, String emailId, Pageable page);

}
