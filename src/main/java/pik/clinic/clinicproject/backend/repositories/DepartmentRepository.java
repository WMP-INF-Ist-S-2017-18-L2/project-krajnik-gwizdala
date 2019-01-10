package pik.clinic.clinicproject.backend.repositories;

import pik.clinic.clinicproject.backend.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
