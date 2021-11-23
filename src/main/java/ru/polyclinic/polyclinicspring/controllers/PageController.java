package ru.polyclinic.polyclinicspring.controllers;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;
import javax.print.Doc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.polyclinic.polyclinicspring.entities.Appointment;
import ru.polyclinic.polyclinicspring.entities.Department;
import ru.polyclinic.polyclinicspring.entities.Doctor;
import ru.polyclinic.polyclinicspring.entities.Patient;
import ru.polyclinic.polyclinicspring.entities.User;
import ru.polyclinic.polyclinicspring.repositories.AppointmentRepository;
import ru.polyclinic.polyclinicspring.repositories.DepartmentRepository;
import ru.polyclinic.polyclinicspring.repositories.DoctorRepository;
import ru.polyclinic.polyclinicspring.repositories.PatientRepository;
import ru.polyclinic.polyclinicspring.services.AppointmentService;
import ru.polyclinic.polyclinicspring.services.DoctorService;
import ru.polyclinic.polyclinicspring.services.PatientService;

@Controller
public class PageController {

  @Autowired
  private DepartmentRepository departmentRepository;

  @Autowired
  private DoctorRepository doctorRepository;

  @Autowired
  private PatientRepository patientRepository;

  @Autowired
  private AppointmentRepository appointmentRepository;

  @Autowired
  DoctorService doctorService;

  @Autowired
  PatientService patientService;

  @Autowired
  AppointmentService appointmentService;

  @GetMapping(path = "/")
  public String getMainPage(Model model, Principal principal) {
    model.addAttribute("departments", departmentRepository.findAll());
    if (principal != null) {
      model.addAttribute("login_user", principal.getName());
    }
    return "index";
  }

  @GetMapping("department/{department}")
  public ModelAndView showDepartmentDoctors(@PathVariable("department") int department) {
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

  @GetMapping("profile")
  public String profile(Model model, Principal principal) {
    User user;
    String userEmail = principal.getName();
    user = patientRepository.findByEmail(userEmail);
    if (user == null) {
      user = doctorRepository.findByEmail(userEmail);
      model.addAttribute("usersAppointments",
          appointmentRepository.findAppointmentsByDoctorIdAndPatientNotNull(
              user.getId()));
      model.addAttribute("userStatus", 1);
    } else {
      model.addAttribute("usersAppointments", appointmentRepository.findAppointmentsByPatientId(
          user.getId()));
      model.addAttribute("userStatus", 0);
    }
    model.addAttribute("user", user);
    model.addAttribute("departments", departmentRepository.findAll());

    return "profile";
  }

  @GetMapping("new_appointment")
  public String new_appointment(@RequestParam("department") String department, Model model) {
//    Department department1 = departmentRepository.findByDepartmentName(department);
//    System.out.println(department1);

    List<Appointment> appointmentList = appointmentRepository.findAppointmentsByPatientIsNullAndDoctor_Department_DepartmentName(department);

    model.addAttribute("appointments", appointmentList);
    return "new_appointment";
  }

  @PostMapping("new_appointment")
  public String get_new_appointment(@RequestParam("appointment") int appointment_id, Principal principal) {

    Patient user;
    String userEmail = principal.getName();
    user = patientRepository.findByEmail(userEmail);
    List<Appointment> usersAppointments = user.getAppointmentList();
    Appointment appointment = appointmentRepository.findById(appointment_id).get();
    appointment.setPatient(user);
    usersAppointments.add(appointment);

    patientService.updatePatient(user);
    appointmentService.updateAppointment(appointment);

    return "redirect:profile";
  }


}





