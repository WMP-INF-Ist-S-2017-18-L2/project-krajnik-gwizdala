package pik.clinic.clinicproject.backend.security;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import pik.clinic.clinicproject.backend.Role;
import pik.clinic.clinicproject.backend.model.Admin;
import pik.clinic.clinicproject.backend.model.Patient;
import pik.clinic.clinicproject.backend.repositories.AdminRepository;
import pik.clinic.clinicproject.backend.repositories.PatientRepository;
import pik.clinic.clinicproject.backend.security.CustomDetailsServices.AdminDetailsService;
import pik.clinic.clinicproject.backend.security.CustomDetailsServices.DoctorDetailsService;
import pik.clinic.clinicproject.backend.security.CustomDetailsServices.PatientDetailsService;
import pik.clinic.clinicproject.backend.security.currentUsers.CurrentAdmin;
import pik.clinic.clinicproject.backend.security.currentUsers.CurrentPatient;

/**
 * Configures spring security, doing the following:
 * <li>Bypass security checks for static resources,</li>
 * <li>Restrict access to the application, allowing only logged in users,</li>
 * <li>Set up the login form,</li>
 * <li>Configures the {@link PatientDetailsService}.</li>
 */
@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private static final String LOGIN_PROCESSING_URL = "/login";
	private static final String LOGIN_FAILURE_URL = "/login?error";
	private static final String LOGIN_URL = "/login";
	private static final String LOGOUT_SUCCESS_URL = "/patient-view" ;

	private final PatientDetailsService patientDetailsService;
	private final DoctorDetailsService doctorDetailsService;
	private final AdminDetailsService adminDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	public SecurityConfiguration(PatientDetailsService patientDetailsService, DoctorDetailsService doctorDetailsService, AdminDetailsService adminDetailsService) {
		this.adminDetailsService = adminDetailsService;
		this.patientDetailsService = patientDetailsService;
		this.doctorDetailsService = doctorDetailsService;

	}
	@Bean
	public DaoAuthenticationProvider adminauthenticationProvider() {
		DaoAuthenticationProvider adminauthenticationProvider
				= new DaoAuthenticationProvider();
		adminauthenticationProvider.setUserDetailsService(adminDetailsService);
		adminauthenticationProvider.setPasswordEncoder(passwordEncoder());

		return adminauthenticationProvider;
	}
@Bean
	public DaoAuthenticationProvider patientauthenticationProvider() {
		DaoAuthenticationProvider patientauthenticationProvider
				= new DaoAuthenticationProvider();
		patientauthenticationProvider.setUserDetailsService(patientDetailsService);
		patientauthenticationProvider.setPasswordEncoder(passwordEncoder());

		return patientauthenticationProvider;
	}
	@Bean
	public DaoAuthenticationProvider doctorauthenticationProvider() {
		DaoAuthenticationProvider doctorauthenticationProvider
				= new DaoAuthenticationProvider();
		doctorauthenticationProvider.setUserDetailsService(doctorDetailsService);
		doctorauthenticationProvider.setPasswordEncoder(passwordEncoder());

		return doctorauthenticationProvider;
	}


	/**
	 * The password encoder to use when encrypting passwords.
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public CurrentPatient currentUser(PatientRepository patientRepository) {
		final String email = SecurityUtils.getUsername();
		Patient user =
				email != null ? patientRepository.findByEmailIgnoreCase(email) :
						null;
		return () -> user;
	}
	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public CurrentAdmin currentAdmin(AdminRepository adminRepository) {
		final String email = SecurityUtils.getUsername();
		Admin admin =
				email != null ? adminRepository.findByEmailIgnoreCase(email) :
						null;
		return () -> admin;
	}


	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.authenticationProvider(adminauthenticationProvider());
		auth.authenticationProvider(patientauthenticationProvider());
		auth.authenticationProvider(doctorauthenticationProvider());


	}

	/**
	 * Require login to access internal pages and configure login form.
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Not using Spring CSRF here to be able to use plain HTML for the login page
		http.csrf().disable()

				// Register our CustomRequestCache, that saves unauthorized access attempts, so
				// the user is redirected after login.
				.requestCache().requestCache(new CustomRequestCache())

				// Restrict access to our application.
				.and().authorizeRequests()

				// Allow all flow internal requests.
				.requestMatchers(SecurityUtils::isFrameworkInternalRequest).permitAll()

				// Allow all requests by logged in users.
				.anyRequest().hasAnyAuthority(Role.getAllRoles())


				// Configure the login page.
				.and().formLogin().loginPage(LOGIN_URL).permitAll().loginProcessingUrl(LOGIN_PROCESSING_URL)
				.failureUrl(LOGIN_FAILURE_URL)

				// Register the success handler that redirects users to the page they last tried
				// to access
				.successHandler(new SavedRequestAwareAuthenticationSuccessHandler())

				// Configure logout
				.and().logout().logoutSuccessUrl(LOGOUT_SUCCESS_URL);
	}

	/**
	 * Allows access to static resources, bypassing Spring security.
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(
				// Vaadin Flow static resources
				"/VAADIN/**",

				// the standard favicon URI
				"/favicon.ico",

				// the robots exclusion standard
				"/robots.txt",

				// web application manifest
				"/manifest.webmanifest",
				"/sw.js",
				"/offline-page.html",

				// icons and images
				"/icons/**",
				"/images/**",

				// (development mode) static resources
				"/frontend/**",

				// (development mode) webjars
				"/webjars/**",

				// (development mode) H2 debugging console
				"/h2-console/**",

				// (production mode) static resources
				"/frontend-es5/**", "/frontend-es6/**");
	}
}