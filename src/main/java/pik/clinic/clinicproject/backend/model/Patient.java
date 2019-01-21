package pik.clinic.clinicproject.backend.model;

import com.vaadin.flow.component.JsonSerializable;
import elemental.json.JsonObject;
import pik.clinic.clinicproject.backend.Role;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Patient implements JsonSerializable {

    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String email;
    @NotNull
    private String password;
    private String firstName;
    private String lastName;
    private String pesel;
    private LocalDate birthDate;
    private String address;
    private String phoneNumber;
    private String role;




    @OneToMany(mappedBy = "patient")
    private List<Visit> visits;


    public Patient() {
    }

    public Patient(@NotNull String email, @NotNull String password, String firstName, String lastName, String pesel, LocalDate birthDate, String address, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
        this.birthDate = birthDate;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.role = Role.PATIENT;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Visit> getVisits() {
        return visits;
    }

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
    }

    public String getDateBirth(){
        return String.valueOf(birthDate);
    }
    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    @Override
    public JsonObject toJson() {
        return null;
    }

    @Override
    public JsonSerializable readJson(JsonObject jsonObject) {
        return null;
    }

    public String getFirstName(Patient patient) {
        return firstName;
    }
}
