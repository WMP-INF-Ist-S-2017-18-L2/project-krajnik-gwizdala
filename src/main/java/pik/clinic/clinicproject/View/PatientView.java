package pik.clinic.clinicproject.View;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.polymertemplate.EventHandler;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pik.clinic.clinicproject.backend.model.Department;
import pik.clinic.clinicproject.backend.model.Doctor;
import pik.clinic.clinicproject.backend.model.Patient;
import pik.clinic.clinicproject.backend.model.Visit;
import pik.clinic.clinicproject.backend.repositories.DepartmentRepository;
import pik.clinic.clinicproject.backend.repositories.DoctorRepository;
import pik.clinic.clinicproject.backend.repositories.PatientRepository;
import pik.clinic.clinicproject.backend.repositories.VisitRepository;
import pik.clinic.clinicproject.backend.security.SecurityUtils;

import javax.annotation.PostConstruct;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;


/**
 * A Designer generated component for the patient-view.html template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Route(value = "patient-view")
@Tag("patient-view")
@HtmlImport("patient-view.html")
@PageTitle("MedClinic - Panel pacjenta")
public class PatientView extends PolymerTemplate<PatientView.PatientViewModel> {

    TemplateRenderer<Patient> renderer = TemplateRenderer.<Patient>of("");
    TemplateRenderer<Doctor> rendererDoc = TemplateRenderer.<Doctor>of("");
    TemplateRenderer<Visit> rendererVisit = TemplateRenderer.<Visit>of("");
    TemplateRenderer<Department> rendererDepartment = TemplateRenderer.<Department>of("");

    @Autowired
    VisitRepository visitRepository;
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    DepartmentRepository departmentRepository;


    @Id("gridinfo")
    private Grid<Visit> gridinfo;
    @Id("doctors")
    private ComboBox<Doctor> doctors;
    @Id("visitDate")
    private DatePicker visitDate;
    @Id("summary")
    private TextField summary;
    @Id("logout")
    private Button logout;
    @Id("addVisit")
    private Button addVisit;
    @Id("currUserPesel")
    private TextField currUserPesel;
    @Id("currUserLastName")
    private TextField currUserLastName;
    @Id("currUserFirstName")
    private TextField currUserFirstName;
    @Id("profileEmail")
    private TextField profileEmail;
    @Id("profileFirstName")
    private TextField profileFirstName;
    @Id("profileLastName")
    private TextField profileLastName;
    @Id("profilePesel")
    private TextField profilePesel;
    @Id("profileAdres")
    private TextField profileAdres;
    @Id("profileData")
    private TextField profileData;
    @Id("profileTelefon")
    private TextField profileTelefon;



    /**
     * Creates a new PatientView.
     */

    public PatientView() throws UsernameNotFoundException, NullPointerException {
        logout.addClickListener(buttonClickEvent -> {
            UI.getCurrent().getPage().executeJavaScript("location.assign('login?logout')");
            SecurityContextHolder.clearContext();

        });

    }

    @PostConstruct
    public Patient actualPatient() {
        return patientRepository.findByEmailIgnoreCase(SecurityUtils.getUsername());
    }

    @PostConstruct
    public void combo() {

        doctors.setItemLabelGenerator(Doctor::getFirstName);
        doctors.setItems(doctorRepository.findAll());
        doctors.setRenderer(rendererDoc.withProperty("doctorName", Doctor::toString));

    }

    @PostConstruct
    public void userLabels() {
        currUserFirstName.setValue(actualPatient().getFirstName());
        currUserLastName.setValue(actualPatient().getLastName());
        currUserPesel.setValue(actualPatient().getPesel());

        profileEmail.setValue(actualPatient().getEmail());
        profileFirstName.setValue(actualPatient().getFirstName());
        profileLastName.setValue(actualPatient().getLastName());
        profilePesel.setValue(actualPatient().getPesel());
        profileAdres.setValue(actualPatient().getAddress());
        profileData.setValue(actualPatient().getDateBirth());
        profileTelefon.setValue(actualPatient().getPhoneNumber());
    }

    @PostConstruct
    public void gridinfo() {
        Patient act = actualPatient();
        gridinfo.setItems(visitRepository.findByPatient(act));
        gridinfo.addColumn(rendererVisit.withProperty("data", Visit::getDateOfVisit)).setVisible(false);
        gridinfo.addColumn(rendererVisit.withProperty("lekarz", Visit::getDoctor)).setVisible(false);
        gridinfo.addColumn(rendererVisit.withProperty("opis", Visit::getSummary)).setVisible(false);
    }

    @EventHandler
    public void saveVisit() {
        try {
            Visit v = new Visit();
            v.setPatient(actualPatient());
            v.setDoctor(doctors.getValue()); //combobox doktora
            v.setDateOfVisit(visitDate.getValue()); //wybor daty wizyty
            v.setSummary(summary.getValue()); //pole na informacje dodatkowa

            if (doctors.getValue() != null && visitDate.getValue() != null && summary.getValue() != null) {
                if (visitRepository.countVisitByDateOfVisitAndDoctor(visitDate.getValue(), doctors.getValue()) >= 10) {
                    Notification.show("Lekarz " + doctors.getValue() + " nie ma już wolnych miejsc w dniu: " + visitDate.getValue() + ". Przepraszamy za niedogodności, prosimy wybrać inny termin lub innego lekarza.");
                } else {
                    visitRepository.save(v);
                    Properties props = System.getProperties();
                    props.put("mail.smtp.host", "poczta.interia.pl");
                    props.put("mail.smtp.auth", "true");

                    Session session = Session.getDefaultInstance(props, new Authenticator() {
                        public PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication("medicclinic", "mediclinic#11955917");
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
                        msg.setFrom(new InternetAddress("medicclinic@interia.pl"));
                        msg.setRecipients(Message.RecipientType.TO, actualPatient().getEmail());
                        msg.setSubject("Zarejestrowano nową wizytę w E-Przychodni!");
                        msg.setSentDate(new Date());
                        msg.setText("Wizyta pacjenta " + actualPatient().toString() +
                                " u doktora " + doctors.getValue() + " odbędzie się dnia " +
                                visitDate.getValue() + ". \nDodatkowy opis : " + summary.getValue() + ".\nE-PRZYCHODNIA MediClinic");
                        Transport.send(msg);
                    } catch (MessagingException mex) {
                        System.out.println("send failed, exception: " + mex);
                    }

                }
                gridinfo.setItems(visitRepository.findByPatient(actualPatient()));

            }
        } catch (Exception e) {
            Notification.show("Wypełnij wszystkie pola!", 5000, Notification.Position.MIDDLE);
        }
    }


    /**
     * This model binds properties between PatientView and patient-view.html
     */
    public interface PatientViewModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
