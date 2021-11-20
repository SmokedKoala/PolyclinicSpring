package ru.polyclinic.polyclinicspring.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.polyclinic.polyclinicspring.entities.Doctor;

public interface DoctorRepository extends CrudRepository<Doctor, Integer> {
  Doctor findByName(String name);

}
