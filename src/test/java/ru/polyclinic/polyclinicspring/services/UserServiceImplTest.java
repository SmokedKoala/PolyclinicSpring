package ru.polyclinic.polyclinicspring.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.polyclinic.polyclinicspring.entities.User;
import ru.polyclinic.polyclinicspring.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class UserServiceImplTest {

  @Autowired
  private UserService userService;

  @MockBean
  private UserRepository userRepository;

  @Test
  void findAllUsers() {
    User user1 = new User();
    User user2 = new User();
    User user3 = new User();
    List<User> usersToBeFound = new ArrayList<>();

    user1.setName("Ivanov Ivan Ivanovich");
    user1.setEmail("ivanov@mail.ru");
    user1.setRole(0);
    user1.setPassword("qwerty");

    user2.setName("Petrov Petr Petrovich");
    user2.setEmail("petrov@mail.ru");
    user2.setRole(1);
    user2.setPassword("123");
    user2.setSpeciality("Therapist");

    user3.setName("Sidorov Sergey Sergeevich");
    user3.setEmail("sidorov@mail.ru");
    user3.setRole(1);
    user3.setPassword("11111");
    user3.setSpeciality("Surgeon");

    userService.saveUser(user1);
    userService.saveUser(user2);
    userService.saveUser(user3);

    usersToBeFound.add(user1);
    usersToBeFound.add(user2);
    usersToBeFound.add(user3);

    Mockito.doReturn(usersToBeFound)
        .when(userRepository)
        .findAll();

    assertEquals(usersToBeFound, userService.findAllUsers());
  }

  @Test
  void findAllPatients() {
    User user1 = new User();
    User user2 = new User();
    User user3 = new User();
    List<User> usersToBeFound = new ArrayList<>();

    user1.setName("Ivanov Ivan Ivanovich");
    user1.setEmail("ivanov@mail.ru");
    user1.setRole(0);
    user1.setPassword("qwerty");

    user2.setName("Petrov Petr Petrovich");
    user2.setEmail("petrov@mail.ru");
    user2.setRole(1);
    user2.setPassword("123");
    user2.setSpeciality("Therapist");

    user3.setName("Sidorov Sergey Sergeevich");
    user3.setEmail("sidorov@mail.ru");
    user3.setRole(1);
    user3.setPassword("11111");
    user3.setSpeciality("Surgeon");

    userService.saveUser(user1);
    userService.saveUser(user2);
    userService.saveUser(user3);

    usersToBeFound.add(user1);

    Mockito.doReturn(usersToBeFound)
        .when(userRepository)
        .findAll();

    assertEquals(usersToBeFound, userService.findAllPatients());
  }

  @Test
  void findAllDoctors() {
    User user1 = new User();
    User user2 = new User();
    User user3 = new User();
    List<User> usersToBeFound = new ArrayList<>();

    user1.setName("Ivanov Ivan Ivanovich");
    user1.setEmail("ivanov@mail.ru");
    user1.setRole(0);
    user1.setPassword("qwerty");

    user2.setName("Petrov Petr Petrovich");
    user2.setEmail("petrov@mail.ru");
    user2.setRole(1);
    user2.setPassword("123");
    user2.setSpeciality("Therapist");

    user3.setName("Sidorov Sergey Sergeevich");
    user3.setEmail("sidorov@mail.ru");
    user3.setRole(1);
    user3.setPassword("11111");
    user3.setSpeciality("Surgeon");

    userService.saveUser(user1);
    userService.saveUser(user2);
    userService.saveUser(user3);

    usersToBeFound.add(user2);
    usersToBeFound.add(user3);

    Mockito.doReturn(usersToBeFound)
        .when(userRepository)
        .findAll();

    assertEquals(usersToBeFound, userService.findAllDoctors());
  }

  @Test
  void findBySpeciality() {
    User user1 = new User();
    User user2 = new User();
    User user3 = new User();
    List<User> usersToBeFound = new ArrayList<>();

    user1.setName("Ivanov Ivan Ivanovich");
    user1.setEmail("ivanov@mail.ru");
    user1.setRole(0);
    user1.setPassword("qwerty");

    user2.setName("Petrov Petr Petrovich");
    user2.setEmail("petrov@mail.ru");
    user2.setRole(1);
    user2.setPassword("123");
    user2.setSpeciality("Therapist");

    user3.setName("Sidorov Sergey Sergeevich");
    user3.setEmail("sidorov@mail.ru");
    user3.setRole(1);
    user3.setPassword("11111");
    user3.setSpeciality("Surgeon");

    userService.saveUser(user1);
    userService.saveUser(user2);
    userService.saveUser(user3);

    usersToBeFound.add(user2);

    Mockito.doReturn(usersToBeFound)
        .when(userRepository)
        .findAll();

    assertEquals(usersToBeFound, userService.findBySpeciality("Therapist"));
  }

  @Test
  void findByName() {
    User user = new User();
    user.setName("Ivanov Ivan Ivanovich");
    user.setEmail("ivanov@mail.ru");
    user.setRole(0);
    user.setPassword("qwerty");

    userService.saveUser(user);

    Mockito.doReturn(user)
        .when(userRepository)
        .findByName(user.getName());
  }

  @Test
  void saveUser() {
    User user = new User();
    user.setName("Ivanov Ivan Ivanovich");
    user.setEmail("ivanov@mail.ru");
    user.setRole(0);
    user.setPassword("qwerty");
    userService.saveUser(user);

    assertEquals("Ivanov Ivan Ivanovich", user.getName());
    Mockito.verify(userRepository, Mockito.times(1)).save(user);
  }

  @Test
  void deleteUser() {
    User user = new User();
    user.setName("Ivanov Ivan Ivanovich");
    user.setEmail("ivanov@mail.ru");
    user.setRole(0);
    user.setPassword("qwerty");
    user.setId(1);

    userService.saveUser(user);
    userService.deleteUser(1);

    verify(userRepository, times(1)).deleteById(1);
  }

  // TODO написать тест к updateUser
  @Test
  void updateUser() {
  }
}