package pik.clinic.clinicproject.backend.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import pik.clinic.clinicproject.backend.model.Patient;
import pik.clinic.clinicproject.backend.model.Visit;

import java.util.List;

public interface VisitRepository extends JpaRepository<Visit, Long> {

    List<Visit> findByPatient(Patient p);

}
