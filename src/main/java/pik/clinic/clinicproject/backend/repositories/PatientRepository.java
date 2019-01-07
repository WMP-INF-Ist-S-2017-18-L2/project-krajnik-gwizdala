package pik.clinic.clinicproject.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pik.clinic.clinicproject.backend.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    Patient findBypesel(String pesel);

}
