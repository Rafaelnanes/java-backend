package rbn.edu.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import rbn.edu.jwt.TokenAuthenticationService;

public class JWTAuthenticationFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
	    throws IOException, ServletException {
	// TokenAuthenticationService, I dont need this class into Bean
	Authentication authentication = new TokenAuthenticationService()
		.getAuthentication((HttpServletRequest) request);

	SecurityContextHolder.getContext().setAuthentication(authentication);
	filterChain.doFilter(request, response);
    }
}
