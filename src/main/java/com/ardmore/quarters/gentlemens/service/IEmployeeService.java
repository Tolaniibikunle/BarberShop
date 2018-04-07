package com.ardmore.quarters.gentlemens.service;

import com.ardmore.quarters.gentlemens.entity.Employee;

public interface IEmployeeService {
    Iterable<Employee> getAllEmployees();
    Employee getEmployeeById(int employeeId);
    boolean addEmployee(Employee employee);
    void updateEmployee(Employee employee);
    void deleteEmployee(int employeeId);
}
