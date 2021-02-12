package com.homsdev.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homsdev.app.domain.Customer;
import com.homsdev.app.domain.repository.CustomerRepository;
import com.homsdev.app.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	
	public List<Customer> getAllCustomers() {
		return customerRepository.getAllCustomers();
	}

}
