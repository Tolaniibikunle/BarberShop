package com.ardmore.quarters.gentlemens.dao;

import java.util.List;

import com.ardmore.quarters.gentlemens.entity.Customer;

public interface ICustomerDAO {

	List<Customer> getAllCustomers();
	Customer getCusomterById(int customerId);
	void addCustomer(Customer customer);
	void updateCustomer(Customer customer);
	void deleteCustomer(int customerId);
	boolean customerExists(int customerId, String firstName, String lastName, String phoneNumber);
	
}
