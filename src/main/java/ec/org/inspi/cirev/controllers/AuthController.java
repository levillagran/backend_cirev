package ec.org.inspi.cirev.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.org.inspi.cirev.models.Modulo;
//import ec.org.inspi.cirev.models.Municipio;
import ec.org.inspi.cirev.models.Role;
import ec.org.inspi.cirev.models.UserRole;
import ec.org.inspi.cirev.payload.request.LoginRequest;
import ec.org.inspi.cirev.payload.response.JwtResponse;
import ec.org.inspi.cirev.repositories.ModuloRepository;
//import ec.org.inspi.cirev.repositories.MunicipioRepository;
import ec.org.inspi.cirev.repositories.RoleRepository;
import ec.org.inspi.cirev.repositories.UserModuloRepository;
import ec.org.inspi.cirev.repositories.UserRepository;
import ec.org.inspi.cirev.repositories.UserRoleRepository;
import ec.org.inspi.cirev.security.jwt.JwtUtils;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	private UserRepository usersRepository;
	@Autowired
	private UserRoleRepository usersRolesRepository;
	@Autowired
	private RoleRepository rolesRepository;
	@Autowired
	private ModuloRepository moduloRepository;
	@Autowired
	private UserModuloRepository userModuloRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
		//System.out.println(new BCryptPasswordEncoder().encode(loginRequest.getPassword()));
		Modulo modulo = moduloRepository.findFirstByCodeAndActiveTrue(loginRequest.getModule());
		ec.org.inspi.cirev.models.User user = usersRepository.findByUsername(loginRequest.getUsername()).get();
		if (userModuloRepository.existsByUserIdAndModuleIdAndActiveTrue(user.getId(), modulo.getId())) {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtUtils.generateJwtToken(loginRequest.getUsername());

			List<UserRole> userRol = usersRolesRepository.findByUserId(user.getId());
			List<String> roles = new ArrayList<>();
			for (UserRole userol : userRol) {
				Role rol = rolesRepository.findById(userol.getRoleId()).get();
				roles.add(rol.getCode());
			}

			return ResponseEntity.ok(new JwtResponse(jwt, user.getId(), user.getName() + " " + user.getSecondname()
					+ " " + user.getLastname() + " " + user.getSecondlastname(), user.getUsername(), roles, loginRequest.getModule()));
		} else {
			return ResponseEntity.ok(new JwtResponse("false", user.getId(), user.getName() + " " + user.getSecondname()
			+ " " + user.getLastname() + " " + user.getSecondlastname(), user.getUsername(), null, loginRequest.getModule()));
		}

	}

}
