package org.aj.angular2.configuration.filter;

import org.aj.angular2.configuration.beans.SecureUser;
import org.aj.angular2.configuration.beans.UserAuthentication;
import org.aj.angular2.configuration.service.TokenAuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * StatelessLoginFilter
 *
 * @author Andre Jacobs
 */
public class StatelessLoginFilter extends AbstractAuthenticationProcessingFilter {

	private final TokenAuthenticationService tokenAuthenticationService;
	private final UserDetailsService userDetailsService;
	
	
	private boolean postOnly = true;

	public StatelessLoginFilter(String urlMapping, TokenAuthenticationService tokenAuthenticationService,
								UserDetailsService userDetailsService, AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(urlMapping));
		this.userDetailsService = userDetailsService;
		this.tokenAuthenticationService = tokenAuthenticationService;
		setAuthenticationManager(authManager);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
											Authentication authentication) throws IOException, ServletException {

		final SecureUser authenticatedUser = (SecureUser) userDetailsService.loadUserByUsername(authentication.getName());
		final UserAuthentication userAuthentication = new UserAuthentication(authenticatedUser);

		tokenAuthenticationService.addAuthentication(response, userAuthentication);

		SecurityContextHolder.getContext().setAuthentication(userAuthentication);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		if (postOnly && !request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		if (username == null) {
			username = "";
		}

		if (password == null) {
			password = "";
		}

		username = username.trim();

		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);

		return this.getAuthenticationManager().authenticate(authRequest);
	}
	
	@Override
	protected void unsuccessfulAuthentication(javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response, AuthenticationException failed) {
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
	}

}
