package com.ardmore.quarters.gentlemens.service;

import com.ardmore.quarters.gentlemens.dao.IAppointmentDAO;
import com.ardmore.quarters.gentlemens.entity.Appointment;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		Appointment appointment = appointmentDAO.findOne(appointmentId);
		return appointment;
	}

	@Override
	public boolean addAppointment(Appointment appointment) {
		Iterable<Appointment> appointments = appointmentDAO.findAllByDateAndTimeAndEmployeeId(appointment.getDate(),appointment.getTime(),appointment.getEmployeeId());
		if(!IterableUtils.isEmpty(appointments)){
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
