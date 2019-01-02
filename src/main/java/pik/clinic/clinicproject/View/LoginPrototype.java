package pik.clinic.clinicproject.View;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.polymertemplate.EventHandler;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;
import pik.clinic.clinicproject.Model.Patient;
import pik.clinic.clinicproject.Model.Visit;
import pik.clinic.clinicproject.Repositories.PatientRepository;
import pik.clinic.clinicproject.Repositories.VisitRepository;

/**
 * A Designer generated component for the login-prototype.html template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Route("prototyp")
@Tag("login-prototype")
@HtmlImport("login-prototype.html")
public class LoginPrototype extends PolymerTemplate<LoginPrototype.LoginPrototypeModel> {

    @Autowired
    VisitRepository visitRepository;

    @Autowired
    PatientRepository patientRepository;

    TemplateRenderer<Visit> renderer = TemplateRenderer.<Visit>of("");

    @Id("loginButton")
    private Button loginButton;
    @Id("pesel")
    private TextField pesel;
    @Id("haslo")
    private TextField haslo;
    @Id("logowanie")
    private FormLayout logowanie;
    @Id("visitGrid")
    private Grid visitGrid;

    /**
     * Creates a new LoginPrototype.
     */
    public LoginPrototype() {
        // You can initialise any data required for the connected UI components here.
        visitGrid.setVisible(false);
    }

    @EventHandler
    private void loginAction() {
        try {
            Patient p1 = patientRepository.findBypesel(Long.parseLong(pesel.getValue()));
            System.out.println(p1.getPesel());
            System.out.println(p1.getPassword());
            if (p1.getPassword().equals(haslo.getValue())) {
                //loginButton.getUI().ifPresent(ui -> ui.navigate("patient-view"));
                System.out.println("true");
                logowanie.setVisible(false);
                visitGrid.setItems(visitRepository.findByPatient(p1));
                visitGrid.addColumn(renderer.withProperty("id", Visit::getId));
                visitGrid.addColumn(renderer.withProperty("opis", Visit::getSummary));
                visitGrid.addColumn(renderer.withProperty("data", Visit::getDateOfVisit));
                visitGrid.addColumn(renderer.withProperty("lekarz", Visit::getDoctor));
                visitGrid.addColumn(renderer.withProperty("pacjent", Visit::getPatient));
                visitGrid.setVisible(true);

            } else {
                System.out.println("Nieprawidłowe hasło");
            }
        } catch (NullPointerException e) {
            System.err.println(e.getMessage());
            System.out.println("Nie istnieje taki użytkownik");
        }


    }

    /**
     * This model binds properties between LoginPrototype and login-prototype.html
     */
    public interface LoginPrototypeModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
