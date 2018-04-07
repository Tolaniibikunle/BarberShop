package com.ardmore.quarters.gentlemens.service;

import java.util.List;

import com.ardmore.quarters.gentlemens.entity.Employee;

public interface IEmployeeService {
List<Employee> getAllEmployees();
Employee getEmployeeById(int employeeId);
boolean addEmployee(Employee employee);
void updateEmployee(Employee employee);
void deleteEmployee(int employeeId);
}
