package pik.clinic.clinicproject.backend.security;


import pik.clinic.clinicproject.backend.model.Patient;

@FunctionalInterface
public interface CurrentPatient {

	Patient getPatient();
}
