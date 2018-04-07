package com.ardmore.quarters.gentlemens.dao;

import com.ardmore.quarters.gentlemens.entity.Appointment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface IAppointmentDAO extends CrudRepository<Appointment,Integer>{

    @Query(value="Select * from appointment where appointment.date= :appointment_input.date, appointment.time= :appointment_input.time",nativeQuery = true)
    boolean appointmentExists(@Param("appointment_input") Appointment appointment);
}
