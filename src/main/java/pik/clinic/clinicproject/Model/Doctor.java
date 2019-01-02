package pik.clinic.clinicproject.Model;


import com.vaadin.flow.component.JsonSerializable;
import elemental.json.JsonObject;

import javax.persistence.*;
import java.util.List;

@Entity
public class Doctor implements JsonSerializable {

    @Id
    @GeneratedValue
    private Long id;
    private String login;
    private String firstName;
    private String lastName;
    private String specialization;

    @OneToMany
    private List<Visit> visits;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    public Doctor() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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
