package pik.clinic.clinicproject.backend.security.CustomDetailsServices;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pik.clinic.clinicproject.backend.model.Admin;
import pik.clinic.clinicproject.backend.repositories.AdminRepository;

import java.util.Collections;

/**
 * Implements the {@link UserDetailsService}.
 *
 * This implementation searches for {@link Admin} entities by the email
 * supplied in the login screen.
 */
@Service
@Primary
public class AdminDetailsService implements UserDetailsService {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminDetailsService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    /**
     *
     * Recovers the {@link Admin} from the database using the email supplied
     * in the login screen. If the user is found, returns a
     * {@link org.springframework.security.core.userdetails.User}.
     *
     * @param email Admin
     *
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByEmailIgnoreCase(email);
        if (null == admin) {
            throw new UsernameNotFoundException("No user present with username: " + email);

        } else {
            return new org.springframework.security.core.userdetails.User(admin.getEmail(), admin.getPassword(),
                    Collections.singletonList(new SimpleGrantedAuthority(admin.getRole())));
        }
    }
}