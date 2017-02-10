package rbn.edu.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import rbn.edu.jwt.AccountCredentials;
import rbn.edu.jwt.TokenAuthenticationService;

@Component
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

    @Autowired
    private TokenAuthenticationService tokenAuthenticationService;
    @Autowired
    private AuthenticationManager authenticationManager;

    public JWTLoginFilter() {
	super(new AntPathRequestMatcher("/login"));
    }

    @Override
    public void afterPropertiesSet() {
	setAuthenticationManager(authenticationManager);
	super.afterPropertiesSet();
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest,
	    HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
	AccountCredentials credentials = new ObjectMapper().readValue(httpServletRequest.getInputStream(),
		AccountCredentials.class);
	UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(credentials.getUsername(),
		credentials.getPassword());
	return getAuthenticationManager().authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
	    Authentication authentication) throws IOException, ServletException {
	String name = authentication.getName();
	tokenAuthenticationService.addAuthentication(response, name);
    }
}
