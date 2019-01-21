package pik.clinic.clinicproject.backend.model;


import com.vaadin.flow.component.JsonSerializable;
import elemental.json.JsonObject;
import pik.clinic.clinicproject.backend.Role;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Doctor implements JsonSerializable {

    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String email;
    @NotNull
    private String password;
    private String firstName;
    private String lastName;
    private String PESEL;
    private LocalDate birthDate;
    private String address;
    private String phoneNumber;
    private String specialization;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    private String role;

    @OneToMany
    private List<Visit> visits;



    public Doctor() {
    }

    public Doctor(@NotNull String email, @NotNull String password, String firstName, String lastName, String PESEL, LocalDate birthDate,
                  String address, String phoneNumber,
                  String specialization, Department department) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.PESEL = PESEL;
        this.birthDate = birthDate;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.specialization = specialization;
        this.department = department;
        this.role = Role.DOCTOR;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
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

    public String getPESEL() {
        return PESEL;
    }

    public void setPESEL(String PESEL) {
        this.PESEL = PESEL;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Visit> getVisits() {
        return visits;
    }

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getDepart() {
        return String.valueOf(department);

    }
    public String getDateBirth() {
        return String.valueOf(birthDate);
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    public String returnFullNameWithDepart() { return firstName + " " + lastName + " " + getDepart(); }

    @Override
    public JsonObject toJson() {
        return null;
    }

    @Override
    public JsonSerializable readJson(JsonObject jsonObject) {
        return null;
    }

//    public String getDepartmentName() { return department.getName(); }

}
