package pik.clinic.clinicproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pik.clinic.clinicproject.View.MainView;

@SpringBootApplication(scanBasePackageClasses = MainView.class)
public class ClinicprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClinicprojectApplication.class, args);
    }

}

