package com.ardmore.quarters.gentlemens.entity;

import javax.persistence.*;

@Entity(name = "appointment")
@Table(name="appointment")
public class Appointment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="appointment_id")
	private int appointmentId;
	
//	@Column(name="employee_id")
	@OneToOne
	private Employee employeeId;
	
//	@Column(name="customer_id")
	@OneToOne
	private Customer customerId;
	
	@Column(name="app_time")
	private String time;
	
	@Column(name="app_date")
	private String date;
	
	@Column(name="price")
	private double price;
	
	@Column(name="status")
	private String status;

	public Appointment(int appointmentId, Employee employeeId, Customer customerId, String time, String date,
			double price, String status) {
		
		this.appointmentId = appointmentId;
		this.employeeId = employeeId;
		this.customerId = customerId;
		this.time = time;
		this.date = date;
		this.price = price;
		this.status = status;
	}

	public int getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}

	public Employee getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Employee employeeId) {
		this.employeeId = employeeId;
	}

	public Customer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Customer customerId) {
		this.customerId = customerId;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
	
	

}
