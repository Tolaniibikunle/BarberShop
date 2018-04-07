package com.ardmore.quarters.gentlemens.controller;

import com.ardmore.quarters.gentlemens.config.Swaggerrize;
import com.ardmore.quarters.gentlemens.entity.Employee;
import com.ardmore.quarters.gentlemens.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Swaggerrize
@RestController
public class EmployeeController {

@Autowired
private EmployeeServiceImpl employeeService;


@GetMapping("/employees")
public ResponseEntity<Iterable<Employee>> getAllEmployees(){
	Iterable<Employee> employeeList = employeeService.getAllEmployees();
	return new ResponseEntity<>(employeeList,HttpStatus.OK);
}


@GetMapping("/employee/{id}")
public ResponseEntity<Employee> getEmployeeByID(@PathVariable("id") Integer id){
	Employee employee = employeeService.getEmployeeById(id);
	return new ResponseEntity<>(employee,HttpStatus.OK);
}


@PostMapping(path = "/createEmployee")
public ResponseEntity<Void> addEmployee(@RequestBody Employee employee){
	boolean newEmployee = employeeService.addEmployee(employee);
	if(!newEmployee){
		return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
	}
	return new ResponseEntity<>(HttpStatus.CREATED);
}


@PutMapping("/employee")
public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee){
	employeeService.updateEmployee(employee);
	return new ResponseEntity<>(employee, HttpStatus.OK);
}

@DeleteMapping("employee/{id}")
public ResponseEntity<Void> deleteEmployee(@PathVariable("id") Integer id){
	employeeService.deleteEmployee(id);
	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}

}
