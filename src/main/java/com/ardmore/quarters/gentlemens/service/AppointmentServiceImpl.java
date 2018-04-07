package com.ardmore.quarters.gentlemens.service;

import java.util.List;

import com.ardmore.quarters.gentlemens.dao.IAppointmentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ardmore.quarters.gentlemens.entity.Appointment;

@Service
public class AppointmentServiceImpl implements IAppoinmtnetService {

	@Autowired
    private IAppointmentDAO appointmentDAO;

	@Override
	public Iterable<Appointment> getAllAppointments() {
		return appointmentDAO.findAll();
	}

//	@Override
//	public Appointment getAppoinmentById(int appointmentId) {
//		Appointment appointment =appointmentDAO.getAppointmentById(appointmentId);
//		return appointment;
//	}

	@Override
	public boolean addAppointment(Appointment appointment) {
//		boolean appointmentInDB = appointmentDAO.appointmentExists( appointment.getEmployeeId(), appointment.getCustomerId(), appointment.getTime(),
//				appointment.getDate(), appointment.getPrice(), appointment.getStatus());
//		if(appointmentInDB){
//			return false;
//		}else{
//			appointmentDAO.addAppointment(appointment);
//			return true;
//		}
        return appointment == appointmentDAO.save(appointment);
	}

//	@Override
//	public void updateAppointment(Appointment appointment) {
//		appointmentDAO.updateAppointment(appointment);
//
//	}
//
//	@Override
//	public void deleteAppointment(int appointmentId) {
//		appointmentDAO.deteleAppointment(appointmentId);
//
//	}

}
