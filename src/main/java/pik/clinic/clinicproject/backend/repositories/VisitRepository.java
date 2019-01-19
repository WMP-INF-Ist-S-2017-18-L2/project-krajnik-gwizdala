package pik.clinic.clinicproject.backend.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import pik.clinic.clinicproject.backend.model.Doctor;
import pik.clinic.clinicproject.backend.model.Patient;
import pik.clinic.clinicproject.backend.model.Visit;

import java.time.LocalDate;
import java.util.List;

public interface VisitRepository extends JpaRepository<Visit, Long> {

    List<Visit> findByPatient(Patient p);
    List<Visit> findByDoctor(Doctor d);
    Long countVisitByDateOfVisitAndDoctor(LocalDate date, Doctor doctor);
}
