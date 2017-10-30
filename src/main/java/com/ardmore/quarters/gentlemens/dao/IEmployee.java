package com.ardmore.quarters.gentlemens.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ardmore.quarters.gentlemens.entity.Employee;

public interface IEmployee {

	List<Employee> getAllEmployees();
	Employee getEmployeeById(int employeeId);
	void addEmployee(Employee employee);
	void updateEmployee(Employee employee);
	void deleteEmployee(int employeeId);
	boolean employeeExists(int employeeId, String firstName, String lastName, String phoneNumber, String email, String address,
			String age, String gender, String experience);
}
