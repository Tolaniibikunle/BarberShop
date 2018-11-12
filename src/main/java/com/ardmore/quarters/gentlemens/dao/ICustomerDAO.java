package com.ardmore.quarters.gentlemens.dao;

import com.ardmore.quarters.gentlemens.entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface ICustomerDAO extends CrudRepository<Customer, Integer> {
    boolean findAllByFirstNameAndLastNameAndPhoneNumber(
            String firstName, String lastName, String phoneNumber);

    Customer findByCustomerId(int customerId);

    void deleteByCustomerId(int customerId);
}
