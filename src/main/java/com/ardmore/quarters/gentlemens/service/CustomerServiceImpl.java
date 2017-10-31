package com.ardmore.quarters.gentlemens.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ardmore.quarters.gentlemens.dao.CustomerDAOImpl;
import com.ardmore.quarters.gentlemens.entity.Customer;

@Service
public class CustomerServiceImpl implements ICustomerService{

	@Autowired
	private CustomerDAOImpl customerDAO;
	@Override
	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer getCustomerById(int customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean createCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCustomer(int customerId) {
		// TODO Auto-generated method stub
		
	}

}
