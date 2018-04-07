package com.ardmore.quarters.gentlemens.dao;

import com.ardmore.quarters.gentlemens.entity.Appointment;
import org.springframework.data.repository.CrudRepository;

public interface IAppointmentDAO extends CrudRepository<Appointment,Integer>{
}
