package ru.polyclinic.polyclinicspring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.polyclinic.polyclinicspring.entities.Patient;
import ru.polyclinic.polyclinicspring.repositories.PatientRepository;

@Controller
public class PatientController {

  @Autowired
  private PatientRepository patientRepository;

  @GetMapping(path = "/")
  public String getMainPage(){
    return "index";
  }

  @PostMapping(path="/add")
  public @ResponseBody
  String addNewUser (@RequestParam String name
      , @RequestParam String email) {
    Patient n = new Patient();
    n.setName(name);
    n.setEmail(email);
    patientRepository.save(n);
    return "Saved";
  }

  @GetMapping(path="/all")
  public @ResponseBody Iterable<Patient> getAllUsers() {
    return patientRepository.findAll();
  }
}
