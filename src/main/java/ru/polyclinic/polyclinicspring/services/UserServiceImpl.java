package ru.polyclinic.polyclinicspring.services;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.polyclinic.polyclinicspring.entities.User;
import ru.polyclinic.polyclinicspring.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  UserRepository userRepository;

  @Override
  public List<User> findAllUsers() {

    return (List<User>) userRepository.findAll();
  }

  @Override
  public List<User> findAllPatients() {
    List<User> all = (List<User>) userRepository.findAll();
    return all.stream().filter(i-> i.getRole() == 0).collect(Collectors.toList());
  }

  @Override
  public List<User> findAllDoctors() {
    List<User> all = (List<User>) userRepository.findAll();
    return all.stream().filter(i-> i.getRole() == 1).collect(Collectors.toList());
  }

  @Override
  public List<User> findBySpeciality(String speciality) {
    List<User> all = (List<User>) userRepository.findAll();
    return all.stream().filter(i-> i.getSpeciality().equals(speciality)).collect(Collectors.toList());
  }

  @Override
  public User findByName(String name) {
    return userRepository.findByName(name);
  }

  @Override
  public void saveUser(User user) {
  userRepository.save(user);
  }

  @Override
  public void deleteUser(Integer userId) {
    userRepository.deleteById(userId);
  }

  //  TODO реализовать updateUser

  @Override
  public void updateUser(User user) {

  }
}
