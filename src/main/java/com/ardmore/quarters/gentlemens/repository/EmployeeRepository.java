package com.ardmore.quarters.gentlemens.repository;

import com.ardmore.quarters.gentlemens.entity.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

    boolean findAllByFirstNameAndLastNameAndPhoneNumber(
            String firstName, String lastName, String phoneNumber);

    Employee findByEmployeeId(int employeeId);

    void deleteByEmployeeId(int employeeId);
}
