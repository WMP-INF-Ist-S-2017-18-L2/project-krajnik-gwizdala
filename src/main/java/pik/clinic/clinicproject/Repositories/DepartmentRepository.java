package pik.clinic.clinicproject.Repositories;

import pik.clinic.clinicproject.Model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
