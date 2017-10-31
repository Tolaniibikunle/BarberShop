package com.ardmore.quarters.gentlemens.dao;

import java.util.List;

import com.ardmore.quarters.gentlemens.entity.Appointment;
import com.ardmore.quarters.gentlemens.entity.Customer;
import com.ardmore.quarters.gentlemens.entity.Employee;

public interface IAppointmentDAO {
	List<Appointment> getAllAppointments();
	Appointment getAppointmentById(int appointmentId);
	void addAppointment(Appointment appointment);
	void updateAppointment(Appointment appointment);
	void deteleAppointment(int appointmentId);
	boolean appointmentExists(Employee employeeId, Customer customerId, String time, String date,
			double price, String status);

}
