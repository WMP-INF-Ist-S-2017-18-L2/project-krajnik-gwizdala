package pik.clinic.clinicproject.View;

import com.vaadin.flow.component.button.Button;
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
@HtmlImport("views/login-view.html")
public class LoginView extends PolymerTemplate<LoginView.LoginViewModel> {

    @Autowired
    PatientRepository patientRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Id("registerButton")
    private Button registerButton;
    @Id("registerPeselField")
    private TextField registerPeselField;
    @Id("registerPassField")
    private PasswordField registerPassField;
    @Id("registerNameField")
    private TextField registerNameField;
    @Id("registerLastNameField")
    private TextField registerLastNameField;
    @Id("registerPhoneField")
    private TextField registerPhoneField;
    @Id("registerEmailField")
    private TextField registerEmailField;
    @Id("registerAddressField")
    private TextField registerAddressField;

    /**
     * Creates a new LoginView.
     */
    public LoginView() {
        // You can initialise any data required for the connected UI components here.
        registerButton.addClickListener(buttonClickEvent -> {
            Patient p = new Patient();
            p.setPesel(registerPeselField.getValue());
            p.setPassword(passwordEncoder.encode(registerPassField.getValue()));
            p.setFirstName(registerNameField.getValue());
            p.setLastName(registerLastNameField.getValue());
            p.setPhoneNumber(registerPhoneField.getValue());
            p.setEmail(registerEmailField.getValue());
            p.setAddress(registerAddressField.getValue());
            p.setRole("admin");
            patientRepository.save(p);
            System.out.println("Zarejetrowano");
        });
    }

    /**
     * This model binds properties between LoginView and login-view.html
     */
    public interface LoginViewModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
