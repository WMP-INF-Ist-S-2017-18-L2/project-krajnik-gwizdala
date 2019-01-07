package pik.clinic.clinicproject.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pik.clinic.clinicproject.backend.model.Visit;

public interface VisitRepository extends JpaRepository<Visit, Long> {
}
