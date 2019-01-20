package pik.clinic.clinicproject.backend.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import pik.clinic.clinicproject.backend.model.Department;


public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Department findByName(String name);
}
