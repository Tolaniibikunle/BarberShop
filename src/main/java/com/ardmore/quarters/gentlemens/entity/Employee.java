package com.ardmore.quarters.gentlemens.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity(name = "employee")
@Table(name = "employee")
public class Employee {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "employee_id")
  private int employeeId;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "phone_number")
  private String phoneNumber;

  @Column(name = "email")
  private String email;

  @Column(name = "address")
  private String address;

  @Column(name = "age")
  private String age;

  @Column(name = "gender")
  private String gender;

  @Column(name = "experience")
  private String experience;

  @Override
  public String toString() {
    return "Employee{" +
        "employeeId=" + employeeId +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", phoneNumber='" + phoneNumber + '\'' +
        ", email='" + email + '\'' +
        ", address='" + address + '\'' +
        ", age='" + age + '\'' +
        ", gender='" + gender + '\'' +
        ", experience='" + experience + '\'' +
        '}';
  }
}
