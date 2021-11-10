package ru.polyclinic.polyclinicspring.services;

import java.util.List;
import ru.polyclinic.polyclinicspring.entities.User;


public interface UserService {

  public List<User> findAllUsers();

  public List<User> findAllPatients();

  public List<User> findAllDoctors();

  public List<User> findBySpeciality(String speciality);

  public User findByName(String name);

  public void saveUser(User user);

  public void deleteUser(Integer userId);

  public void updateUser(User user);

//  TODO список записей и работа с ними
}
