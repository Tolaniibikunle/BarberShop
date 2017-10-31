package com.ardmore.quarters.gentlemens.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ardmore.quarters.gentlemens.entity.Appointment;
import com.ardmore.quarters.gentlemens.service.AppointmentServiceImpl;

@RestController
@RequestMapping("createAppointment")
public class AppointmentController {
	
	@Autowired
	private AppointmentServiceImpl appointmentService;
	
	
	@GetMapping("appointment/{id}")
	public ResponseEntity<Appointment> getAppointmentById(@PathVariable("id") Integer id){
		Appointment appointment = appointmentService.getAppoinmentById(id);
		return new ResponseEntity<Appointment>(appointment, HttpStatus.OK);
	}
	
	@GetMapping("appointments")
	public ResponseEntity<List<Appointment>> getAllAppointments(){
		List<Appointment> appointmentList = appointmentService.getAllAppointments();
		return new ResponseEntity<List<Appointment>>(appointmentList,HttpStatus.OK);
	}
	
	@PostMapping("appointment")
	public ResponseEntity<Void> addAppointment(@RequestBody Appointment appointment, UriComponentsBuilder builder){
		boolean newAppointment = appointmentService.addAppointment(appointment);
		if(newAppointment == false){
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/appointment/{id}").buildAndExpand(appointment.getAppointmentId()).toUri());
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@PutMapping("appointment")
	public ResponseEntity<Appointment> updateAppointment(@RequestBody Appointment appointment){
		appointmentService.updateAppointment(appointment);
		return new ResponseEntity<Appointment>(appointment,HttpStatus.OK);
	}
	
	@DeleteMapping("appointment/{id{")
	public ResponseEntity<Void> deleteAppointment(@PathVariable("id") Integer id){
		appointmentService.deleteAppointment(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
