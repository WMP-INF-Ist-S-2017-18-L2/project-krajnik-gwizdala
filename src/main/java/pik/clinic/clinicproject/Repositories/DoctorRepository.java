package pik.clinic.clinicproject.Repositories;

import pik.clinic.clinicproject.Model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Doctor findBylogin(String login);
}
