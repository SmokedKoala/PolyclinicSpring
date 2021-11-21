package ru.polyclinic.polyclinicspring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
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
  private PatientRepository patientRepository;

  @Autowired
  DoctorService doctorService;

  @GetMapping(path = "/")
  public String getMainPage(Model model){
    model.addAttribute("departments", departmentRepository.findAll());
    return "index";
  }

  @GetMapping("department/{department}")
  public ModelAndView showDepartmentDoctors(@PathVariable("department") int department){
    ModelAndView mv = new ModelAndView("department");
    mv.addObject("doctors", doctorService.findBySpeciality(department));
//    model.addAttribute("doctors",doctorService.findBySpeciality(department));
//    return "department";
    return mv;
  }

  @GetMapping("signup")
  public String signup(Model model) {
    model.addAttribute("patient", new Patient());
    return "signup";
  }

  @PostMapping("signup")
  public String signUp(@ModelAttribute("patient") Patient user) {
    patientRepository.save(user);
    return "redirect:/";
  }

  @GetMapping("login")
  public String login() {
    return "login";
  }

}
