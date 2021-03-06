package pik.clinic.clinicproject.backend.model;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Visit {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDate dateOfVisit;
    private String summary;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    public Visit() {
    }

    public Visit(LocalDate dateOfVisit, Patient patient, Doctor doctor, String summary) {
        this.dateOfVisit = dateOfVisit;
        this.patient = patient;
        this.doctor = doctor;
        this.summary = summary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDateOfVisit() {
        return String.valueOf(dateOfVisit);
    }

    public void setDateOfVisit(LocalDate dateOfVisit) {
        this.dateOfVisit = dateOfVisit;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPatient() {
        return String.valueOf(patient);

    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getDoctor() {
        return String.valueOf(doctor);

    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
