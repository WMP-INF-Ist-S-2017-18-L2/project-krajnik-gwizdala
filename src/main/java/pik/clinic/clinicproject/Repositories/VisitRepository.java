package pik.clinic.clinicproject.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import pik.clinic.clinicproject.Model.Patient;
import pik.clinic.clinicproject.Model.Visit;

import java.util.List;

public interface VisitRepository extends JpaRepository<Visit, Long> {

    List<Visit> findByPatient(Patient p);


}
