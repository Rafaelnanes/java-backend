package rbn.edu.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import rbn.edu.jwt.AuthenticatedUser;
import rbn.edu.jwt.TokenAuthenticationService;
import rbn.edu.model.User;
import rbn.edu.service.IUserService;

@Component
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

    @Autowired
    private TokenAuthenticationService tokenAuthenticationService;
    @Autowired
    private IUserService userService;

    @Autowired
    public JWTLoginFilter(AuthenticationManager authenticationManager) {
	super(new AntPathRequestMatcher("/login"));
	setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest,
	    HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
	User credentials = new ObjectMapper().readValue(httpServletRequest.getInputStream(), User.class);
	User user = userService.getUserByLogin(credentials.getLogin());
	AuthenticatedUser authenticatedUser = new AuthenticatedUser(user);
	return getAuthenticationManager().authenticate(authenticatedUser);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
	    Authentication authentication) throws IOException, ServletException {
	String name = authentication.getName();
	tokenAuthenticationService.addAuthentication(response, name);
    }
}
