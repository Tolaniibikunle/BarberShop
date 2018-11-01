package com.ardmore.quarters.gentlemens.service;

import com.ardmore.quarters.gentlemens.dao.IAppointmentDAO;
import com.ardmore.quarters.gentlemens.entity.Appointment;
import java.util.List;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentServiceImpl implements IAppointmentService {

  @Autowired private IAppointmentDAO appointmentDAO;

  @Override
  public List<Appointment> getAllAppointments() {
    return appointmentDAO.findAll();
  }

  @Override
  public Appointment getAppointmentById(int appointmentId) {
    Appointment appointment = appointmentDAO.findByAppointmentId(appointmentId);
    return appointment;
  }

  @Override
  public boolean addAppointment(Appointment appointment) {
    List<Appointment> appointments =
        appointmentDAO.findAllByDateAndTimeAndEmployeeId(
            appointment.getDate(), appointment.getTime(), appointment.getEmployeeId());
    if (!IterableUtils.isEmpty(appointments)) {
      return false;
    } else {
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
    appointmentDAO.deleteByAppointmentId(appointmentId);
  }
}
