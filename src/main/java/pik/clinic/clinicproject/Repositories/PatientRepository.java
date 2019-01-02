package pik.clinic.clinicproject.Repositories;

import pik.clinic.clinicproject.Model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    Patient findBypesel(Long pesel);

}
