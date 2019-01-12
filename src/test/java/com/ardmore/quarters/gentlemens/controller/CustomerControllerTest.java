package com.ardmore.quarters.gentlemens.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.ardmore.quarters.gentlemens.entity.Customer;
import com.ardmore.quarters.gentlemens.service.CustomerServiceImpl;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {

  @Mock
  private CustomerServiceImpl customerService;

  @InjectMocks
  private CustomerController customerController;

  @Test
  public void testGetCustomerById() {
    Customer actual = getCustomer();

    when(customerService.getCustomerById(1)).thenReturn(actual);

    Customer expected = getCustomer();

    assertThat(expected).isEqualToComparingFieldByField(customerController.getCustomerById(1).getBody());
  }

  @Test
  public void testGetAllCustomers() {
    when(customerService.getAllCustomers()).thenReturn(getCustomerList());

    List<Customer> expected = getCustomerList();

    assertThat(expected).isEqualTo(customerController.getAllCustomers().getBody());
  }

  @Test
  public void testCreateCustomerValid() {
    when(customerService.addCustomer(any())).thenReturn(true);

    HttpStatus expected = HttpStatus.CREATED;

    assertThat(expected).isEqualByComparingTo(customerController.addCustomer(getCustomer()).getStatusCode());
  }

  @Test
  public void testCreateCutomerInvalid() {
    when(customerService.addCustomer(any())).thenReturn(false);

    HttpStatus expected = HttpStatus.CONFLICT;

    assertThat(expected).isEqualByComparingTo(customerController.addCustomer(getCustomer()).getStatusCode());
  }

  @Test
  public void testUpdateCustomer() {
    Customer expected = getCustomer();

    assertThat(expected).isEqualToComparingFieldByField(customerController.updateCustomer(getCustomer()).getBody());
  }

  @Test
  public void testDeleteCustomer() {
    HttpStatus expected = HttpStatus.NO_CONTENT;

    assertThat(expected).isEqualByComparingTo(customerController.deleteCustomer(1).getStatusCode());
  }

  private Customer getCustomer() {
    Customer customer = new Customer();
    customer.setCustomerId(1);
    customer.setFirstName("Test");
    customer.setLastName("Tester");
    customer.setPhoneNumber("1111111111");
    return customer;
  }

  private List<Customer> getCustomerList() {
    List<Customer> customerList = new ArrayList<>();
    customerList.add(getCustomer());
    return customerList;
  }

}
