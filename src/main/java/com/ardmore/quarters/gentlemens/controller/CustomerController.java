package com.ardmore.quarters.gentlemens.controller;

import com.ardmore.quarters.gentlemens.config.LoggerConsts;
import com.ardmore.quarters.gentlemens.config.swagger.Swaggerize;
import com.ardmore.quarters.gentlemens.entity.Customer;
import com.ardmore.quarters.gentlemens.service.CustomerServiceImpl;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Swaggerize
@RestController
public class CustomerController {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerServiceImpl customerService;

    @GetMapping("/getCustomer/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") Integer id) {
        LOGGER.info(LoggerConsts.GET_CUSTOMER_REQUEST, id);
        Customer customer = customerService.getCustomerById(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping("/getAllCustomers")
    public ResponseEntity<Iterable<Customer>> getAllCustomers() {
        LOGGER.info(LoggerConsts.GET_ALL_CUSTOMERS_REQUEST);
        Iterable<Customer> customerList = customerService.getAllCustomers();
        return new ResponseEntity<>(customerList, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Void> addCustomer(@RequestBody Customer customer) {
        LOGGER.info(LoggerConsts.NEW_CUSTOMER_REQUEST, customer);
        boolean newCustomer = customerService.addCustomer(customer);
        if (!newCustomer) {
            LOGGER.error(LoggerConsts.CUSTOMER_ALREADY_EXISTS);
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
        LOGGER.info(LoggerConsts.UPDATE_CUSTOMER_REQUEST, customer);
        customerService.updateCustomer(customer);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("id") Integer id) {
        LOGGER.info(LoggerConsts.DELETE_CUSTOMER_REQUEST, id);
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
