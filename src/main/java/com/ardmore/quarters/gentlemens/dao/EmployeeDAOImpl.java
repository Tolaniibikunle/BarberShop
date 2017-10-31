package com.ardmore.quarters.gentlemens.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ardmore.quarters.gentlemens.entity.Employee;

@Transactional
@Repository
public class EmployeeDAOImpl implements IEmployeeDAO {

	@PersistenceContext 
	private EntityManager entityManager;
	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getAllEmployees() {
		String hql = "FROM Employee as employee ORDER BY employee.emoployeId ASC";
		return entityManager.createQuery(hql).getResultList();
	}

	@Override
	public Employee getEmployeeById(int employeeId) {
		return entityManager.find(Employee.class, employeeId);
	}

	@Override
	public void addEmployee(Employee employee) {
		entityManager.persist(employee);
		
	}

	@Override
	public void updateEmployee(Employee employee) {
		Employee newEmployee = getEmployeeById(employee.getEmployeeId());
		newEmployee.setFirstName(employee.getFirstName());
		newEmployee.setLastName(employee.getLastName());
		newEmployee.setPhoneNumber(employee.getPhoneNumber());
		newEmployee.setAddress(employee.getAddress());
		newEmployee.setAge(employee.getAge());
		newEmployee.setEmail(employee.getEmail());
		newEmployee.setGender(employee.getGender());
		newEmployee.setExperience(newEmployee.getExperience());
		//saves everything to db
		entityManager.flush();
		
	}

	@Override
	public void deleteEmployee(int employeeId) {
		entityManager.remove(getEmployeeById(employeeId));
		
	}

	@Override
	public boolean employeeExists(int employeeId, String firstName, String lastName, String phoneNumber, String email,
			String address, String age, String gender, String experience) {
		String hql = "FROM Employee as employee WHERE employee.employeeId = ? and employee.firstName = ? and employee.lastName = ? and employee.phoneNumber = ? "
				+ "and employee.email = ? and employee.address = ? and employee.age = ? and employee.gender = ? and employee.experience ?";
		int parameters = entityManager.createNamedQuery(hql).setParameter(1, employeeId).setParameter(2, firstName).setParameter(3, lastName).setParameter(4, phoneNumber).
				setParameter(5, email).setParameter(6, address).setParameter(7, age).setParameter(8, gender).setParameter(9, experience).getResultList().size();
		if(parameters !=0  || parameters>0){
			return true;
		}else{
			return false;
		}
	}

}
