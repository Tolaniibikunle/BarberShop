package com.ardmore.quarters.gentlemens.service;

import com.ardmore.quarters.gentlemens.dao.IEmployeeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ardmore.quarters.gentlemens.entity.Employee;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private IEmployeeDAO employeeDAO;

	@Override
	public Iterable<Employee> getAllEmployees() {
		return employeeDAO.findAll();
	}

	@Override
	public Employee getEmployeeById(int employeeId) {
		return employeeDAO.findOne(employeeId);
	}

	@Override
	public boolean addEmployee(Employee employee) {
		boolean employeeInDB = employeeDAO.findAllByFirstNameAndLastNameAndPhoneNumber(employee.getFirstName(), employee.getLastName(), employee.getPhoneNumber());
		if(employeeInDB){
			return false;
		} else{
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
		employeeDAO.delete(employeeId);

	}

}
