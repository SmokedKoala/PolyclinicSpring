package ru.polyclinic.polyclinicspring.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.polyclinic.polyclinicspring.entities.Appointment;

public interface AppointmentRepository extends CrudRepository<Appointment, Integer> {

  public Iterable<Appointment> findAppointmentsByDoctorIdAndPatientNotNull(int id);
  public Iterable<Appointment> findAppointmentsByPatientId(int id);
}
