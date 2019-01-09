package pik.clinic.clinicproject.backend.model;

import com.vaadin.flow.component.JsonSerializable;
import elemental.json.JsonObject;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Patient extends AbstarctUser implements JsonSerializable {

    @Id
    @GeneratedValue
    private int id;
    private String firstName;
    private String lastName;
    private String password;
    private String pesel;
    private String phoneNumber;
    private String address;
    private String email;
    private LocalDate birthDate;
    private String role;

    @OneToMany(mappedBy = "patient")
    private List<Visit> visits;

    public Patient() {
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public List<Visit> getVisits() {
        return visits;
    }

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;

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
}
