package com.homsdev.app.domain.repository;

import java.util.List;

import com.homsdev.app.domain.Customer;

public interface CustomerRepository {
	List<Customer> getAllCustomers();
	void addNewCustomer(Customer customer);
}
