//package com.ardmore.quarters.gentlemens.service;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.ardmore.quarters.gentlemens.entity.Customer;
//
//@Service
//public class CustomerServiceImpl implements ICustomerService{
//
//	@Autowired
//	private CustomerDAOImpl customerDAO;
//	@Override
//	public List<Customer> getAllCustomers() {
//		return customerDAO.getAllCustomers();
//	}
//
//	@Override
//	public Customer getCustomerById(int customerId) {
//		Customer customer = customerDAO.getCusomterById(customerId);
//		return customer;
//	}
//
//	@Override
//	public boolean addCustomer(Customer customer) {
//		boolean customterInDB = customerDAO.customerExists(customer.getCustomerId(),
//				customer.getFirstName(), customer.getLastName(), customer.getPhoneNumber());
//		if(customterInDB){
//			return false;
//		} else{
//			customerDAO.addCustomer(customer);
//			return true;
//		}
//	}
//
//	@Override
//	public void updateCustomer(Customer customer) {
//		customerDAO.updateCustomer(customer);
//
//	}
//
//	@Override
//	public void deleteCustomer(int customerId) {
//	customerDAO.deleteCustomer(customerId);
//
//	}
//
//}
