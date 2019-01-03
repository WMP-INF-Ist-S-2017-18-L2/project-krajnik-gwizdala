package pik.clinic.clinicproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pik.clinic.clinicproject.Model.Doctor;
import pik.clinic.clinicproject.Repositories.DoctorRepository;
import pik.clinic.clinicproject.View.MainView;

import javax.annotation.PostConstruct;

@SpringBootApplication(scanBasePackageClasses = MainView.class)
public class ClinicprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClinicprojectApplication.class, args);
    }



}

