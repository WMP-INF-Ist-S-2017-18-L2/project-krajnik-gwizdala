package pik.clinic.clinicproject.View;


import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.polymertemplate.EventHandler;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;
import pik.clinic.clinicproject.backend.model.Doctor;
import pik.clinic.clinicproject.backend.model.Patient;
import pik.clinic.clinicproject.backend.model.Visit;
import pik.clinic.clinicproject.backend.repositories.DoctorRepository;
import pik.clinic.clinicproject.backend.repositories.PatientRepository;
import pik.clinic.clinicproject.backend.repositories.VisitRepository;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * A Designer generated component for the admin-view.html template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Route("adminview")
@Tag("admin-view")
@HtmlImport("admin-view.html")
public class AdminView extends PolymerTemplate<AdminView.AdminViewModel> {

    @Autowired
    PatientRepository patientRepository;
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    VisitRepository visitRepository;

    Visit currentSelectedVisit;

    TemplateRenderer<Patient> renderer = TemplateRenderer.<Patient>of("");
    TemplateRenderer<Doctor> rendererDoc = TemplateRenderer.<Doctor>of("");
    TemplateRenderer<Visit> rendererVisit = TemplateRenderer.<Visit>of("");
    /**************************************VISIT********************/
    @Id("patients")
    private ComboBox<Patient> patients;
    @Id("doctors")
    private ComboBox<Doctor> doctors;
    @Id("summary")
    private TextField summaryField;
    @Id("visitGrid")
    private Grid<Visit> visitGrid;
    /**************************************VISIT********************/
    @Id("registerPeselField")
    private TextField registerPeselField;
    @Id("registerPassField")
    private PasswordField registerPassField;
    /**************************************PATIENT******************/
    @Id("registerLastNameField")
    private TextField registerLastNameField;
    @Id("registerPhoneField")
    private TextField registerPhoneField;
    @Id("registerAddressField")
    private TextField registerAddressField;
    @Id("registerEmailField")
    private TextField registerEmailField;
    @Id("visitDate")
    private DatePicker visitDate;
    @Id("registerPeselField")
    private TextField registerPeselFIeld;
    @Id("registerNameField")
    private TextField registerNameField;
    @Id("patGrid")
    private Grid<Patient> patGrid;
    @Id("vaadinButton")
    private Button vaadinButton;

    /**************************************PATIENT******************/

    public AdminView() {
        // You can initialise any data required for the connected UI components here.
        visitGrid.addSelectionListener(item -> {
            item.getFirstSelectedItem().ifPresent(selectedItem -> {
                currentSelectedVisit = selectedItem;

                if (currentSelectedVisit != null) {
                    vaadinButton.setEnabled(true);
                } else {
                    vaadinButton.setEnabled(false);
                }
            });
        });

        vaadinButton.addClickListener(buttonClickEvent -> {
            //vaadinButton.setEnabled(false);
            List<Visit> visitList = visitRepository.findAll();

            for(Visit visit : visitList) {
                if(visit.getId() == (visitGrid.getSelectionModel().getFirstSelectedItem().get().getId())) {
                    visitRepository.deleteById(visit.getId());
                }
            }


            visitGrid.setItems(visitRepository.findAll());
        });

    }

    @PostConstruct
    public void combo() {
        patients.setItemLabelGenerator(Patient::getFirstName);
        patients.setItems(patientRepository.findAll());
        patients.setRenderer(renderer.withProperty("patientName", Patient::toString));
        doctors.setItemLabelGenerator(Doctor::getFirstName);
        doctors.setItems(doctorRepository.findAll());
        doctors.setRenderer(rendererDoc.withProperty("doctorName", Doctor::toString));
        vaadinButton.setEnabled(false);
    }

    @EventHandler
    public void saveVisit() {
        Notification.show("TEKST");
        Visit v = new Visit();
        v.setSummary(summaryField.getValue());
        v.setDoctor(doctors.getValue());
        v.setPatient(patients.getValue());
        v.setDateOfVisit(visitDate.getValue());
        visitRepository.save(v);
        visitGrid.setItems(visitRepository.findAll());
    }

    @PostConstruct
    public void patGrid() {
        patGrid.setItems(patientRepository.findAll());
        patGrid.addColumn(renderer.withProperty("id", Patient::getId));
        patGrid.addColumn(renderer.withProperty("imie", Patient::getFirstName));
        patGrid.addColumn(renderer.withProperty("nazwisko", Patient::getLastName));
    }

    @EventHandler
    public void addPatient() {
        Patient p = new Patient();
        p.setFirstName(registerNameField.getValue());
        p.setLastName(registerLastNameField.getValue());
        p.setPesel(Long.parseLong(registerPeselField.getValue()));
        p.setPhoneNumber(registerPhoneField.getValue());
        p.setAddress(registerAddressField.getValue());
        p.setEmail(registerEmailField.getValue());
        p.setPassword(registerPassField.getValue());
        patientRepository.save(p);
        patients.setItems(patientRepository.findAll());
    }

    @PostConstruct
    public void visitGrid(){
        visitGrid.setItems(visitRepository.findAll());
        visitGrid.addColumn(rendererVisit.withProperty("id", Visit::getId));
        visitGrid.addColumn(rendererVisit.withProperty("opis", Visit::getSummary));
        visitGrid.addColumn(rendererVisit.withProperty("data", Visit::getDateOfVisit));
        visitGrid.addColumn(rendererVisit.withProperty("lekarz", Visit::getDoctor));
        visitGrid.addColumn(rendererVisit.withProperty("pacjent", Visit::getPatient));
    }



    /**
     * Creates a new AdminView.
     */


    /**
     * This model binds properties between AdminView and admin-view.html
     */
    public interface AdminViewModel extends TemplateModel {
        // Add setters and getters for template properties here.

    }
}
