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
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pik.clinic.clinicproject.backend.model.Department;
import pik.clinic.clinicproject.backend.model.Doctor;
import pik.clinic.clinicproject.backend.model.Patient;
import pik.clinic.clinicproject.backend.model.Visit;
import pik.clinic.clinicproject.backend.repositories.*;
import pik.clinic.clinicproject.backend.security.SecurityUtils;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.NoSuchElementException;

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
@PageTitle("MedClinic - Panel Administracyjny")
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
    @Autowired
    AdminRepository adminRepository;

    Visit currentSelectedVisit;
    Patient currentSelectedPatient;
    Doctor currentSelectedDoctor;
    Department currentSelectedDepartment;

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
            try{
            for (Patient patient : patientList) {
                if (patient.getId() == (patientGrid.getSelectionModel().getFirstSelectedItem().get().getId())) {
                    patientRepository.deleteById(patient.getId());
                }
            }
            }catch (NoSuchElementException e){
                Notification.show("Nie wybrano elementu!",5000, Notification.Position.MIDDLE);
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
            try{
            for (Doctor doctor : doctorList) {

                    if (doctor.getId() == (doctorGrid.getSelectionModel().getFirstSelectedItem().get().getId())) {
                        try{
                        doctorRepository.deleteById(doctor.getId());
                        }catch (Exception e){
                            Notification.show(" Nie można usunąć! Do podanego lekarza przypisane są wizyty!",5000, Notification.Position.MIDDLE);

                        }
                    }

                }

            }catch (NoSuchElementException e){
                Notification.show("Nie wybrano elementu!",5000, Notification.Position.MIDDLE);
            }

            doctorGrid.setItems(doctorRepository.findAll());
        });


        //DEPARTMENT, GRID SELECTION AND DELETE LISTENERS
        departmentGrid.addSelectionListener(item -> {
            item.getFirstSelectedItem().ifPresent(selectedItem -> {
                currentSelectedDepartment = selectedItem;
                if (currentSelectedDepartment != null) {
                    departmentDeleteButton.setEnabled(true);
                } else {
                    departmentDeleteButton.setEnabled(false);
                }
            });
        });
        departmentDeleteButton.addClickListener(buttonClickEvent -> {
            //vaadinButton.setEnabled(false);
            List<Department> departmentList = departmentRepository.findAll();
            try{
                for (Department department : departmentList) {

                    if (department.getId() == (departmentGrid.getSelectionModel().getFirstSelectedItem().get().getId())) {
                        try{
                            departmentRepository.deleteById(department.getId());
                        }catch (Exception e){
                            Notification.show("Nie można usunąc! Do wybranej poradni przypisani są lekarze!",5000, Notification.Position.MIDDLE);

                        }
                    }

                }

            }catch (NoSuchElementException e){
                Notification.show("Nie wybrano elementu!");
            }

            departmentGrid.setItems(departmentRepository.findAll());
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
            List<Visit> visitList = visitRepository.findAll();
            try{
                for (Visit visit : visitList) {
                    if (visit.getId() == (visitGrid.getSelectionModel().getFirstSelectedItem().get().getId())) {
                        visitRepository.deleteById(visit.getId());
                    }
                }
            }catch (NoSuchElementException e){
                Notification.show("Nie wybrano elementu!",5000, Notification.Position.MIDDLE);
            }




            visitGrid.setItems(visitRepository.findAll());
        });





    }
    @PostConstruct
    public void init(){
        /********* PATIENT ******* */
        patientGrid.setItems(patientRepository.findAll());
        patientGrid.addColumn(renderer.withProperty("patientId", Patient::getId)).setVisible(false);
        patientGrid.addColumn(renderer.withProperty("patientImie", Patient::getFirstName)).setVisible(false);
        patientGrid.addColumn(renderer.withProperty("patientNazwisko", Patient::getLastName)).setVisible(false);
        patientGrid.addColumn(renderer.withProperty("patientDate",Patient::getDateBirth)).setVisible(false);
        patientGrid.addColumn(renderer.withProperty("patientEmail", Patient::getEmail)).setVisible(false);
        patientGrid.addColumn(renderer.withProperty("patientPesel", Patient::getPesel)).setVisible(false);
        patientGrid.addColumn(renderer.withProperty("patientTelefon", Patient::getPhoneNumber)).setVisible(false);
        patientGrid.addColumn(renderer.withProperty("patientAdres", Patient::getAddress)).setVisible(false);


        patientDeleteButton.setEnabled(false);

        /********* DOCTOR ******* */
        doctorGrid.setItems(doctorRepository.findAll());
        doctorGrid.addColumn(rendererDoc.withProperty("doctorId", Doctor::getId)).setVisible(false);
        doctorGrid.addColumn(rendererDoc.withProperty("doctorPesel", Doctor::getPESEL)).setVisible(false);
        doctorGrid.addColumn(rendererDoc.withProperty("doctorImie", Doctor::getFirstName)).setVisible(false);
        doctorGrid.addColumn(rendererDoc.withProperty("doctorNazwisko", Doctor::getLastName)).setVisible(false);
        doctorGrid.addColumn(rendererDoc.withProperty("doctorDate", Doctor::getDateBirth)).setVisible(false);
        doctorGrid.addColumn(rendererDoc.withProperty("doctorEmail", Doctor::getEmail)).setVisible(false);
        doctorGrid.addColumn(rendererDoc.withProperty("doctorTelefon", Doctor::getPhoneNumber)).setVisible(false);
        doctorGrid.addColumn(rendererDoc.withProperty("doctorAdres", Doctor::getAddress)).setVisible(false);
        doctorGrid.addColumn(rendererDoc.withProperty("doctorSpecialization", Doctor::getSpecialization)).setVisible(false);
        doctorGrid.addColumn(rendererDoc.withProperty("doctorDepartment",Doctor::getDepart)).setVisible(false);

        doctorDeleteButton.setEnabled(false);

        /********* DEPARTMENT ******* */
        departmentGrid.setItems(departmentRepository.findAll());
        departmentGrid.addColumn(departmentTemplateRenderer.withProperty("departmentId",Department::getId)).setVisible(false);
        departmentGrid.addColumn(departmentTemplateRenderer.withProperty("departmentNameGrid",Department::getName)).setVisible(false);
        departmentGrid.addColumn(departmentTemplateRenderer.withProperty("departmentPhone",Department::getRegistrationPhone)).setVisible(false);
        departmentGrid.addColumn(departmentTemplateRenderer.withProperty("departmentEmail",Department::getRegistrationEmail)).setVisible(false);

        doctorDepartmentComboBox.setItemLabelGenerator(Department::getName);
        doctorDepartmentComboBox.setItems(departmentRepository.findAll());
        doctorDepartmentComboBox.setRenderer(departmentTemplateRenderer.withProperty("departmentName",Department::toString));

        departmentDeleteButton.setEnabled(false);

        /********* VIST ******* */
        visitGrid.setItems(visitRepository.findAll());
        visitGrid.addColumn(rendererVisit.withProperty("visitIdGrid", Visit::getId)).setVisible(false);
        visitGrid.addColumn(rendererVisit.withProperty("visitDataGrid", Visit::getDateOfVisit)).setVisible(false);
        visitGrid.addColumn(rendererVisit.withProperty("visitPatientGird", Visit::getPatient)).setVisible(false);
        visitGrid.addColumn(rendererVisit.withProperty("visitDoctorGrid", Visit::getDoctor)).setVisible(false);
        visitGrid.addColumn(rendererVisit.withProperty("visitSumaryGrid", Visit::getSummary)).setVisible(false);

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
        if(patientEmailField.getValue()!= null && patientPasswordField.getValue()!= null &&
                passwordNameField.getValue()!= null && patientLastNameField.getValue()!= null && patientPESELField.getValue()!= null &&
                patientDatePicker.getValue()!= null && patientAddressField.getValue()!= null && patientPhoneField.getValue()!= null ){
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
            if(patientRepository.findByEmailIgnoreCase(patientEmailField.getValue())== null && adminRepository.findByEmailIgnoreCase(patientEmailField.getValue())== null
                    && doctorRepository.findByEmailIgnoreCase(patientEmailField.getValue())== null ){
                patientRepository.save(p);
                Notification.show("Pomyślnie dodano pacjęta!");
            }else {
                Notification.show("Podany email jest zajęty!");
            }
            patientGrid.setItems(patientRepository.findAll());
            visitPatientComboBox.setItems(patientRepository.findAll());

        }else{
            Notification.show("Wypełnij wszystkie pola!",5000, Notification.Position.MIDDLE);
        }

    }

    @EventHandler
    public void addDoctor() {
        if(doctorEmailField.getValue()!= null && doctorPasswordField.getValue()!= null &&
                doctorNameField.getValue()!= null && doctorLastNameField.getValue()!= null && doctorPESELField.getValue()!= null &&
                doctorDatePicker.getValue()!= null && doctorAddressield.getValue()!= null && doctorPhoneField.getValue()!= null && doctorSpecializationField.getValue()!= null &&
                doctorDepartmentComboBox.getValue()!= null ){
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
            if(patientRepository.findByEmailIgnoreCase(doctorEmailField.getValue())== null && adminRepository.findByEmailIgnoreCase(doctorEmailField.getValue())== null
                    && doctorRepository.findByEmailIgnoreCase(doctorEmailField.getValue())== null ){
                doctorRepository.save(d);
                Notification.show("Pomyślnie dodano !",5000, Notification.Position.MIDDLE);
            }else {
                Notification.show("Podany email jest zajęty!",5000, Notification.Position.MIDDLE);
            }
            doctorGrid.setItems(doctorRepository.findAll());
            visitDoctorComboBox.setItems(doctorRepository.findAll());

        }else{
            Notification.show("Wypełnij wszystkie pola!",5000, Notification.Position.MIDDLE);
        }



    }

    @EventHandler
    public void addDepartment(){
        if(departmentNameField.getValue() != null && departmentPhoneField.getValue()!= null && departmentEmailField.getValue() != null){
            if(departmentRepository.findByName(departmentNameField.getValue()) == null){
                Department department = new Department(
                        departmentNameField.getValue(),
                        departmentPhoneField.getValue(),
                        departmentEmailField.getValue()
                );
                departmentRepository.save(department);
                departmentGrid.setItems(departmentRepository.findAll());
                doctorDepartmentComboBox.setItems(departmentRepository.findAll());
                Notification.show("Dodano poradnie" ,5000, Notification.Position.MIDDLE);
            }else {
                Notification.show("Istnieje już poradnia o takiej nazwie!",5000, Notification.Position.MIDDLE);
            }

        }else{
            Notification.show("Wypełnij wszystkie pola!",5000, Notification.Position.MIDDLE);
        }

    }

    @EventHandler
    public void addVisit() {
        try {
            Notification.show("Dodano wizytę" ,5000, Notification.Position.MIDDLE);
            Visit v = new Visit(
                    visitDatePicker.getValue(),
                    visitPatientComboBox.getValue(),
                    visitDoctorComboBox.getValue(),
                    visitSummaryField.getValue()
            );
            visitRepository.save(v);
            visitGrid.setItems(visitRepository.findAll());
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
