package com.ardmore.quarters.gentlemens.controller;

import com.ardmore.quarters.gentlemens.config.LoggerConsts;
import com.ardmore.quarters.gentlemens.config.swagger.Swaggerize;
import com.ardmore.quarters.gentlemens.entity.Employee;
import com.ardmore.quarters.gentlemens.service.EmployeeServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Swaggerize
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeService;

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    @GetMapping("/getAllEmployees")
    public ResponseEntity<Iterable<Employee>> getAllEmployees() {
        LOGGER.info(LoggerConsts.GET_ALL_EMPLOYEES_REQUEST);
        Iterable<Employee> employeeList = employeeService.getAllEmployees();
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    @GetMapping("/getEmployee/{id}")
    public ResponseEntity<Employee> getEmployeeByID(@PathVariable("id") Integer id) {
        LOGGER.info(LoggerConsts.GET_EMPLOYEE_REQUEST, id);
        Employee employee = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping(path = "/create")
    public ResponseEntity<Void> addEmployee(@RequestBody Employee employee) {
        LOGGER.info(LoggerConsts.NEW_EMPLOYEE_REQUEST, employee);
        boolean newEmployee = employeeService.addEmployee(employee);
        if (!newEmployee) {
            LOGGER.error(LoggerConsts.EMPLOYEE_ALREADY_EXISTS);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
        LOGGER.info(LoggerConsts.UPDATE_EMPLOTEE_REQUEST, employee);
        employeeService.updateEmployee(employee);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("id") Integer id) {
        LOGGER.info(LoggerConsts.DELETE_EMPLOYEE_REQUEST, id);
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
