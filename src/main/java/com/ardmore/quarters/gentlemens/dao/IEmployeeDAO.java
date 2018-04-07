package com.ardmore.quarters.gentlemens.dao;

import java.util.List;


import com.ardmore.quarters.gentlemens.entity.Employee;
import org.springframework.data.repository.CrudRepository;

public interface IEmployeeDAO extends CrudRepository<Employee,Integer>{

}
