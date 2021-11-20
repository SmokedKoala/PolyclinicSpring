package ru.polyclinic.polyclinicspring.entities;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "doctor")
public class Doctor {

  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  private Integer id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String email;

  @Column(nullable = true)
  private String speciality;

  @ManyToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "department_id")
  private Department department = new Department();

  @OneToMany(targetEntity=Appointment.class, mappedBy = "doctor", cascade = CascadeType.ALL)
  private List<Appointment> appointmentList;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getSpeciality() {
    return speciality;
  }

  public void setSpeciality(String speciality) {
    this.speciality = speciality;
  }

  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }

  public List<Appointment> getAppointmentList() {
    return appointmentList;
  }

  public void setAppointmentList(
      List<Appointment> appointmentList) {
    this.appointmentList = appointmentList;
  }

  public Doctor() {
  }

  public Doctor(Integer id, String name, String password, String email, String speciality) {
    this.id = id;
    this.name = name;
    this.password = password;
    this.email = email;
    this.speciality = speciality;
  }

  @Override
  public String toString() {
    return "Doctor{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", password='" + password + '\'' +
        ", email='" + email + '\'' +
        ", speciality='" + speciality + '\'' +
        '}';
  }
}

