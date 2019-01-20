package pik.clinic.clinicproject.View;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.InitialPageSettings;
import com.vaadin.flow.server.PageConfigurator;
import com.vaadin.flow.templatemodel.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import pik.clinic.clinicproject.backend.model.Admin;
import pik.clinic.clinicproject.backend.model.Patient;
import pik.clinic.clinicproject.backend.repositories.AdminRepository;
import pik.clinic.clinicproject.backend.repositories.DoctorRepository;
import pik.clinic.clinicproject.backend.repositories.PatientRepository;

import javax.annotation.PostConstruct;

/**
 * A Designer generated component for the login-view.html template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Route("login")
@Tag("login-view")
@HtmlImport("login-view.html")
@PageTitle("MedClinic - Logowanie")
public class LoginView extends PolymerTemplate<LoginView.LoginViewModel> implements PageConfigurator, AfterNavigationObserver {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    AdminRepository adminRepository;

    @Id("clearFIeldButton")
    private Button clearFIeldButton;
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
    @Id("rDatePicker")
    private DatePicker rDatePicker;

    /**
     * Creates a new LoginView.
     */
    public LoginView() {
        rpeselField.setMaxLength(11);


        registerButton.addClickListener(buttonClickEvent -> {
            if (remailField.getValue() != null && rpasswordFIeld.getValue() != null && rFirstNameField.getValue() != null && rLastNameField.getValue() != null
                    && rpeselField.getValue() != null &&
                    rDatePicker.getValue() != null && rAdressField.getValue() != null && rPhoneField.getValue() != null) {
                Patient p = new Patient(
                        remailField.getValue(),
                        passwordEncoder.encode(rpasswordFIeld.getValue()),
                        rFirstNameField.getValue(),
                        rLastNameField.getValue(),
                        rpeselField.getValue(),
                        rDatePicker.getValue(),
                        rAdressField.getValue(),
                        rPhoneField.getValue()
                );
                if (patientRepository.findByEmailIgnoreCase(remailField.getValue()) == null && adminRepository.findByEmailIgnoreCase(remailField.getValue()) == null
                        && doctorRepository.findByEmailIgnoreCase(remailField.getValue()) == null) {
                    patientRepository.save(p);
                    Notification.show("Pomyślnie Zarejestrowano!", 5000, Notification.Position.MIDDLE);
                } else {
                    Notification.show("Podany email jest zajęty!", 5000, Notification.Position.MIDDLE);
                }
            } else {
                Notification.show("Wypełnij wszystkie pola!", 5000, Notification.Position.MIDDLE);

            }
        });

        clearFIeldButton.addClickListener(buttonClickEvent -> {
            remailField.clear();
            rpasswordFIeld.clear();
            rFirstNameField.clear();
            rLastNameField.clear();
            rpeselField.clear();
            rDatePicker.clear();
            rAdressField.clear();
            rpeselField.clear();
            rPhoneField.clear();
        });
        // You can initialise any data required for the connected UI components here.
    }

    @Override
    public void configurePage(InitialPageSettings settings) {
        // Force login page to use Shady DOM to avoid problems with browsers and
        // password managers not supporting shadow DOM
        settings.addInlineWithContents(InitialPageSettings.Position.PREPEND,
                "window.customElements=window.customElements||{};" +
                        "window.customElements.forcePolyfill=true;" +
                        "window.ShadyDOM={force:true};", InitialPageSettings.WrapMode.JAVASCRIPT);
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
        boolean error = event.getLocation().getQueryParameters().getParameters().containsKey("error");

    }

    @PostConstruct
    public void addAdmin() {
        if (adminRepository.findByEmailIgnoreCase("admin@admin.com") == null) {
            Admin a = new Admin("admin@admin.com", passwordEncoder.encode("admin!QAZ@WSX"), "admin");
            adminRepository.save(a);
            Notification.show("Succesfully created ADMIN account");
        } else {
            System.out.println("admin already exists");
        }
    }

    /**
     * This model binds properties between LoginView and login-view.html
     */
    public interface LoginViewModel extends TemplateModel {
        // Add setters and getters for template properties here.
        void setError(boolean error);
    }
}
