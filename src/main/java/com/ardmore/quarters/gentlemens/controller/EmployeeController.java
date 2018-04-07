//package com.ardmore.quarters.gentlemens.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.util.UriComponentsBuilder;
//
//import com.ardmore.quarters.gentlemens.entity.Employee;
//import com.ardmore.quarters.gentlemens.service.EmployeeServiceImpl;
//
//@RestController
//@RequestMapping("/createEmployee")
//public class EmployeeController {
//
//@Autowired
//private EmployeeServiceImpl employeeService;
////read
//@GetMapping("/employees")
//public ResponseEntity<List<Employee>> getAllEmployees(){
//	List<Employee> employeeList = employeeService.getAllEmployees();
//	return new ResponseEntity<List<Employee>>(employeeList,HttpStatus.OK);
//}
////read
//@GetMapping("/emloyee/{id}")
//public ResponseEntity<Employee> getEmployeeByID(@PathVariable("id") Integer id){
//	Employee employee = employeeService.getEmployeeById(id);
//	return new ResponseEntity<Employee>(employee,HttpStatus.OK);
//}
////create
//@PostMapping("/employee")
//public ResponseEntity<Void> addEmployee(@RequestBody Employee employee, UriComponentsBuilder builder){
//	boolean newEmployee = employeeService.addEmployee(employee);
//	if(newEmployee == false){
//		return new ResponseEntity<Void>(HttpStatus.CONFLICT);
//	}
//	HttpHeaders headers = new HttpHeaders();
//	headers.setLocation(builder.path("/employee/{id}").buildAndExpand(employee.getEmployeeId()).toUri());
//	return new ResponseEntity<Void>(HttpStatus.CREATED);
//}
//
////update
//@PutMapping("/employee")
//public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee){
//	employeeService.updateEmployee(employee);
//	return new ResponseEntity<Employee>(employee, HttpStatus.OK);
//}
//
//@DeleteMapping("employee/{id}")
//public ResponseEntity<Void> deleteEmployee(@PathVariable("id") Integer id){
//	employeeService.deleteEmployee(id);
//	return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//}
//
//}
