package ru.polyclinic.polyclinicspring.services;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import ru.polyclinic.polyclinicspring.entities.Appointment;
import ru.polyclinic.polyclinicspring.entities.Doctor;
import ru.polyclinic.polyclinicspring.entities.Patient;
import ru.polyclinic.polyclinicspring.repositories.AppointmentRepository;

public class AppointmentServiceImpl implements AppointmentService{

  @Autowired
  AppointmentRepository appointmentRepository;

  @Override
  public List<Appointment> getAllForDoctor(Doctor doctor) {
    List<Appointment> appointments = (List<Appointment>) appointmentRepository.findAll();
    return appointments.stream().filter(i -> i.getDoctor().equals(doctor)).collect(Collectors.toList());
  }

  @Override
  public List<Appointment> getAllForPatient(Patient patient) {
    List<Appointment> appointments = (List<Appointment>) appointmentRepository.findAll();
    return appointments.stream().filter(i -> i.getPatient().equals(patient)).collect(Collectors.toList());
  }
}