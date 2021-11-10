package ru.polyclinic.polyclinicspring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.polyclinic.polyclinicspring.entities.User;
import ru.polyclinic.polyclinicspring.repositories.UserRepository;

@Controller
public class PatientController {

  @Autowired
  private UserRepository patientRepository;

  @GetMapping(path = "/")
  public String getMainPage(){
    return "index";
  }

  @PostMapping(path="/add")
  public @ResponseBody
  String addNewUser (@RequestParam String name
      , @RequestParam String email) {
    User n = new User();
    n.setName(name);
    n.setEmail(email);
    patientRepository.save(n);
    return "Saved";
  }

  @GetMapping(path="/all")
  public @ResponseBody Iterable<User> getAllUsers() {
    return patientRepository.findAll();
  }
}
