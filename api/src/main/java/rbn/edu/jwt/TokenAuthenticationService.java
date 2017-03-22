package rbn.edu.jwt;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import rbn.edu.model.User;
import rbn.edu.service.IUserService;

@Service
public class TokenAuthenticationService {

    private static final Logger logger = LoggerFactory.getLogger(TokenAuthenticationService.class);

    @Autowired
    private IUserService userService;

    private long EXPIRATIONTIME = 1000 * 60 * 60; // 1 hour
    private String secret = "ThisIsASecret";
    // private String tokenPrefix = "Bearer";
    private String headerString = "Authorization";
    private final String TOKEN = "//";

    public void addAuthentication(HttpServletResponse response, String username) throws JsonProcessingException {
	logger.info("Authenticating user {}", username);
	String finalString = "";
	StringBuilder JWT = new StringBuilder();

	User user = userService.getUserByLogin(username);
	SecurityContextHolder.getContext().setAuthentication(new AuthenticatedUser(user));
	user.getUserLevels().forEach(level -> level.setUser(null));
	user.setPassword(null);
	ObjectMapper mapper = new ObjectMapper();
	String json = mapper.writeValueAsString(user);
	JWT.append(json);
	JWT.append(TOKEN);
	JWT.append(
		Jwts.builder().setSubject(username).setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
			.signWith(SignatureAlgorithm.HS512, secret).compact());
	// response.addHeader(headerString, tokenPrefix + " " + JWT);
	try {
	    finalString = Base64.getEncoder().encodeToString(JWT.toString().getBytes("utf-8"));
	} catch (UnsupportedEncodingException e) {

	}
	response.addHeader(headerString, finalString);
    }

    public Authentication getAuthentication(HttpServletRequest request)
	    throws UnsupportedEncodingException, ExpiredJwtException {
	String token = request.getHeader(headerString);

	if (token != null) {
	    String username = null;

	    try {
		username = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
	    } catch (ExpiredJwtException e) {
		logger.error("Cannot authenticate user {}, cause: ", username, e.getMessage());
		throw new ExpiredJwtException(e.getHeader(), e.getClaims(), e.getMessage());
	    }

	    if (username != null) {
		logger.info("Get authentication from user {}", username);
		User user = userService.getUserByLogin(username);
		return new AuthenticatedUser(user);
	    }
	}
	return null;
    }

}
