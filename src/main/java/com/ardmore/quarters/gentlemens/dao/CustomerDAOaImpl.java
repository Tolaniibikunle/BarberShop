package com.ardmore.quarters.gentlemens.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ardmore.quarters.gentlemens.entity.Customer;

@Transactional
@Repository
public class CustomerDAOaImpl implements ICustomerDAO {

	@PersistenceContext
	private EntityManager entityManager;
	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> getAllCustomers() {
		String hql = "FROM Customer as customer ORDER BY customer.customerId ASC";
		return entityManager.createQuery(hql).getResultList();
	}

	@Override
	public Customer getCusomterById(int customerId) {
		return entityManager.find(Customer.class, customerId);
	}

	@Override
	public void addCustomer(Customer customer) {
		entityManager.persist(customer);
		
	}

	@Override
	public void updateCustomer(Customer customer) {
		Customer newCustomer = getCusomterById(customer.getCustomerId());
		newCustomer.setFirstName(customer.getFirstName());
		newCustomer.setLastName(customer.getLastName());
		newCustomer.setPhoneNumber(customer.getPhoneNumber());
		entityManager.flush();
		
	}

	@Override
	public void deleteCustomer(int customerId) {
		entityManager.remove(getCusomterById(customerId));
		
	}

	@Override
	public boolean customerExists(int customerId, String firstName, String lastName, String phoneNumber) {
		String hql = "FROM Customer as customer WHERE customer.customerId = ? and customer.firstName = ? and customer.lastName = ? and customer.phoneNumber?";
		int parameters = entityManager.createQuery(hql).setParameter(1, customerId).setParameter(2, firstName).setParameter(3, lastName).setParameter(4, phoneNumber).getResultList().size();
	if(parameters !=0 || parameters >0){
		return true;
	} else{
		return false;
	}
	}

}
