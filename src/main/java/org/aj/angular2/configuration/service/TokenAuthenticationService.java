package org.aj.angular2.configuration.service;

import org.aj.angular2.configuration.beans.UserAuthentication;
import org.aj.angular2.configuration.handler.TokenHandler;
import org.aj.angular2.model.repository.ConfigurationRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Optional;

/**
 * The TokenAuthenticationService
 *
 * @author Andre Jacobs
 */
@Service
public class TokenAuthenticationService {
	
	private Logger log = Logger.getLogger(TokenAuthenticationService.class);

	private static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";

    private TokenHandler tokenHandler;
    
    @Autowired
    public TokenAuthenticationService(CustomUserDetailsService userService, ConfigurationRepository configRepository) {
    	String secret = configRepository.getSymetricKey();
    	tokenHandler = new TokenHandler(secret, userService);
    }

    /**
     * Add the token to the response header
     *
     * @param response
     * @param authentication
     */
    public void addAuthentication(HttpServletResponse response, UserAuthentication authentication) {
        final UserDetails user = authentication.getDetails();
        String token = tokenHandler.createTokenForUser(user);
        response.addHeader(AUTH_HEADER_NAME, token);
        Cookie cookie = new Cookie(AUTH_HEADER_NAME, token);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }

    /**
     * Verify the authentication of the user by the jwt
     *
     * @param request http request
     * @param response http response
     * @return Authentication or null when the token is not valid
     */
    public Authentication getAuthentication(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader(AUTH_HEADER_NAME);
        if (token == null || token.isEmpty()) {
            Optional<Cookie> cookie = Arrays.stream(request.getCookies()).filter(c -> AUTH_HEADER_NAME.equals(c.getName())).findFirst();
            if (cookie.isPresent()) {
                token = cookie.get().getValue();
            }
        }
        if (token != null) {
            final UserDetails user = tokenHandler.parseUserFromToken(token);
            if (user != null) {
            	UserAuthentication userAuthentication = new UserAuthentication(user);
            	response.addHeader(AUTH_HEADER_NAME, tokenHandler.createTokenForUser(user));
                return userAuthentication;
            }
        }
        return null;
    }
}
