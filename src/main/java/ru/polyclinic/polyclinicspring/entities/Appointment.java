package ru.polyclinic.polyclinicspring.entities;

import java.sql.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "appointment")
public class Appointment {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(nullable = false)
  private Date time;

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return id;
  }

  @ManyToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "doctor_id")
  private Doctor doctor = new Doctor();

  @ManyToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "patient_id")
  private Patient patient = new Patient();

  public Doctor getDoctor() {
    return doctor;
  }

  public void setDoctor(Doctor doctor) {
    this.doctor = doctor;
  }

  public Patient getPatient() {
    return patient;
  }

  public void setPatient(Patient patient) {
    this.patient = patient;
  }

  public Date getTime() {
    return time;
  }

  public void setTime(Date time) {
    this.time = time;
  }

  public Appointment() {
  }

  public Appointment(Integer id, Date time, Doctor doctor,
      Patient patient) {
    this.id = id;
    this.time = time;
    this.doctor = doctor;
    this.patient = patient;
  }

  @Override
  public String toString() {
    return "Appointment{" +
        "id=" + id +
        ", time=" + time +
        ", doctor=" + doctor +
        ", patient=" + patient +
        '}';
  }
}
