package com.ardmore.quarters.gentlemens.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.ardmore.quarters.gentlemens.repository.AppointmentRepository;
import com.ardmore.quarters.gentlemens.entity.Appointment;
import com.ardmore.quarters.gentlemens.util.AppointmentUtil;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AppointmentServiceTest {

  @Mock
  private AppointmentRepository appointmentDAO;

  @InjectMocks
  private AppointmentServiceImpl appointmentService;

  @Test
  public void testGetAppointmentById() {
    Appointment actual = AppointmentUtil.getAppointment();

    when(appointmentDAO.findByAppointmentId(1)).thenReturn(actual);

    Appointment expected = AppointmentUtil.getAppointment();

    assertThat(appointmentService.getAppointmentById(1)).isEqualToComparingFieldByField(expected);
  }

  @Test
  public void testGetAllAppointments() {
    List<Appointment> actual = AppointmentUtil.getAppointments();

    when(appointmentDAO.findAll()).thenReturn(actual);

    List<Appointment> expected = AppointmentUtil.getAppointments();

    assertThat(appointmentService.getAllAppointments()).isEqualTo(expected);
  }

//  @Test
//  public void testAddAppointmentValid() {
//    List<Appointment> appointmentList = new ArrayList<>();
//
//    when(appointmentDAO.findAllByDateAndTimeAndEmployeeId(any(), any(), any())).thenReturn(appointmentList);
//
//    assertThat(appointmentService.addAppointment(AppointmentUtil.getAppointment())).isTrue();
//  }
//
//  @Test
//  public void testAddAppointmentInvalid() {
//    when(appointmentDAO.findAllByDateAndTimeAndEmployeeId(any(), any(), any())).thenReturn(AppointmentUtil.getAppointments());
//
//    assertThat(appointmentService.addAppointment(AppointmentUtil.getAppointment())).isFalse();
//  }

}
