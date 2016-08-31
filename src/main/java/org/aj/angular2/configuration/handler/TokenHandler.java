package org.aj.angular2.configuration.handler;

import io.jsonwebtoken.*;
import org.aj.angular2.configuration.service.CustomUserDetailsService;
import org.apache.log4j.Logger;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

/**
 * This class handle the Java Web Token
 *
 * @author Andre Jacobs
 */
public class TokenHandler {
	
	private Logger log = Logger.getLogger(TokenHandler.class);

	private final String secret;
	private final CustomUserDetailsService customUserDetailsService;

	public TokenHandler(String secret, CustomUserDetailsService customUserDetailsService) {
		this.secret = secret;
		this.customUserDetailsService = customUserDetailsService;
	}

	/**
	 * Parse the jwt
	 *
	 * @param token
	 * @return
     */
	public UserDetails parseUserFromToken(String token) {
		String username = null;
		try {
			Jws<Claims> jwtClaims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			Claims claims = jwtClaims.getBody();
			username = claims.getSubject();
			Date date = claims.getExpiration();
		}
		catch (ExpiredJwtException | SignatureException ex) {
			log.error("Exception during token parsing");
			if (ex instanceof ExpiredJwtException) {
				log.error("Token has been expired");
				throw ex;
			}
		}
		return customUserDetailsService.loadUserByUsername(username);
	}

	/**
	 * Create a new token
	 * @param user Current User
	 * @return The generatet token
     */
	public String createTokenForUser(UserDetails user) {
		String token = Jwts.builder().setSubject(user.getUsername()).setExpiration(new Date(System.currentTimeMillis() + (60 * 1000 * 2))).signWith(SignatureAlgorithm.HS512, secret).compact();
		return token;
	}

}
