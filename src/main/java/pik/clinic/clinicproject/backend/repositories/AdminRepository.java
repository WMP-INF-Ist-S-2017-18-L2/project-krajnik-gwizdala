package pik.clinic.clinicproject.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pik.clinic.clinicproject.backend.model.Admin;



public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByEmailIgnoreCase(String email);
}
