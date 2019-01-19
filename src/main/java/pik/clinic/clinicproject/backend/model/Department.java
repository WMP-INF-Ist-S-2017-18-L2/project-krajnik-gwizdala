package pik.clinic.clinicproject.backend.model;

import com.vaadin.flow.component.JsonSerializable;
import elemental.json.JsonObject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Department implements JsonSerializable {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String registrationPhone;
    private String registrationEmail;

    @OneToMany(mappedBy = "department")
    private List<Doctor> doctors;

    public Department() {
    }

    public Department(String name, String registrationPhone, String registrationEmail) {
        this.name = name;
        this.registrationPhone = registrationPhone;
        this.registrationEmail = registrationEmail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegistrationPhone() {
        return registrationPhone;
    }

    public void setRegistrationPhone(String registrationPhone) {
        this.registrationPhone = registrationPhone;
    }

    public String getRegistrationEmail() {
        return registrationEmail;
    }

    public void setRegistrationEmail(String registrationEmail) {
        this.registrationEmail = registrationEmail;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    @Override
    public String toString() {
        return name;

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
