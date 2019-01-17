package pik.clinic.clinicproject.backend;

public class Role {
    public static final String PATIENT = "patient";
    public static final String DOCTOR = "doctor";
    // This role implicitly allows access to all views.
    public static final String ADMIN = "admin";

    private Role() {
        // Static methods and fields only
    }

    public static String[] getAllRoles() {
        return new String[] { PATIENT, DOCTOR, ADMIN };
    }

}
