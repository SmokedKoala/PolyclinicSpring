package ru.polyclinic.polyclinicspring.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.polyclinic.polyclinicspring.entities.User;

public interface UserRepository extends CrudRepository<User, Integer> {
  User findByName(String name);
}
