package com.ardmore.quarters.gentlemens.controller;

import com.ardmore.quarters.gentlemens.config.Swaggerize;
import com.ardmore.quarters.gentlemens.entity.Appointment;
import com.ardmore.quarters.gentlemens.service.AppointmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/createAppointment")
@Swaggerize
public class AppointmentController {
//
	@Autowired
	private AppointmentServiceImpl appointmentService;
//
//
//	@GetMapping("/appointment/{id}")
//	public ResponseEntity<Appointment> getAppointmentById(@PathVariable("id") Integer id){
//		Appointment appointment = appointmentService.getAppoinmentById(id);
//		return new ResponseEntity<Appointment>(appointment, HttpStatus.OK);
//	}
//
	@GetMapping("/appointments")
	public ResponseEntity<Iterable<Appointment>> getAllAppointments(){
		Iterable<Appointment> appointmentList = appointmentService.getAllAppointments();
		return new ResponseEntity<>(appointmentList,HttpStatus.OK);
	}
//
	@PostMapping("/appointment")
	public ResponseEntity<Void> addAppointment(@RequestBody Appointment appointment, UriComponentsBuilder builder){
		boolean newAppointment = appointmentService.addAppointment(appointment);
		if(newAppointment == false){
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/appointment/{id}").buildAndExpand(appointment.getAppointmentId()).toUri());
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
//
//	@PutMapping("/appointment")
//	public ResponseEntity<Appointment> updateAppointment(@RequestBody Appointment appointment){
//		appointmentService.updateAppointment(appointment);
//		return new ResponseEntity<Appointment>(appointment,HttpStatus.OK);
//	}
//
//	@DeleteMapping("/appointment/{id{")
//	public ResponseEntity<Void> deleteAppointment(@PathVariable("id") Integer id){
//		appointmentService.deleteAppointment(id);
//		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//	}
//
}
