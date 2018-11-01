package com.ardmore.quarters.gentlemens.util;

import com.ardmore.quarters.gentlemens.entity.Appointment;
import java.util.ArrayList;
import java.util.List;

public class AppointmentUtil {

  public static Appointment getAppointment() {
    Appointment appointment = new Appointment();
    appointment.setAppointmentId(1);
    appointment.setCustomerId(1);
    appointment.setDate("01/01/0001");
    appointment.setEmployeeId(1);
    appointment.setPrice(20.00);
    appointment.setStatus("active");
    appointment.setTime("00:00");
    return appointment;
  }

  public static List<Appointment> getAppointments() {
    List<Appointment> appointmentList = new ArrayList<>();
    appointmentList.add(getAppointment());
    return appointmentList;
  }

}
