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
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.List;
import java.util.Properties;

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
    Patient currentSelectedPatient;
    Doctor currentSelectedDoctor;

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
    @Id("docGrid")
    private Grid<Doctor> docGrid;
    @Id("deleteVisitButton")
    private Button deleteVisitButton;
    @Id("deletePatientButton")
    private Button deletePatientButton;
    @Id("deleteDoctorButton")
    private Button deleteDoctorButton;
    @Id("docRegisterPESEL")
    private TextField docRegisterPESEL;
    @Id("docRegisterFirstName")
    private TextField docRegisterFirstName;
    @Id("docRegisterLastName")
    private TextField docRegisterLastName;
    @Id("docRegisterEmail")
    private TextField docRegisterEmail;
    @Id("docRegisterPhoneNumber")
    private TextField docRegisterPhoneNumber;
    @Id("docRegisterAddress")
    private TextField docRegisterAddress;
    @Id("docRegisterPassword")
    private PasswordField docRegisterPassword;

    /**************************************PATIENT******************/

    public AdminView() {
        // You can initialise any data required for the connected UI components here.
        //VISIT GRID SELECTION AND DELETE LISTENERS
        visitGrid.addSelectionListener(item -> {
            item.getFirstSelectedItem().ifPresent(selectedItem -> {
                currentSelectedVisit = selectedItem;

                if (currentSelectedVisit != null) {
                    deleteVisitButton.setEnabled(true);
                } else {
                    deleteVisitButton.setEnabled(false);
                }
            });
        });

        deleteVisitButton.addClickListener(buttonClickEvent -> {
            //vaadinButton.setEnabled(false);
            List<Visit> visitList = visitRepository.findAll();

            for (Visit visit : visitList) {
                if (visit.getId() == (visitGrid.getSelectionModel().getFirstSelectedItem().get().getId())) {
                    visitRepository.deleteById(visit.getId());
                }
            }


            visitGrid.setItems(visitRepository.findAll());
        });

        //PATIENT GRID SELECTION AND DELETE LISTENERS
        patGrid.addSelectionListener(item -> {
            item.getFirstSelectedItem().ifPresent(selectedItem -> {
                currentSelectedPatient = selectedItem;

                if (currentSelectedPatient != null) {
                    deletePatientButton.setEnabled(true);
                } else {
                    deletePatientButton.setEnabled(false);
                }
            });
        });

        deletePatientButton.addClickListener(buttonClickEvent -> {
            //vaadinButton.setEnabled(false);
            List<Patient> patientList = patientRepository.findAll();

            for (Patient patient : patientList) {
                if (patient.getId() == (patGrid.getSelectionModel().getFirstSelectedItem().get().getId())) {
                    patientRepository.deleteById(patient.getId());
                }
            }


            patGrid.setItems(patientRepository.findAll());
        });

        //DOCTOR GRID SELECTION AND DELETE LISTENERS
        docGrid.addSelectionListener(item -> {
            item.getFirstSelectedItem().ifPresent(selectedItem -> {
                currentSelectedDoctor = selectedItem;

                if (currentSelectedDoctor != null) {
                    deletePatientButton.setEnabled(true);
                } else {
                    deletePatientButton.setEnabled(false);
                }
            });
        });

        deleteDoctorButton.addClickListener(buttonClickEvent -> {
            //vaadinButton.setEnabled(false);
            List<Doctor> doctorList = doctorRepository.findAll();

            for (Doctor doctor : doctorList) {
                if (doctor.getId() == (docGrid.getSelectionModel().getFirstSelectedItem().get().getId())) {
                    doctorRepository.deleteById(doctor.getId());
                }
            }


            patGrid.setItems(patientRepository.findAll());
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
        deleteVisitButton.setEnabled(false);
    }

    @EventHandler
    public void saveVisit() {
        try {
            Notification.show("TEKST");
            Visit v = new Visit();
            v.setPatient(patients.getValue());
            v.setDoctor(doctors.getValue()); //combobox doktora
            v.setDateOfVisit(visitDate.getValue()); //wybor daty wizyty
            v.setSummary(summaryField.getValue()); //pole na informacje dodatkowa
            if (doctors.getValue() != null && patients.getValue() != null && visitDate.getValue() != null && summaryField.getValue() != null) {
                visitRepository.save(v);
                visitGrid.setItems(visitRepository.findAll());
                Properties props = System.getProperties();
                props.put("mail.smtp.host", "poczta.interia.pl");
                props.put("mail.smtp.auth", "true");

                Session session = Session.getDefaultInstance(props, new Authenticator() {
                    public PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("patro10", "patryk");
                    }
                });
                Provider[] providers = session.getProviders();

                try {
                    session.setProvider(providers[0]);
                } catch (NoSuchProviderException e) {
                    e.printStackTrace();
                }
                try {
                    MimeMessage msg = new MimeMessage(session);
                    msg.setFrom(new InternetAddress("patro10@interia.pl"));
                    msg.setRecipients(Message.RecipientType.TO, "stefekx9@interia.pl");
                    msg.setSubject("Tetowy mail");
                    msg.setSentDate(new Date());
                    msg.setText("Wizyta pacjenta " + patients.getValue() +
                            " u doktora " + doctors.getValue() + " odbędzie się dnia " +
                            visitDate.getValue());
                    Transport.send(msg);
                } catch (MessagingException mex) {
                    System.out.println("send failed, exception: " + mex);
                }
            }
        } catch (Exception e) {
            Notification.show("Wypełnij wszystkie pola!", 5000, Notification.Position.MIDDLE);
        }
    }

    @PostConstruct
    public void patGrid() {
        patGrid.setItems(patientRepository.findAll());
        patGrid.addColumn(renderer.withProperty("id", Patient::getId));
        patGrid.addColumn(renderer.withProperty("imie", Patient::getFirstName));
        patGrid.addColumn(renderer.withProperty("nazwisko", Patient::getLastName));
        patGrid.addColumn(renderer.withProperty("email", Patient::getEmail));
        patGrid.addColumn(renderer.withProperty("pesel", Patient::getPesel));
        patGrid.addColumn(renderer.withProperty("adres", Patient::getAddress));
        patGrid.addColumn(renderer.withProperty("telefon", Patient::getPhoneNumber));

        deletePatientButton.setEnabled(false);
    }

    @PostConstruct
    public void docGrid() {
        docGrid.setItems(doctorRepository.findAll());
        docGrid.addColumn(rendererDoc.withProperty("id", Doctor::getId));
        docGrid.addColumn(rendererDoc.withProperty("imie", Doctor::getFirstName));
        docGrid.addColumn(rendererDoc.withProperty("nazwisko", Doctor::getLastName));
        docGrid.addColumn(rendererDoc.withProperty("email", Doctor::getEmail));
        docGrid.addColumn(rendererDoc.withProperty("pesel", Doctor::getPESEL));
        docGrid.addColumn(rendererDoc.withProperty("adres", Doctor::getAddress));
        docGrid.addColumn(rendererDoc.withProperty("telefon", Doctor::getPhoneNumber));

        deleteDoctorButton.setEnabled(false);
    }


    @EventHandler
    public void addPatient() {
        Patient p = new Patient();
        p.setFirstName(registerNameField.getValue());
        p.setLastName(registerLastNameField.getValue());
        p.setPesel(registerPeselField.getValue());
        p.setPhoneNumber(registerPhoneField.getValue());
        p.setAddress(registerAddressField.getValue());
        p.setEmail(registerEmailField.getValue());
        p.setPassword(registerPassField.getValue());
        patientRepository.save(p);
        patients.setItems(patientRepository.findAll());
    }

    @EventHandler
    public void addDoctor() {
        Doctor d = new Doctor();
        d.setFirstName(docRegisterFirstName.getValue());
        d.setLastName(docRegisterLastName.getValue());
        d.setPESEL(docRegisterPESEL.getValue());
        d.setPhoneNumber(docRegisterPhoneNumber.getValue());
        d.setAddress(docRegisterAddress.getValue());
        d.setEmail(docRegisterEmail.getValue());
        d.setPassword(docRegisterPassword.getValue());
        doctorRepository.save(d);
        docGrid.setItems(doctorRepository.findAll());
    }

    @PostConstruct
    public void visitGrid() {
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
