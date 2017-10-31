package com.ardmore.quarters.gentlemens.dao;

import java.util.List;


import com.ardmore.quarters.gentlemens.entity.Employee;

public interface IEmployeeDAO {

	List<Employee> getAllEmployees();
	Employee getEmployeeById(int employeeId);
	void addEmployee(Employee employee);
	void updateEmployee(Employee employee);
	void deleteEmployee(int employeeId);
	boolean employeeExists(String firstName, String lastName, String phoneNumber, String email, String address,
			String age, String gender, String experience);
}
