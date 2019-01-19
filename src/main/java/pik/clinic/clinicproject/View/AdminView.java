package pik.clinic.clinicproject.View;


import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.component.polymertemplate.EventHandler;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import java.util.List;

/**
 * A Designer generated component for the admin-view.html template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Route(value = "admin-view")
@Tag("admin-view")
@HtmlImport("admin-view.html")
@Viewport("width=device-width, minimum-scale=1, initial-scale=1, user-scalable=yes")
public class AdminView extends PolymerTemplate<AdminView.AdminViewModel> implements BeforeEnterObserver {

    @Autowired
    PatientRepository patientRepository;
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    VisitRepository visitRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    Visit currentSelectedVisit;
    Patient currentSelectedPatient;
    Doctor currentSelectedDoctor;

    private TemplateRenderer<Patient> renderer = TemplateRenderer.<Patient>of("");
    private TemplateRenderer<Doctor> rendererDoc = TemplateRenderer.<Doctor>of("");
    private TemplateRenderer<Visit> rendererVisit = TemplateRenderer.<Visit>of("");
    private TemplateRenderer<Department> departmentTemplateRenderer = TemplateRenderer.<Department>of("");
    /*****************PATIENT******************/
        @Id("patGrid")
        private Grid<Patient> patientGrid;
        @Id("patientEmailField")
        private TextField patientEmailField;
        @Id("patientPasswordField")
        private PasswordField patientPasswordField;
        @Id("passwordNameField")
        private TextField passwordNameField;
        @Id("patientLastNameField")
        private TextField patientLastNameField;
        @Id("patientPESELField")
        private TextField patientPESELField;
        @Id("patientDatePicker")
        private DatePicker patientDatePicker;
        @Id("patientAddressField")
        private TextField patientAddressField;
        @Id("patientPhoneField")
        private TextField patientPhoneField;
        @Id("patientAddButton")
        private Button patientAddButton;
        @Id("patientDeleteButton")
        private Button patientDeleteButton;



        @Id("doctorEmailField")
        private TextField doctorEmailField;
        @Id("doctorPasswordField")
        private PasswordField doctorPasswordField;
        @Id("doctorNameField")
        private TextField doctorNameField;
        @Id("doctorLastNameField")
        private TextField doctorLastNameField;
        @Id("doctorPESELField")
        private TextField doctorPESELField;
        @Id("doctorDatePicker")
        private DatePicker doctorDatePicker;
        @Id("doctorAddressield")
        private TextField doctorAddressield;
        @Id("doctorPhoneField")
        private TextField doctorPhoneField;
        @Id("doctorSpecializationField")
        private TextField doctorSpecializationField;
        @Id("doctorDepartmentComboBox")
        private ComboBox<Department> doctorDepartmentComboBox;
        @Id("doctorAddButton")
        private Button doctorAddButton;
        @Id("doctorGrid")
        private Grid<Doctor> doctorGrid;
        @Id("doctorDeleteButton")
        private Button doctorDeleteButton;

    /********************** DEPARTMENT *****************************/
        @Id("departmentNameField")
        private TextField departmentNameField;
        @Id("departmentPhoneField")
        private TextField departmentPhoneField;
        @Id("departmentEmailField")
        private TextField departmentEmailField;
        @Id("departmentAddButton")
        private Button departmentAddButton;
        @Id("departmentGrid")
        private Grid<Department> departmentGrid;
    /**************************************VISIT********************/
        @Id("visitGrid")
        private Grid<Visit> visitGrid;
        @Id("visitPatientComboBox")
        private ComboBox<Patient> visitPatientComboBox;
        @Id("visitDoctorComboBox")
        private ComboBox<Doctor> visitDoctorComboBox;
        @Id("visitSummaryField")
        private TextField visitSummaryField;
        @Id("visitDatePicker")
        private DatePicker visitDatePicker;
        @Id("addVisiButton")
        private Button addVisiButton;
        @Id("visitDeleteButton")
        private Button visitDeleteButton;
    @Id("departmentDeleteButton")
    private Button departmentDeleteButton;
    @Id("logoutButton")
    private Button logoutButton;


    public AdminView() {

        // You can initialise any data required for the connected UI components here.

        logoutButton.addClickListener(buttonClickEvent -> {
            UI.getCurrent().getPage().executeJavaScript("location.assign('login?logout')");
            SecurityContextHolder.clearContext();

        });

        /**PATIENT GRID SELECTION AND DELETE LISTENERS **/
        patientGrid.addSelectionListener(item -> {
            item.getFirstSelectedItem().ifPresent(selectedItem -> {
                currentSelectedPatient = selectedItem;

                if (currentSelectedPatient != null) {
                    patientDeleteButton.setEnabled(true);
                } else {
                    patientDeleteButton.setEnabled(false);
                }
            });
        });

        patientDeleteButton.addClickListener(buttonClickEvent -> {
            //vaadinButton.setEnabled(false);
            List<Patient> patientList = patientRepository.findAll();

            for (Patient patient : patientList) {
                if (patient.getId() == (patientGrid.getSelectionModel().getFirstSelectedItem().get().getId())) {
                    patientRepository.deleteById(patient.getId());
                }
            }


            patientGrid.setItems(patientRepository.findAll());
        });


        /**DOCTOR GRID SELECTION AND DELETE LISTENERS */
        doctorGrid.addSelectionListener(item -> {
            item.getFirstSelectedItem().ifPresent(selectedItem -> {
                currentSelectedDoctor = selectedItem;

                if (currentSelectedDoctor != null) {
                    doctorDeleteButton.setEnabled(true);
                } else {
                    doctorDeleteButton.setEnabled(false);
                }
            });
        });

        doctorDeleteButton.addClickListener(buttonClickEvent -> {
            //vaadinButton.setEnabled(false);
            List<Doctor> doctorList = doctorRepository.findAll();

            for (Doctor doctor : doctorList) {
                if (doctor.getId() == (doctorGrid.getSelectionModel().getFirstSelectedItem().get().getId())) {
                    doctorRepository.deleteById(doctor.getId());
                }
            }


            doctorGrid.setItems(doctorRepository.findAll());
        });



        //VISIT GRID SELECTION AND DELETE LISTENERS
        visitGrid.addSelectionListener(item -> {
            item.getFirstSelectedItem().ifPresent(selectedItem -> {
                currentSelectedVisit = selectedItem;

                if (currentSelectedVisit != null) {
                    visitDeleteButton.setEnabled(true);
                } else {
                    visitDeleteButton.setEnabled(false);
                }
            });
        });

        visitDeleteButton.addClickListener(buttonClickEvent -> {
            //vaadinButton.setEnabled(false);
            List<Visit> visitList = visitRepository.findAll();

            for (Visit visit : visitList) {
                if (visit.getId() == (visitGrid.getSelectionModel().getFirstSelectedItem().get().getId())) {
                    visitRepository.deleteById(visit.getId());
                }
            }


            visitGrid.setItems(visitRepository.findAll());
        });





    }
    @PostConstruct
    public void init(){
        /********* PATIENT ******* */
        patientGrid.setItems(patientRepository.findAll());
        patientGrid.addColumn(renderer.withProperty("id", Patient::getId));
        patientGrid.addColumn(renderer.withProperty("imie", Patient::getFirstName));
        patientGrid.addColumn(renderer.withProperty("nazwisko", Patient::getLastName));
        patientGrid.addColumn(renderer.withProperty("email", Patient::getEmail));
        patientGrid.addColumn(renderer.withProperty("pesel", Patient::getPesel));
        patientGrid.addColumn(renderer.withProperty("telefon", Patient::getPhoneNumber));
        patientGrid.addColumn(renderer.withProperty("adres", Patient::getAddress));


        patientDeleteButton.setEnabled(false);

        /********* DOCTOR ******* */
        doctorGrid.setItems(doctorRepository.findAll());
        doctorGrid.addColumn(rendererDoc.withProperty("id", Doctor::getId));
        doctorGrid.addColumn(rendererDoc.withProperty("pesel", Doctor::getPESEL));
        doctorGrid.addColumn(rendererDoc.withProperty("imie", Doctor::getFirstName));
        doctorGrid.addColumn(rendererDoc.withProperty("nazwisko", Doctor::getLastName));
        doctorGrid.addColumn(rendererDoc.withProperty("email", Doctor::getEmail));
        doctorGrid.addColumn(rendererDoc.withProperty("telefon", Doctor::getPhoneNumber));
        doctorGrid.addColumn(rendererDoc.withProperty("adres", Doctor::getAddress));
        doctorGrid.addColumn(rendererDoc.withProperty("doctorSpecialization", Doctor::getSpecialization));
        doctorGrid.addColumn(rendererDoc.withProperty("doctorDepartment",Doctor::getDepartmentName));

        doctorDeleteButton.setEnabled(false);

        /********* DEPARTMENT ******* */
        departmentGrid.setItems(departmentRepository.findAll());
        departmentGrid.addColumn(departmentTemplateRenderer.withProperty("id",Department::getId));
        departmentGrid.addColumn(departmentTemplateRenderer.withProperty("departmentNameGrid",Department::getName));
        departmentGrid.addColumn(departmentTemplateRenderer.withProperty("departmentPhone",Department::getRegistrationPhone));
        departmentGrid.addColumn(departmentTemplateRenderer.withProperty("departmentEmail",Department::getRegistrationEmail));

        doctorDepartmentComboBox.setItemLabelGenerator(Department::getName);
        doctorDepartmentComboBox.setItems(departmentRepository.findAll());
        doctorDepartmentComboBox.setRenderer(departmentTemplateRenderer.withProperty("departmentName",Department::toString));

        departmentDeleteButton.setEnabled(false);

        /********* VIST ******* */
        visitGrid.setItems(visitRepository.findAll());
        visitGrid.addColumn(rendererVisit.withProperty("visitIdGrid", Visit::getId));
        visitGrid.addColumn(rendererVisit.withProperty("visitDataGrid", Visit::getDateOfVisit));
        visitGrid.addColumn(rendererVisit.withProperty("visitPatientGird", Visit::getPatient));
        visitGrid.addColumn(rendererVisit.withProperty("visitDoctorGrid", Visit::getDoctor));
        visitGrid.addColumn(rendererVisit.withProperty("visitSumaryGrid", Visit::getSummary));

        visitPatientComboBox.setItemLabelGenerator(Patient::getFirstName);
        visitPatientComboBox.setItems(patientRepository.findAll());
        visitPatientComboBox.setRenderer(renderer.withProperty("patientName", Patient::toString));
        visitDoctorComboBox.setItemLabelGenerator(Doctor::getFirstName);
        visitDoctorComboBox.setItems(doctorRepository.findAll());
        visitDoctorComboBox.setRenderer(rendererDoc.withProperty("doctorName", Doctor::toString));
        visitDeleteButton.setEnabled(false);

    }









    @EventHandler
    public void addPatient() {
        Patient p = new Patient(
                patientEmailField.getValue(),
                passwordEncoder.encode(patientPasswordField.getValue()),
                passwordNameField.getValue(),
                patientLastNameField.getValue(),
                patientPESELField.getValue(),
                patientDatePicker.getValue(),
                patientAddressField.getValue(),
                patientPhoneField.getValue()
        );
        patientRepository.save(p);
        patientGrid.setItems(patientRepository.findAll());
        visitPatientComboBox.setItems(patientRepository.findAll());
        Notification.show("Dodano pacjenta");
    }

    @EventHandler
    public void addDoctor() {
        Doctor d = new Doctor(
                doctorEmailField.getValue(),
                passwordEncoder.encode(doctorPasswordField.getValue()),
                doctorNameField.getValue(),
                doctorLastNameField.getValue(),
                doctorPESELField.getValue(),
                doctorDatePicker.getValue(),
                doctorAddressield.getValue(),
                doctorPhoneField.getValue(),
                doctorSpecializationField.getValue(),
                doctorDepartmentComboBox.getValue()

        );
        doctorRepository.save(d);
        doctorGrid.setItems(doctorRepository.findAll());
        visitDoctorComboBox.setItems(doctorRepository.findAll());
        Notification.show("Dodano lekarza");
    }

    @EventHandler
    public void addDepartment(){
        Department department = new Department(
                departmentNameField.getValue(),
                departmentPhoneField.getValue(),
                departmentEmailField.getValue()
        );
        departmentRepository.save(department);
        departmentGrid.setItems(departmentRepository.findAll());
        doctorDepartmentComboBox.setItems(departmentRepository.findAll());
        Notification.show("Dodano poradnie");
    }

    @EventHandler
    public void addVisit() {
        try {
            Notification.show("Dodano wizytę");
            Visit v = new Visit(
                    visitDatePicker.getValue(),
                    visitPatientComboBox.getValue(),
                    visitDoctorComboBox.getValue(),
                    visitSummaryField.getValue()
            );
            visitRepository.save(v);
            visitGrid.setItems(visitRepository.findAll());
           /* if (visitDoctorComboBox.getValue() != null && visitPatientComboBox.getValue() != null && visitDatePicker.getValue() != null && visitSummaryField.getValue() != null) {
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
                    msg.setText("Wizyta pacjenta " + visitPatientComboBox.getValue() +
                            " u doktora " + visitDoctorComboBox.getValue() + " odbędzie się dnia " +
                            visitDatePicker.getValue());
                    Transport.send(msg);
                } catch (MessagingException mex) {
                    System.out.println("send failed, exception: " + mex);
                }
            }*/
        } catch (Exception e) {
            Notification.show("Wypełnij wszystkie pola!", 5000, Notification.Position.MIDDLE);
        }
    }



    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if(patientRepository.findByEmailIgnoreCase(SecurityUtils.getUsername())!= null){
            beforeEnterEvent.rerouteTo("patient-view");
            beforeEnterEvent.getUI().navigate("patient-view");

        }else if(doctorRepository.findByEmailIgnoreCase(SecurityUtils.getUsername())!= null){
                    beforeEnterEvent.rerouteTo("doctor-view");
                    beforeEnterEvent.getUI().navigate("doctor-view");

        }


    }



    /**
     * This model binds properties between AdminView and admin-view.html
     */
    public interface AdminViewModel extends TemplateModel {
        // Add setters and getters for template properties here.

    }
}
