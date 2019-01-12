package com.ardmore.quarters.gentlemens.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.ardmore.quarters.gentlemens.entity.Employee;
import com.ardmore.quarters.gentlemens.service.EmployeeServiceImpl;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTest {

  @Mock
  private EmployeeServiceImpl employeeService;

  @InjectMocks
  private EmployeeController employeeController;

  @Test
  public void testGetEmployeeById() {
    Employee actual = getEmployee();

    when(employeeService.getEmployeeById(1)).thenReturn(actual);

    Employee expected = getEmployee();

    assertThat(expected).isEqualToComparingFieldByField(employeeController.getEmployeeByID(1).getBody());
  }

  @Test
  public void testGetAllEmployees() {
    List<Employee> actual = getEmployeeList();

    when(employeeService.getAllEmployees()).thenReturn(actual);

    List<Employee> expected = new ArrayList<>();
    expected.add(getEmployee());

    assertThat(expected).isEqualTo(employeeController.getAllEmployees().getBody());
  }

  @Test
  public void testCreateEmployeeValid() {
    when(employeeService.addEmployee(any())).thenReturn(true);

    HttpStatus expected = HttpStatus.CREATED;

    assertThat(expected).isEqualByComparingTo(employeeController.addEmployee(getEmployee()).getStatusCode());
  }

  @Test
  public void testCreateEmployeeInvalid() {
    when(employeeService.addEmployee(any())).thenReturn(false);

    HttpStatus expected = HttpStatus.BAD_REQUEST;

    assertThat(employeeController.addEmployee(getEmployee()).getStatusCode()).isEqualByComparingTo(expected);
  }

  @Test
  public void testUpdateEmployee() {
    Employee expected = getEmployee();

    assertThat(expected).isEqualToComparingFieldByField(employeeController.updateEmployee(getEmployee()).getBody());
  }

  @Test
  public void testDeleteEmployee() {
    HttpStatus expected = HttpStatus.NO_CONTENT;

    assertThat(expected).isEqualByComparingTo(employeeController.deleteEmployee(1).getStatusCode());
  }

  private Employee getEmployee() {
    Employee employee = new Employee();
    employee.setAddress("123 Test St.");
    employee.setAge("34");
    employee.setEmail("test@test.com");
    employee.setEmployeeId(1);
    employee.setExperience("2 years");
    employee.setFirstName("Test");
    employee.setGender("male");
    employee.setLastName("Tester");
    employee.setPhoneNumber("11111111111");
    return employee;
  }

  private List<Employee> getEmployeeList() {
    List<Employee> employeeList = new ArrayList<>();
    employeeList.add(getEmployee());
    return employeeList;
  }

}
