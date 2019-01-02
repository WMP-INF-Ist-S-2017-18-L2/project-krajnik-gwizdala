package pik.clinic.clinicproject.Model;

public class CurrentPatient {
    private Long pesel;
    private boolean logged;

    public CurrentPatient() {
    }

    public Long getPesel() {
        return pesel;
    }

    public CurrentPatient(Long pesel, boolean logged) {
        this.pesel = pesel;
        this.logged = logged;
    }

    public Long setPesel(Long pesel) {
        this.pesel = pesel;
        return pesel;
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }
}
