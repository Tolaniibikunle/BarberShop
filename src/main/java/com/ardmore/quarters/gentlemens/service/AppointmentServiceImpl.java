package com.ardmore.quarters.gentlemens.service;

import java.util.List;

import com.ardmore.quarters.gentlemens.dao.IAppointmentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ardmore.quarters.gentlemens.entity.Appointment;

@Service
public class AppointmentServiceImpl implements IAppointmentService {

	@Autowired
    private IAppointmentDAO appointmentDAO;

	@Override
	public Iterable<Appointment> getAllAppointments() {
		return appointmentDAO.findAll();
	}

	@Override
	public Appointment getAppointmentById(int appointmentId) {
		Appointment appointment =appointmentDAO.findOne(appointmentId);
		return appointment;
	}

	@Override
	public boolean addAppointment(Appointment appointment) {
		boolean appointmentInDB = appointmentDAO.appointmentExists(appointment);
		if(appointmentInDB){
			return false;
		}else{
			appointmentDAO.save(appointment);
			return true;
		}
	}

	@Override
	public void updateAppointment(Appointment appointment) {
		appointmentDAO.save(appointment);

	}

	@Override
	public void deleteAppointment(int appointmentId) {
		appointmentDAO.delete(appointmentId);

	}

}
