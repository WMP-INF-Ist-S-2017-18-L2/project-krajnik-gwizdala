package pik.clinic.clinicproject.backend.security.currentUsers;

import pik.clinic.clinicproject.backend.model.Admin;

@FunctionalInterface
public interface CurrentAdmin {
    Admin getAdmin();
}
