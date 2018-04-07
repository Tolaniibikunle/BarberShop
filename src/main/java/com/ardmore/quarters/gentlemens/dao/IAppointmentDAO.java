package com.ardmore.quarters.gentlemens.dao;

import java.util.List;

import com.ardmore.quarters.gentlemens.entity.Appointment;
import com.ardmore.quarters.gentlemens.entity.Customer;
import com.ardmore.quarters.gentlemens.entity.Employee;
import org.springframework.data.repository.CrudRepository;

public interface IAppointmentDAO extends CrudRepository<Appointment,Integer>{
}
