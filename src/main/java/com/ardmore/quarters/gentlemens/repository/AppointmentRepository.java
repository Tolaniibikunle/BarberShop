package com.ardmore.quarters.gentlemens.repository;

import com.ardmore.quarters.gentlemens.entity.Appointment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AppointmentRepository extends CrudRepository<Appointment, Long> {
    List<Appointment> findAllByDateAndTimeAndEmployeeId(String date, String time, int employeeId);

    Appointment findByAppointmentId(int id);

    void deleteByAppointmentId(int id);

    List<Appointment> findAll();
}
