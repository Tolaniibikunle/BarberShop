package com.ardmore.quarters.gentlemens.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.ardmore.quarters.gentlemens.entity.Appointment;
import com.ardmore.quarters.gentlemens.service.AppointmentServiceImpl;
import com.ardmore.quarters.gentlemens.util.AppointmentUtil;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

@RunWith(MockitoJUnitRunner.class)
public class AppointmentControllerTest {

  @Mock
  private AppointmentServiceImpl appointmentService;

  @InjectMocks
  private AppointmentController appointmentController;

  @Test
  public void testGetAppointmentById() {
    Appointment actual = AppointmentUtil.getAppointment();

    when(appointmentService.getAppointmentById(1)).thenReturn(actual);

    Appointment expected = AppointmentUtil.getAppointment();

    assertThat(expected).isEqualToComparingFieldByField(appointmentController.getAppointmentById(1).getBody());
  }

  @Test
  public void testGetAllAppointments() {
    List<Appointment> actual = AppointmentUtil.getAppointments();

    when(appointmentService.getAllAppointments()).thenReturn(actual);

    List<Appointment> expected = new ArrayList<>();
    expected.add(AppointmentUtil.getAppointment());

    assertThat(expected).isEqualTo(appointmentController.getAllAppointments().getBody());
  }

  @Test
  public void testAddAppointmentValid() {
    when(appointmentService.addAppointment(any())).thenReturn(true);

    HttpStatus expected = HttpStatus.CREATED;

    assertThat(expected).isEqualByComparingTo(appointmentController.addAppointment(AppointmentUtil.getAppointment()).getStatusCode());
  }

  @Test
  public void testAddAppointmentInvalid() {
    when(appointmentService.addAppointment(any())).thenReturn(false);

    HttpStatus expected = HttpStatus.CONFLICT;

    assertThat(expected).isEqualByComparingTo(appointmentController.addAppointment(AppointmentUtil.getAppointment()).getStatusCode());
  }

  @Test
  public void testUpdateAppointment() {
    Appointment expected = AppointmentUtil.getAppointment();

    assertThat(expected).isEqualToComparingFieldByField(appointmentController.updateAppointment(AppointmentUtil.getAppointment()).getBody());
  }

  @Test
  public void testDeleteAppointment() {
    HttpStatus expected = HttpStatus.NO_CONTENT;

    assertThat(expected).isEqualByComparingTo(appointmentController.deleteAppointment(1).getStatusCode());
  }

}
