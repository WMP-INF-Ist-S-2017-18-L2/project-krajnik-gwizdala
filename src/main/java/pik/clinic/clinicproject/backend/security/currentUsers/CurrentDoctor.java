package pik.clinic.clinicproject.backend.security.currentUsers;

import pik.clinic.clinicproject.backend.model.Doctor;

@FunctionalInterface
public interface CurrentDoctor  {
    Doctor getDoctor();
}
