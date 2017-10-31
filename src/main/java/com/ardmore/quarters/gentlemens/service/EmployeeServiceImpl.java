package com.ardmore.quarters.gentlemens.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ardmore.quarters.gentlemens.dao.EmployeeDAOImpl;
import com.ardmore.quarters.gentlemens.entity.Employee;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
	
	@Autowired
	private EmployeeDAOImpl employeeDAO;

	@Override
	public List<Employee> getAllEmployees() {
		return employeeDAO.getAllEmployees();
	}

	@Override
	public Employee getEmployeeById(int employeeId) {
		Employee employee = employeeDAO.getEmployeeById(employeeId);
		return employee;
	}

	/*
	 * (non-Javadoc)
	 * @see com.ardmore.quarters.gentlemens.service.IEmployeeService#createEmployee(com.ardmore.quarters.gentlemens.entity.Employee)
	 * look at this again 
	 */
	@Override
	public boolean addEmployee(Employee employee) {
		boolean employeeInDB = employeeDAO.employeeExists(employee.getFirstName(), employee.getLastName(), 
				employee.getPhoneNumber(), employee.getEmail(), employee.getAddress(), employee.getAge(), employee.getGender(), employee.getExperience());
				
		if(employeeInDB){
			return false;
		} else{
			employeeDAO.addEmployee(employee);
			return true;
		}
		
	}

	@Override
	public void updateEmployee(Employee employee) {
		employeeDAO.updateEmployee(employee);
		
	}

	@Override
	public void deleteEmployee(int employeeId) {
		employeeDAO.deleteEmployee(employeeId);
		
	}

}
