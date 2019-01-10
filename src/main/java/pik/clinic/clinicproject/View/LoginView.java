package pik.clinic.clinicproject.View;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import pik.clinic.clinicproject.backend.model.Patient;
import pik.clinic.clinicproject.backend.repositories.PatientRepository;

/**
 * A Designer generated component for the login-view.html template.
 *
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Route("login")
@Tag("login-view")
@HtmlImport("login-view.html")
public class LoginView extends PolymerTemplate<LoginView.LoginViewModel> {

    @Autowired
    PatientRepository patientRepository;
    @Autowired
    PasswordEncoder passwordEncoder;


    @Id("remailField")
    private TextField remailField;
    @Id("rpasswordFIeld")
    private PasswordField rpasswordFIeld;
    @Id("rFirstNameField")
    private TextField rFirstNameField;
    @Id("rLastNameField")
    private TextField rLastNameField;
    @Id("rPhoneField")
    private TextField rPhoneField;
    @Id("rpeselField")
    private TextField rpeselField;
    @Id("rAdressField")
    private TextField rAdressField;
    @Id("registerButton")
    private Button registerButton;


    /**
     * Creates a new LoginView.
     */
    public LoginView() {
        // You can initialise any data required for the connected UI components here.
        registerButton.addClickListener(buttonClickEvent -> {
            Patient p = new Patient();
            p.setEmail(remailField.getValue());
            p.setPassword(passwordEncoder.encode(rpasswordFIeld.getValue()));
            p.setFirstName(rFirstNameField.getValue());
            p.setLastName(rLastNameField.getValue());
            p.setPhoneNumber(rPhoneField.getValue());
            p.setPesel(rpeselField.getValue());
            p.setAddress(rAdressField.getValue());
            p.setRole("admin");
            patientRepository.save(p);
            Notification.show("Pomślnie zarejestrowano!");
        });
    }

    /**
     * This model binds properties between LoginView and login-view.html
     */
    public interface LoginViewModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
