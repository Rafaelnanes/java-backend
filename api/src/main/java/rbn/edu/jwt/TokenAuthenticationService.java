package rbn.edu.jwt;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import rbn.edu.model.UserLevel;
import rbn.edu.service.IUserService;

@Service
public class TokenAuthenticationService {

    @Autowired
    private IUserService userService;

    private long EXPIRATIONTIME = 1000 * 60 * 60 * 24; // 1 day
    private String secret = "ThisIsASecret";
    // private String tokenPrefix = "Bearer";
    private String headerString = "Authorization";
    private final String TOKEN = "//";

    public void addAuthentication(HttpServletResponse response, String username) {
	String finalString = "";
	StringBuilder JWT = new StringBuilder();
	for (UserLevel level : userService.getUserByLogin(username).getUserLevels()) {
	    JWT.append(level.getAuthority());
	    JWT.append(",");
	}
	JWT.delete(JWT.length() - 1, JWT.length());// remove the last ','
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

    public Authentication getAuthentication(HttpServletRequest request) throws UnsupportedEncodingException {
	String token = request.getHeader(headerString);

	if (token != null) {
	    String username = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
	    if (username != null) {
		return new AuthenticatedUser(username);
	    }
	}
	return null;
    }

}
