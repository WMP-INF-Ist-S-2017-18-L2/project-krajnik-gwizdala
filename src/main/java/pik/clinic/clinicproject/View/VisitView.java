package pik.clinic.clinicproject.View;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.polymertemplate.EventHandler;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;
import pik.clinic.clinicproject.Model.Doctor;
import pik.clinic.clinicproject.Model.Patient;
import pik.clinic.clinicproject.Model.Visit;
import pik.clinic.clinicproject.Repositories.DoctorRepository;
import pik.clinic.clinicproject.Repositories.PatientRepository;
import pik.clinic.clinicproject.Repositories.VisitRepository;
import javax.annotation.PostConstruct;

/**
 * A Designer generated component for the visit-view.html template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Route("visit")
@Tag("visit-view")
@HtmlImport("visit-view.html")
@HtmlImport(value="frontend://bower_components/fullcalendar/full-calendar.html")
public class VisitView extends PolymerTemplate<VisitView.VisitViewModel> {

    @Autowired
    PatientRepository patientRepository;
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    VisitRepository visitRepository;

    TemplateRenderer<Patient> renderer = TemplateRenderer.<Patient>of("");
    TemplateRenderer<Doctor> rendererDoc = TemplateRenderer.<Doctor>of("");
    @Id("patients")
    private ComboBox<Patient> patients;
    @Id("doctors")
    private ComboBox<Doctor> doctors;
    @Id("summary")
    private TextField summaryField;

    /*
     * Creates a new VisitView.
     */
    public VisitView() {

    }

    @PostConstruct
    public void combo() {
        patients.setItemLabelGenerator(Patient::getFirstName);
        patients.setItems(patientRepository.findAll());
        patients.setRenderer(renderer.withProperty("patientName", Patient::toString));
        doctors.setItemLabelGenerator(Doctor::getFirstName);
        doctors.setItems(doctorRepository.findAll());
        doctors.setRenderer(rendererDoc.withProperty("doctorName", Doctor::toString));

    }

    @EventHandler
    public void saveVisit() {
        Notification.show("TEKST");
        Visit v = new Visit();
        v.setSummary(summaryField.getValue());
        v.setDoctor(doctors.getValue());
        v.setPatient(patients.getValue());
        visitRepository.save(v);
    }

    /**
     * This model binds properties between VisitView and visit-view.html
     */
    public interface VisitViewModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
