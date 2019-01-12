package com.ardmore.quarters.gentlemens.service;

import com.ardmore.quarters.gentlemens.repository.EmployeeRepository;
import com.ardmore.quarters.gentlemens.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private EmployeeRepository employeeDAO;

    @Override
    public Iterable<Employee> getAllEmployees() {
        return employeeDAO.findAll();
    }

    @Override
    public Employee getEmployeeById(int employeeId) {
        return employeeDAO.findByEmployeeId(employeeId);
    }

    @Override
    public boolean addEmployee(Employee employee) {
        boolean employeeInDB =
                employeeDAO.findAllByFirstNameAndLastNameAndPhoneNumber(
                        employee.getFirstName(), employee.getLastName(), employee.getPhoneNumber());
        if (employeeInDB) {
            return false;
        } else {
            employeeDAO.save(employee);
            return true;
        }
    }

    @Override
    public void updateEmployee(Employee employee) {
        employeeDAO.save(employee);
    }

    @Override
    public void deleteEmployee(int employeeId) {
        employeeDAO.deleteByEmployeeId(employeeId);
    }
}
