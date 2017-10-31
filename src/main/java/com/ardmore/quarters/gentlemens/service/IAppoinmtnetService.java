package com.ardmore.quarters.gentlemens.service;

import java.util.List;

import com.ardmore.quarters.gentlemens.entity.Appointment;

public interface IAppoinmtnetService {
	List<Appointment> getAllAppointments();
	Appointment getAppoinmentById(int appointmentId);
	boolean createAppointment(Appointment appointment);
	void updateAppointment(Appointment appointment);
	void deleteAppointment(int appointmentId);
}
