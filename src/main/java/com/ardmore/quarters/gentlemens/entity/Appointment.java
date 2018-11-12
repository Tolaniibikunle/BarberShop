package com.ardmore.quarters.gentlemens.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "appointment")
@Table(name = "appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
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
