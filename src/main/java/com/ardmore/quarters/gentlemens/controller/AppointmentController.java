package com.ardmore.quarters.gentlemens.controller;

import com.ardmore.quarters.gentlemens.entity.Appointment;
import com.ardmore.quarters.gentlemens.service.AppointmentServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AppointmentController {

	@Autowired
	private AppointmentServiceImpl appointmentService;

	private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentController.class);


	@GetMapping("/appointment/{id}")
	public ResponseEntity<Appointment> getAppointmentById(@PathVariable("id") Integer id){
		Appointment appointment = appointmentService.getAppointmentById(id);
		return new ResponseEntity<>(appointment, HttpStatus.OK);
	}

	@GetMapping("/appointments")
	public ResponseEntity<Iterable<Appointment>> getAllAppointments(){
		Iterable<Appointment> appointmentList = appointmentService.getAllAppointments();
		return new ResponseEntity<>(appointmentList,HttpStatus.OK);
	}

	@PostMapping("/appointment")
	public ResponseEntity<Void> addAppointment(@RequestBody Appointment appointment){
		LOGGER.info("Add Appointment: {}", appointment);
		boolean newAppointment = appointmentService.addAppointment(appointment);
		if(!newAppointment){
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PutMapping("/appointment")
	public ResponseEntity<Appointment> updateAppointment(@RequestBody Appointment appointment){
		appointmentService.updateAppointment(appointment);
		return new ResponseEntity<>(appointment,HttpStatus.OK);
	}

	@DeleteMapping("/appointment/{id{")
	public ResponseEntity<Void> deleteAppointment(@PathVariable("id") Integer id){
		appointmentService.deleteAppointment(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
//
}
