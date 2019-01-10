package pik.clinic.clinicproject.backend.repositories;

import pik.clinic.clinicproject.backend.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    Patient findBypesel(Long pesel);

}
