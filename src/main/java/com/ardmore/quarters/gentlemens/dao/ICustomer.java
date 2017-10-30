package com.ardmore.quarters.gentlemens.dao;

import java.util.List;

import com.ardmore.quarters.gentlemens.entity.Customer;

public interface ICustomer {

	List<Customer> getAllCustomers();
	Customer getCusomterById(int CustomerId);
	void addCustomer(Customer customer);
	void updateCustomer(Customer customer);
	void deleteCustomer(int CustomerId);
	boolean customerExists();
	
}
