package com.ardmore.quarters.gentlemens.service;

import com.ardmore.quarters.gentlemens.entity.Appointment;

public interface IAppointmentService {

  Iterable<Appointment> getAllAppointments();

  Appointment getAppointmentById(int appointmentId);

  boolean addAppointment(Appointment appointment);

  void updateAppointment(Appointment appointment);

  void deleteAppointment(int appointmentId);
}
