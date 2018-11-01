package com.ardmore.quarters.gentlemens.controller;

import com.ardmore.quarters.gentlemens.config.LoggerConsts;
import com.ardmore.quarters.gentlemens.config.Swaggerrize;
import com.ardmore.quarters.gentlemens.entity.Appointment;
import com.ardmore.quarters.gentlemens.service.AppointmentServiceImpl;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Swaggerrize
@RestController
@RequestMapping("/appointment")
public class AppointmentController {

  @Autowired private AppointmentServiceImpl appointmentService;

  private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentController.class);

  @GetMapping("/getAppointment/{id}")
  public ResponseEntity<Appointment> getAppointmentById(@PathVariable("id") Integer id) {
    LOGGER.info(LoggerConsts.GET_APPOINTMENT_REQUEST, id);
    Appointment appointment = appointmentService.getAppointmentById(id);
    return new ResponseEntity<>(appointment, HttpStatus.OK);
  }

  @GetMapping("/getAllAppointments")
  public ResponseEntity<List<Appointment>> getAllAppointments() {
    LOGGER.info(LoggerConsts.GET_ALL_APPOINTMENTS_REQUEST);
    List<Appointment> appointmentList = appointmentService.getAllAppointments();
    return new ResponseEntity<>(appointmentList, HttpStatus.OK);
  }

  @PostMapping("/create")
  public ResponseEntity<Void> addAppointment(@RequestBody Appointment appointment) {
    LOGGER.info(LoggerConsts.NEW_APPOINTMENT_REQUEST, appointment);
    boolean newAppointment = appointmentService.addAppointment(appointment);
    if (!newAppointment) {
      LOGGER.error(LoggerConsts.APPOINTMENT_ALREADY_EXISTS);
      return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @PutMapping("/update")
  public ResponseEntity<Appointment> updateAppointment(@RequestBody Appointment appointment) {
    LOGGER.info(LoggerConsts.UPDATE_APPOINTMENT_REQUEST);
    appointmentService.updateAppointment(appointment);
    return new ResponseEntity<>(appointment, HttpStatus.OK);
  }

  @DeleteMapping("/delete/{id} ")
  public ResponseEntity<Void> deleteAppointment(@PathVariable("id") Integer id) {
    LOGGER.info(LoggerConsts.DELETE_APPOINTMENT_REQUEST);
    appointmentService.deleteAppointment(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
  //
}
