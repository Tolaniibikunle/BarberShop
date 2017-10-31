package com.ardmore.quarters.gentlemens.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ardmore.quarters.gentlemens.dao.AppointmentDAOImpl;
import com.ardmore.quarters.gentlemens.entity.Appointment;

public class AppointmentServiceImpl implements IAppoinmtnetService {

	@Autowired
	private AppointmentDAOImpl appointmentDAO;
	@Override
	public List<Appointment> getAllAppointments() {
		return appointmentDAO.getAllAppointments();
	}

	@Override
	public Appointment getAppoinmentById(int appointmentId) {
		Appointment appointment =appointmentDAO.getAppointmentById(appointmentId);
		return appointment;
	}

	@Override
	public boolean addAppointment(Appointment appointment) {
		boolean appointmentInDB = appointmentDAO.appointmentExists( appointment.getEmployeeId(), appointment.getCustomerId(), appointment.getTime(),
				appointment.getDate(), appointment.getPrice(), appointment.getStatus());
		if(appointmentInDB){
			return false;
		}else{
			appointmentDAO.addAppointment(appointment);
			return true;
		}
	}

	@Override
	public void updateAppointment(Appointment appointment) {
		appointmentDAO.updateAppointment(appointment);
		
	}

	@Override
	public void deleteAppointment(int appointmentId) {
		appointmentDAO.deteleAppointment(appointmentId);
		
	}

}
