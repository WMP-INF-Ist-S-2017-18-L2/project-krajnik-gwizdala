package pik.clinic.clinicproject.backend.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pik.clinic.clinicproject.backend.model.Patient;
import pik.clinic.clinicproject.backend.repositories.PatientRepository;

import java.util.Collections;

/**
 * Implements the {@link UserDetailsService}.
 * 
 * This implementation searches for {@link Patient} entities by the pesel
 * supplied in the login screen.
 */
@Service
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {

	private final PatientRepository patientRepository;

	@Autowired
	public UserDetailsServiceImpl(PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}

	/**
	 *
	 * Recovers the {@link Patient} from the database using the pesel supplied
	 * in the login screen. If the user is found, returns a
	 * {@link org.springframework.security.core.userdetails.User}.
	 *
	 * @param pesel Patient pesel
	 * 
	 */
	@Override
	public UserDetails loadUserByUsername(String pesel) throws UsernameNotFoundException {
		Patient patient = patientRepository.findBypesel(pesel);
		if (null == patient) {
			throw new UsernameNotFoundException("No user present with username: " + pesel);

		} else {
			return new org.springframework.security.core.userdetails.User(patient.getPesel(), patient.getPassword(),
					Collections.singletonList(new SimpleGrantedAuthority(patient.getRole())));
		}
	}
}