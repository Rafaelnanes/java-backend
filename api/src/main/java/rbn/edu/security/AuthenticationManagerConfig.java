package rbn.edu.security;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class AuthenticationManagerConfig implements AuthenticationManager {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
	if (authentication.getPrincipal() == null) {
	    throw new UsernameNotFoundException("User not found");
	}
	if (CollectionUtils.isEmpty(authentication.getAuthorities())) {
	    throw new AuthenticationCredentialsNotFoundException("User don't have any role");
	}
	return authentication;
    }

}
