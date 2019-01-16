package pik.clinic.clinicproject.backend.security.currentUsers;


import pik.clinic.clinicproject.backend.model.Patient;

@FunctionalInterface
public interface CurrentPatient {

	Patient getPatient();
}
