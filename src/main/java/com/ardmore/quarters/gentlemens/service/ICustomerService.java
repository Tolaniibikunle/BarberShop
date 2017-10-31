package com.ardmore.quarters.gentlemens.service;

import java.util.List;

import com.ardmore.quarters.gentlemens.entity.Customer;

public interface ICustomerService {
List<Customer> getAllCustomers();
Customer getCustomerById(int customerId);
boolean addCustomer(Customer customer);
void updateCustomer(Customer customer);
void deleteCustomer(int customerId);
}
