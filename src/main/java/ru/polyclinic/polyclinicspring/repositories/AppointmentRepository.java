package ru.polyclinic.polyclinicspring.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.polyclinic.polyclinicspring.entities.Appointment;

public interface AppointmentRepository extends CrudRepository<Appointment, Integer> {

}
