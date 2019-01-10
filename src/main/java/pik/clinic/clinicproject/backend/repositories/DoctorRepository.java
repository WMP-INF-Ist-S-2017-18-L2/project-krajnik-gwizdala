package pik.clinic.clinicproject.backend.repositories;

import pik.clinic.clinicproject.backend.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
