package ru.polyclinic.polyclinicspring.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.polyclinic.polyclinicspring.entities.Doctor;
import ru.polyclinic.polyclinicspring.entities.Patient;
import ru.polyclinic.polyclinicspring.repositories.DepartmentRepository;
import ru.polyclinic.polyclinicspring.repositories.DoctorRepository;
import ru.polyclinic.polyclinicspring.repositories.PatientRepository;
import ru.polyclinic.polyclinicspring.services.DoctorService;

@Controller
public class MainPageController {

  @Autowired
  private DepartmentRepository departmentRepository;

  @Autowired
  private DoctorRepository doctorRepository;

  @Autowired
  DoctorService doctorService;

  @GetMapping(path = "/")
  public String getMainPage(Model model){
    model.addAttribute("departments", departmentRepository.findAll());
    return "index";
  }

  @GetMapping("/{department}")
  public String showDepartmentDoctors(@PathVariable("department") int department, Model model){
    model.addAttribute("doctors",doctorService.findBySpeciality(department));
    return "department";
  }

//  @PostMapping(path="/add")
//  public @ResponseBody
//  String addNewUser (@RequestParam String name
//      , @RequestParam String email) {
//    Patient n = new Patient();
//    n.setName(name);
//    n.setEmail(email);
//    patientRepository.save(n);
//    return "Saved";
//  }
//
//  @GetMapping(path="/all")
//  public @ResponseBody Iterable<Patient> getAllUsers() {
//    return patientRepository.findAll();
//  }

}
