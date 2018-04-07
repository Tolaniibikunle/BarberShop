package com.ardmore.quarters.gentlemens.controller;

import com.ardmore.quarters.gentlemens.config.Swaggerrize;
import com.ardmore.quarters.gentlemens.entity.Customer;
import com.ardmore.quarters.gentlemens.service.CustomerServiceImpl;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Swaggerrize
@RestController
public class CustomerController {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private CustomerServiceImpl customerService;

	@GetMapping("/customer/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable("id") Integer id){
		Customer customer = customerService.getCustomerById(id);
		return new ResponseEntity<>(customer,HttpStatus.OK);
	}

	@GetMapping("/customers")
	public ResponseEntity<Iterable<Customer>> getAllCustomers(){

		Iterable<Customer> customerList = customerService.getAllCustomers();
		return new ResponseEntity<>(customerList,HttpStatus.OK);
	}

	@PostMapping( "/customer")
	public ResponseEntity<Void> addCustomer(@RequestBody Customer customer){
		boolean newCustomer = customerService.addCustomer(customer);
		if(!newCustomer){
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PutMapping("/customer")
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer){
		customerService.updateCustomer(customer);
		return new ResponseEntity<>(customer,HttpStatus.OK);
}

	@DeleteMapping("/customer/{id}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable("id") Integer id){
		customerService.deleteCustomer(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
