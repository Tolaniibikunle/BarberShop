package com.ardmore.quarters.gentlemens.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ardmore.quarters.gentlemens.entity.Appointment;
import com.ardmore.quarters.gentlemens.entity.Customer;
import com.ardmore.quarters.gentlemens.entity.Employee;

@Transactional
@Repository
public class AppointmentDAOImpl implements IAppointmentDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Appointment> getAllAppointments() {
		String hql = "FROM Appointment as appointment ORDER BY appointment.appointmentId ASC";
		return entityManager.createQuery(hql).getResultList();
	}

	@Override
	public Appointment getAppointmentById(int appointmentId) {
		return entityManager.find(Appointment.class, appointmentId);
	}

	@Override
	public void addAppointment(Appointment appointment) {
		entityManager.persist(appointment);

	}

	@Override
	public void updateAppointment(Appointment appointment) {
		Appointment newAppointment = getAppointmentById(appointment.getAppointmentId());
		newAppointment.setCustomerId(appointment.getCustomerId());
		newAppointment.setEmployeeId(appointment.getEmployeeId());
		newAppointment.setDate(appointment.getDate());
		newAppointment.setPrice(appointment.getPrice());
		newAppointment.setStatus(appointment.getStatus());
		newAppointment.setTime(appointment.getTime());
		entityManager.flush();

	}

	@Override
	public void deteleAppointment(int appointmentId) {
		entityManager.remove(getAppointmentById(appointmentId));

	}

	@Override
	public boolean appointmentExists(int appointmentId, Employee employeeId, Customer customerId, String time,
			String date, double price, String status) {
		String hql = "FROM Appointment as appointment WHERE appointment.appointmentId = ? and appointment.employeeId = ? and appointment.customerId = ? "
				+ "and appointment.time = ? and appointment.date = ? and appointment.price = ? and appointment.status = ?";
		int parameters = entityManager.createNamedQuery(hql).setParameter(1, appointmentId).setParameter(2, employeeId).setParameter(3, customerId).
				setParameter(4, time).setParameter(5, date).setParameter(6, price).setParameter(7, status).getResultList().size();
		if(parameters !=0 || parameters>0){
			return true;
		} else {
			return false;
		}
	}

}
