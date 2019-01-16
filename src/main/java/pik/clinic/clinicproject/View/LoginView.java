package pik.clinic.clinicproject.View;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.router.*;
import com.vaadin.flow.router.internal.AfterNavigationHandler;
import com.vaadin.flow.templatemodel.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import pik.clinic.clinicproject.backend.model.Admin;
import pik.clinic.clinicproject.backend.model.Patient;
import pik.clinic.clinicproject.backend.model.Visit;
import pik.clinic.clinicproject.backend.repositories.AdminRepository;
import pik.clinic.clinicproject.backend.repositories.PatientRepository;
import pik.clinic.clinicproject.backend.repositories.VisitRepository;
import pik.clinic.clinicproject.backend.security.SecurityUtils;


/**
 * A Designer generated component for the login-view.html template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Route("login")
@Tag("login-view")
@HtmlImport("login-view.html")
public class LoginView extends PolymerTemplate<LoginView.LoginViewModel>  {

    @Autowired
    PatientRepository patientRepository;
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    VisitRepository visitRepository;

    TemplateRenderer<Visit> renderer = TemplateRenderer.<Visit>of("");
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
     * REGISTER
     */


    /**
     * Creates a new LoginView.
     */
    public LoginView() {
        // You can initialise any data required for the connected UI components here.

        rpeselField.setMaxLength(11);
        registerButton.addClickListener(buttonClickEvent -> {
            Patient p = new Patient();
            p.setEmail(remailField.getValue());
            p.setPassword(passwordEncoder.encode(rpasswordFIeld.getValue()));
            p.setFirstName(rFirstNameField.getValue());
            p.setLastName(rLastNameField.getValue());
            p.setPhoneNumber(rPhoneField.getValue());
            p.setPesel(rpeselField.getValue());
            p.setAddress(rAdressField.getValue());
            p.setRole("patient");
            patientRepository.save(p);
            Notification.show("Pomślnie zarejestrowano!");

            Admin a = new Admin();
            a.setEmail("admin@admin.com");
            a.setPassword(passwordEncoder.encode("admin"));
            a.setRole("admin");
            adminRepository.save(a);

        });





    }




    /**
     * This model binds properties between LoginView and login-view.html
     */
    public interface LoginViewModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}

