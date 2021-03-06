package com.ardmore.quarters.gentlemens.repository;

import com.ardmore.quarters.gentlemens.entity.Appointment;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface AppointmentRepository extends CrudRepository<Appointment, Integer> {
    List<Appointment> findAllByDateAndTimeAndEmployeeId(String date, String time, int employeeId);

    Appointment findByAppointmentId(int id);

    void deleteByAppointmentId(int id);

    List<Appointment> findAll();
}
