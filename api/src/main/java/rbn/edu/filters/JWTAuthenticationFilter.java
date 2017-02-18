package rbn.edu.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.ExpiredJwtException;
import rbn.edu.jwt.TokenAuthenticationService;
import rbn.edu.model.api.ResponseError;
import rbn.edu.model.api.StatusCodeDetail;

@Component
public class JWTAuthenticationFilter extends GenericFilterBean {

    @Autowired
    private TokenAuthenticationService tokenAuthenticationService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
	    throws IOException, ServletException {
	Authentication authentication = null;
	try {
	    authentication = tokenAuthenticationService.getAuthentication((HttpServletRequest) request);
	} catch (ExpiredJwtException e) {
	    handleTokenExpiration(response);
	    return;
	}

	SecurityContextHolder.getContext().setAuthentication(authentication);
	filterChain.doFilter(request, response);
    }

    private void handleTokenExpiration(ServletResponse response) throws IOException, JsonProcessingException {
	HttpServletResponse httpServletResponse = (HttpServletResponse) response;
	ResponseError errorResponse = new ResponseError();
	List<String> list = new ArrayList<String>();
	errorResponse.setMessages(list);
	errorResponse.setStatusCode(HttpStatus.UNAUTHORIZED.value());
	errorResponse.setStatusCodeDetail(StatusCodeDetail.TOKEN_EXPIRED);
	list.add(StatusCodeDetail.TOKEN_EXPIRED.toString());
	httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
	httpServletResponse.getWriter().write(convertObjectToJson(errorResponse));
    }

    public String convertObjectToJson(Object object) throws JsonProcessingException {
	if (object == null) {
	    return null;
	}
	ObjectMapper mapper = new ObjectMapper();
	return mapper.writeValueAsString(object);
    }
}
