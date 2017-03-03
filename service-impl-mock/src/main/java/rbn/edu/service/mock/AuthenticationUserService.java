package rbn.edu.service.mock;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import rbn.edu.model.User;
import rbn.edu.model.UserLevel;
import rbn.edu.service.IUserService;

@Service("userDetailsService")
public class AuthenticationUserService implements UserDetailsService {

    @Autowired
    private IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	User user = userService.getUserByLogin(username);
	List<GrantedAuthority> authorities = null;
	if (user != null && !CollectionUtils.isEmpty(user.getUserLevels())) {
	    authorities = buildUserAuthority(user.getUserLevels());
	}

	return buildUserForAuthentication(user, authorities);
    }

    private org.springframework.security.core.userdetails.User buildUserForAuthentication(User user,
	    List<GrantedAuthority> authorities) {
	return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(),
		user.isEnabled(), true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Set<UserLevel> usuarioNiveis) {

	Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

	// Build user's authorities
	for (UserLevel usuarioNivel : usuarioNiveis) {
	    setAuths.add(new SimpleGrantedAuthority(usuarioNivel.getAuthority()));
	}

	List<GrantedAuthority> lista = new ArrayList<GrantedAuthority>(setAuths);

	return lista;
    }

}
