package com.ardmore.quarters.gentlemens.service;

import com.ardmore.quarters.gentlemens.dao.ICustomerDAO;
import com.ardmore.quarters.gentlemens.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements ICustomerService{

	@Autowired
	private ICustomerDAO customerDAO;
	@Override
	public Iterable<Customer> getAllCustomers() {
		return customerDAO.findAll();
	}

	@Override
	public Customer getCustomerById(int customerId) {
		Customer customer = customerDAO.findOne(customerId);
		return customer;
	}

	@Override
	public boolean addCustomer(Customer customer) {
		boolean customerInDB = customerDAO.findAllByFirstNameAndLastNameAndPhoneNumber(customer.getFirstName(),customer.getFirstName(),customer.getPhoneNumber());
		if(customerInDB){
			return false;
		} else{
			customerDAO.save(customer);
			return true;
		}
	}

	@Override
	public void updateCustomer(Customer customer) {
		customerDAO.save(customer);

	}

	@Override
	public void deleteCustomer(int customerId) {
	customerDAO.delete(customerId);

	}

}
