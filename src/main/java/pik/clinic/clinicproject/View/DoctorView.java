package pik.clinic.clinicproject.View;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import pik.clinic.clinicproject.backend.model.Doctor;
import pik.clinic.clinicproject.backend.model.Visit;
import pik.clinic.clinicproject.backend.repositories.DoctorRepository;
import pik.clinic.clinicproject.backend.repositories.VisitRepository;
import pik.clinic.clinicproject.backend.security.SecurityUtils;
import javax.annotation.PostConstruct;


/**
 * A Designer generated component for the doctor-view.html template.
 *
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Route(value = "doctor-view")
@Tag("doctor-view")
@HtmlImport("doctor-view.html")
public class DoctorView extends PolymerTemplate<DoctorView.DoctorViewModel> {

    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    VisitRepository visitRepository;

    TemplateRenderer<Visit> rendererVisit = TemplateRenderer.<Visit>of("");

    @Id("currDoctorDepartment")
    private TextField currDoctorDepartment;
    @Id("gridinfo")
    private Grid gridinfo;
    @Id("logout")
    private Button logout;
    @Id("currDoctorLastName")
    private TextField currDoctorLastName;
    @Id("currDoctorFirstName")
    private TextField currDoctorFirstName;
    @Id("currDoctorPesel")
    private TextField currDoctorPesel;

    /**
     * Creates a new DoctorView.
     */
    public DoctorView() {
        // You can initialise any data required for the connected UI components here.
        logout.addClickListener(buttonClickEvent -> {
            UI.getCurrent().getPage().executeJavaScript("location.assign('login?logout')");
            SecurityContextHolder.clearContext();

        });
    }

    @PostConstruct
    public Doctor actualDoctor() {
        return doctorRepository.findByEmailIgnoreCase(SecurityUtils.getUsername());
    }

    @PostConstruct
    public void userLabels() {
        currDoctorFirstName.setValue(actualDoctor().getFirstName());
        currDoctorLastName.setValue(actualDoctor().getLastName());
        currDoctorPesel.setValue(actualDoctor().getPESEL());
        currDoctorDepartment.setValue(actualDoctor().getDepartment().getName());
    }

    @PostConstruct
    public void gridinfo() {
        Doctor act = actualDoctor();
        gridinfo.setItems(visitRepository.findByDoctor(act));
        gridinfo.addColumn(rendererVisit.withProperty("data", Visit::getDateOfVisit));
        gridinfo.addColumn(rendererVisit.withProperty("pacjent", Visit::getPatient));
        gridinfo.addColumn(rendererVisit.withProperty("opis", Visit::getSummary));
    }

    /**
     * This model binds properties between DoctorView and doctor-view.html
     */
    public interface DoctorViewModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
