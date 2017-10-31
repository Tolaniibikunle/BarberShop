package com.ardmore.quarters.gentlemens.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ardmore.quarters.gentlemens.entity.Customer;
import com.ardmore.quarters.gentlemens.service.CustomerServiceImpl;

@RestController
@RequestMapping("createCustomer")
public class CustomerController {

	@Autowired
	private CustomerServiceImpl customerService;
	
	@GetMapping("customer/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable("id") Integer id){
		Customer customer = customerService.getCustomerById(id);
		return new ResponseEntity<Customer>(customer,HttpStatus.OK);
	}
	
	@GetMapping("customers")
	public ResponseEntity<List<Customer>> getAllCustomers(){
		List<Customer> customerList = customerService.getAllCustomers();
		return new ResponseEntity<List<Customer>>(customerList,HttpStatus.OK);
	}
	
	@PostMapping("customer")
	public ResponseEntity<Void> addCustomer(@RequestBody Customer customer, UriComponentsBuilder builder){
		boolean newCustomer = customerService.addCustomer(customer);
		if(newCustomer== false){
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/customer/{id}").buildAndExpand(customer.getCustomerId()).toUri());
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@PutMapping("customer")
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer){
		customerService.updateCustomer(customer);
		return new ResponseEntity<Customer>(customer,HttpStatus.OK);
}

	@DeleteMapping("customer/{id}")
	public ResponseEntity<Void> detleCustomer(@PathVariable("id") Integer id){
		customerService.deleteCustomer(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
