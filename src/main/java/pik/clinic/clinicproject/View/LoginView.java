package pik.clinic.clinicproject.View;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.polymertemplate.EventHandler;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import pik.clinic.clinicproject.Model.Doctor;
import pik.clinic.clinicproject.Model.Patient;
import pik.clinic.clinicproject.Repositories.DoctorRepository;
import pik.clinic.clinicproject.Repositories.PatientRepository;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * A Designer generated component for the login-view.html template.
 *
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@PageTitle("Klinika MediClinic")
@Route(value = "login")
@Tag("login-view")
@HtmlImport("login-view.html")
public class LoginView extends PolymerTemplate<LoginView.LoginViewModel> {

    @Autowired
    PatientRepository patientRepository;
    @Autowired
    DoctorRepository doctorRepository;

    @Id("loginButton")
    private Button loginButton;
    @Id("loginPeselField")
    private TextField loginPeselField;
    @Id("loginComboBox")
    private ComboBox<String> loginComboBox;
    /**
    REGISTER
     */

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
    @Id("registerAddressField")
    private TextField registerAddressField;
    @Id("registerEmailField")
    private TextField registerEmailField;
    @Id("registerClearButton")
    private Button registerClearButton;
    @Id("registerButton")
    private Button registerButton;
    @Id("loginPassField")
    private PasswordField loginPassField;

    /**
     * Creates a new LoginView.
     */
    public LoginView(HttpServletRequest request) throws Exception {
        // You can initialise any data required for the connected UI components here.
        loginComboBox.setItems("Pacjent","Doktor");
        //peselField.label change value
        loginComboBox.addValueChangeListener(event -> {
            if(loginComboBox.getValue().equals("Pacjent"))
            {
                loginPeselField.setLabel("PESEL");
            }
            if(loginComboBox.getValue().equals("Doktor"))
            {
                loginPeselField.setLabel("Identyfikator");

            }

        });

        loginButton.addClickListener(event -> {
            if(loginComboBox.getValue().equals("Pacjent"))
            {
                try{
                    Patient p1 = patientRepository.findBypesel(Long.parseLong(loginPeselField.getValue()));
                    if(p1.getPassword().equals(loginPassField.getValue()))
                    {
                        HttpSession session = request.getSession();
                        session.setAttribute("patient",p1);
                        loginButton.getUI().ifPresent(ui -> ui.navigate("patient-view"));
                    }
                    else
                    {
                        System.out.println("Nieprawidłowe hasło");
                    }
                }catch (NullPointerException e) {
                    System.err.println(e.getMessage());
                    System.out.println("Nie istnieje taki użytkownik");
                }
            }
            else if(loginComboBox.getValue().equals("Doktor")) {
                try {
                    Doctor d = doctorRepository.findBylogin(loginPeselField.getValue());
                    if(d.getPassword().equals(loginPassField.getValue()))
                    {
                        HttpSession session = request.getSession();
                        session.setAttribute("doctor",d);
                        loginButton.getUI().ifPresent(ui -> ui.navigate("doctor-view"));
                    }
                    else
                    {
                        System.out.println("Nieprawidłowe hasło");
                    }
                } catch (NullPointerException e) {
                    System.err.println(e.getMessage());
                    System.out.println("Nie istnieje taki użytkownik");
                }

            }
        });
    }

    @EventHandler
    private void register(){
        Patient p = new Patient();
        p.setPesel(Long.parseLong(registerPeselField.getValue()));
        p.setPassword(registerPassField.getValue());
        p.setFirstName(registerNameField.getValue());
        p.setLastName(registerLastNameField.getValue());
        p.setPhoneNumber(registerPhoneField.getValue());
        p.setEmail(registerEmailField.getValue());
        p.setAddress(registerAddressField.getValue());
        patientRepository.save(p);
    }


    /**
     * This model binds properties between LoginView and login-view.html
     */
    public interface LoginViewModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
