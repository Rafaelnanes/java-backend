package rbn.edu.utils;

import java.util.ArrayList;

import rbn.edu.jwt.AuthenticationUser;
import rbn.edu.model.User;
import rbn.edu.model.UserLevel;

public class AuthenticationUserWrapper {

    private static AuthenticationUserWrapper instance;

    private AuthenticationUserWrapper() {
    }

    public static AuthenticationUserWrapper get() {
	if (instance == null) {
	    instance = new AuthenticationUserWrapper();
	}
	return instance;
    }

    public AuthenticationUser convert(User user) {
	AuthenticationUser authenticationUser = new AuthenticationUser();
	authenticationUser.setAuthorities(new ArrayList<UserLevel>(user.getUserLevels()));
	authenticationUser.setLogin(user.getLogin());
	authenticationUser.setPassword(user.getPassword());
	authenticationUser.setEnabled(user.isEnabled());
	return authenticationUser;
    }

}
