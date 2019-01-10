package pik.clinic.clinicproject.backend.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import pik.clinic.clinicproject.backend.model.Doctor;


public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
