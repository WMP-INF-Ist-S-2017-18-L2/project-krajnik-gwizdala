package pik.clinic.clinicproject.View;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.EventHandler;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;
import pik.clinic.clinicproject.Model.Patient;
import pik.clinic.clinicproject.Repositories.PatientRepository;


/**
 * A Designer generated component for the login-site.html template.
 *
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Route("")
@Tag("login-site")
@HtmlImport("login-site.html")
public class LoginSite extends PolymerTemplate<LoginSite.LoginSiteModel> {


    @Autowired
    PatientRepository patientRepository;
    /*******************************************************
     * *****************************************************
     *                     LOGIN PAGE
     * *****************************************************
     * ******************************************************/

    /**
     * Login page items
     */
    @Id("peselField")
    private TextField loginPeselField;
    @Id("passwordField")
    private PasswordField loginPasswordField;
    @Id("loginComboBox")
    private ComboBox<String> loginComboBox;
    @Id("loginButton")
    private Button loginButton;


    /**
     * Login page methods
     */
    @EventHandler
    private void login()
    {

        if(loginComboBox.getValue()=="Pacjent")
        {
            loginButton.getUI().ifPresent(ui -> ui.navigate("patient-panel"));
        }
    }
    /*******************************************************
     * *****************************************************
     *                     LOGIN PAGE
     * *****************************************************
     * ******************************************************/



    /*******************************************************
     * *****************************************************
     *                     REGISTER PAGE
     * *****************************************************
     * ******************************************************/

    /**
     * Register page items
     */
    @Id("registerPeselField")
    private TextField registerPeselField;
    @Id("registerPasswordField")
    private PasswordField registerPasswordField;
    @Id("registerNameField")
    private TextField registerNameField;
    @Id("registerLastNameField")
    private TextField registerLastNameField;
    @Id("registerEmailField")
    private TextField registerEmailField;
    @Id("registerButton")
    private Button registerButton;
    @Id("birthDatePicker")
    private DatePicker birthDatePicker;
    @Id("registerPhoneField")
    private TextField registerPhoneField;
    @Id("registerAddressField")
    private TextField registerAddressField;


    /**
     * Register page methods
     */
    @EventHandler
    private void register()
    {
        Patient p = new Patient();
        p.setPesel(Long.parseLong(registerPeselField.getValue()));
        p.setPassword(registerPasswordField.getValue());
        p.setFirstName(registerNameField.getValue());
        p.setLastName(registerLastNameField.getValue());
        p.setEmail(registerEmailField.getValue());
        p.setPhoneNumber(registerPhoneField.getValue());
        p.setAddress(registerAddressField.getValue());
        p.setBirthDate(birthDatePicker.getValue());
        patientRepository.save(p);



    }

    /*******************************************************
     * *****************************************************
     *                     REGISTER PAGE
     * *****************************************************
     * ******************************************************/


    /**
     * Creates a new LoginSite.
     */
    public LoginSite() {
        // loginButton can initialise any data required for the connected UI components here.
        /**
         *
         * Login page
         *
         * */
        //comboboxLogin add items
        loginComboBox.setItems("Pacjent","Doktor");
        //peselField.label change value
        loginComboBox.addValueChangeListener(event -> {
            if(loginComboBox.getValue()=="Pacjent")
            {
                loginPeselField.setLabel("PESEL");
            }
            if(loginComboBox.getValue()=="Doktor")
            {
                loginPeselField.setLabel("Identyfikator");

            }

        });
        /**
         *
         * Register page
         *
         * */
    }

    /**
     * This model binds properties between LoginSite and login-site.html
     */
    public interface LoginSiteModel extends TemplateModel {
        // Add setters and getters for template properties here.

    }
}
