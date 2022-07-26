package ec.org.inspi.cirev.security.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ec.org.inspi.cirev.models.Role;
import ec.org.inspi.cirev.models.UserRole;
import ec.org.inspi.cirev.repositories.RoleRepository;
import ec.org.inspi.cirev.repositories.UserRepository;
import ec.org.inspi.cirev.repositories.UserRoleRepository;

@Service
public class UserServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository usersRepository;
	
	@Autowired
	private UserRoleRepository usersRolesRepository;
	
	@Autowired
	private RoleRepository rolesRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ec.org.inspi.cirev.models.User user = usersRepository.findByUsername(username).get();
		List<UserRole> userRol = usersRolesRepository.findByUserId(user.getId());
		List<String> roles = new ArrayList<>();
		for (UserRole userol : userRol) {
			Role rol = rolesRepository.findById(userol.getRoleId()).get();
			roles.add(rol.getCode());
		}
		List<GrantedAuthority> authorities = buildAuthorities(roles);
		//System.out.println(new BCryptPasswordEncoder().encode(username));
		return buildUser(user, authorities);
	}

	private UserDetails buildUser(ec.org.inspi.cirev.models.User user, List<GrantedAuthority> authorities) {
		return new User(user.getUsername(), user.getPassword(), true, true, true, true, authorities);
	}

	private List<GrantedAuthority> buildAuthorities(List<String> roles) {
		Set<GrantedAuthority> auths = new HashSet<GrantedAuthority>();
		for (String rol : roles) {
			auths.add(new SimpleGrantedAuthority(rol));
		}
		return new ArrayList<GrantedAuthority>(auths);
	}

}