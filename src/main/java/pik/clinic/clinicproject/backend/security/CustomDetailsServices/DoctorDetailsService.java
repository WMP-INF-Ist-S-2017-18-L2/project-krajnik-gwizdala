package pik.clinic.clinicproject.backend.security.CustomDetailsServices;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pik.clinic.clinicproject.backend.model.Doctor;
import pik.clinic.clinicproject.backend.repositories.DoctorRepository;


import java.util.Collections;

/**
 * Implements the {@link UserDetailsService}.
 *
 * This implementation searches for {@link Doctor} entities by the email
 * supplied in the login screen.
 */

@Service
@Primary
public class DoctorDetailsService implements UserDetailsService {

    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorDetailsService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    /**
     *
     * Recovers the {@link Doctor} from the database using the email supplied
     * in the login screen. If the user is found, returns a
     * {@link org.springframework.security.core.userdetails.User}.
     *
     * @param email Doctor
     *
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Doctor doctor = doctorRepository.findByEmailIgnoreCase(email);
        if (null == doctor) {
            throw new UsernameNotFoundException("No user present with username: " + email);

        } else {
            return new org.springframework.security.core.userdetails.User(doctor.getEmail(), doctor.getPassword(),
                    Collections.singletonList(new SimpleGrantedAuthority(doctor.getRole())));
        }
    }
}