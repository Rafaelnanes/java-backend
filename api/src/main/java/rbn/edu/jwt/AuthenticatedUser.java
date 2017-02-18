package rbn.edu.jwt;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import rbn.edu.model.User;
import rbn.edu.model.UserLevel;

public class AuthenticatedUser implements Authentication {

    private static final long serialVersionUID = -4202601568054467013L;
    private boolean authenticated = true;
    private User user;

    public AuthenticatedUser(User user) {
	this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
	return user.getUserLevels();
    }

    public void setAuthorities(List<UserLevel> userLevels) {
	user.setUserLevels(new HashSet<UserLevel>(userLevels));
    }

    @Override
    public Object getCredentials() {
	return null;
    }

    @Override
    public Object getDetails() {
	return null;
    }

    @Override
    public Object getPrincipal() {
	return user;
    }

    @Override
    public boolean isAuthenticated() {
	return this.authenticated;
    }

    @Override
    public void setAuthenticated(boolean b) throws IllegalArgumentException {
	this.authenticated = b;
    }

    @Override
    public String getName() {
	return this.user.getLogin();
    }
}
