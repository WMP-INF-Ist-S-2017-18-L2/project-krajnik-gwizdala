package pik.clinic.clinicproject.View;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.polymertemplate.EventHandler;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
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
import java.util.Properties;

/**
 * A Designer generated component for the patient-view.html template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Route("patient-view")
@Tag("patient-view")
@HtmlImport("patient-view.html")
public class PatientView extends PolymerTemplate<PatientView.PatientViewModel> {

    private static final String HOST = "smtp.gmail.com";
    private static final int PORT = 465;
    // Adres email osby która wysyła maila
    private static final String FROM = "patro10@interia.pl";
    // Hasło do konta osoby która wysyła maila
    private static final String PASSWORD = "patryk";
    // Adres email osoby do której wysyłany jest mail
    private static final String TO = "stefekx9@gmail.com";
    // Temat wiadomości
    private static final String SUBJECT = "Hello World";
    // Treść wiadomości
    private static final String CONTENT = "To mój pierwszy mail wysłany za pomocą JavaMailAPI.";

    TemplateRenderer<Patient> renderer = TemplateRenderer.<Patient>of("");
    TemplateRenderer<Doctor> rendererDoc = TemplateRenderer.<Doctor>of("");
    TemplateRenderer<Visit> rendererVisit = TemplateRenderer.<Visit>of("");

    @Autowired
    VisitRepository visitRepository;
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    DoctorRepository doctorRepository;

    @Id("nameLabel")
    private Label nameLabel;
    @Id("gridinfo")
    private Grid<Visit> gridinfo;
    @Id("patients")
    private ComboBox<Patient> patients;
    @Id("doctors")
    private ComboBox<Doctor> doctors;
    @Id("visitdate")
    private DatePicker visitdate;
    @Id("summary")
    private TextField summary;
    @Id("addVisit")
    private Button addVisit;
    /*@Id("peselLabel")
    private Label peselLabel;
    @Id("addresLabel")
    private Label addresLabel;*/

    /**
     * Creates a new PatientView.
     */
    public PatientView() {
        // You can initialise any data required for the connected UI components here.
    }

    @PostConstruct
    public void combo() {
        patients.setItemLabelGenerator(Patient::getFirstName);
        patients.setItems(patientRepository.findAll());
        patients.setRenderer(renderer.withProperty("patientName", Patient::toString));
        doctors.setItemLabelGenerator(Doctor::getFirstName);
        doctors.setItems(doctorRepository.findAll());
        doctors.setRenderer(rendererDoc.withProperty("doctorName", Doctor::toString));
        //vaadinButton.setEnabled(false);
    }


    @EventHandler
    public void saveVisit() {
        try {
            Notification.show("TEKST");
            Visit v = new Visit();
            v.setPatient(patients.getValue());
            v.setDoctor(doctors.getValue()); //combobox doktora
            v.setDateOfVisit(visitdate.getValue()); //wybor daty wizyty
            v.setSummary(summary.getValue()); //pole na informacje dodatkowa
            if (doctors.getValue() != null && patients.getValue() != null && visitdate.getValue() != null && summary.getValue() != null) {
                visitRepository.save(v);
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
                            visitdate.getValue());

                    Transport.send(msg);

                } catch (MessagingException mex) {
                    System.out.println("send failed, exception: " + mex);
                }
            }
        } catch (Exception e) {
            Notification.show("Wypełnij wszystkie pola!", 5000, Notification.Position.MIDDLE);
        }
        /*try {
            new SendMail().send();
        } catch (MessagingException e) {
            e.printStackTrace();
        }*/


    }

    /**
     * This model binds properties between PatientView and patient-view.html
     */
    public interface PatientViewModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
