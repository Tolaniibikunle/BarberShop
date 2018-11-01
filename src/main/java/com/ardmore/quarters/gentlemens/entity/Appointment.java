package com.ardmore.quarters.gentlemens.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity(name = "appointment")
@Table(name = "appointment")
public class Appointment {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "appointment_id")
  private int appointmentId;

  @Column(name = "employee_id")
  private int employeeId;

  @Column(name = "customer_id")
  private int customerId;

  @Column(name = "app_time")
  private String time;

  @Column(name = "app_date")
  private String date;

  @Column(name = "price")
  private double price;

  @Column(name = "status")
  private String status;

  @Override
  public String toString() {
    return "Appointment{" +
        "appointmentId=" + appointmentId +
        ", employeeId=" + employeeId +
        ", customerId=" + customerId +
        ", time='" + time + '\'' +
        ", date='" + date + '\'' +
        ", price=" + price +
        ", status='" + status + '\'' +
        '}';
  }
}
