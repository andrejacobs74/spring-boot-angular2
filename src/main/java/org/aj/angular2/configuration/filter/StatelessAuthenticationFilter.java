package org.aj.angular2.configuration.filter;

import io.jsonwebtoken.ExpiredJwtException;
import org.aj.angular2.configuration.service.TokenAuthenticationService;
import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * StatelessAuthenticationFilter
 *
 * @author Andre Jacobs
 */
public class StatelessAuthenticationFilter extends GenericFilterBean {
	
	private Logger log = Logger.getLogger(StatelessAuthenticationFilter.class);

	private final TokenAuthenticationService authenticationService;

	public StatelessAuthenticationFilter(TokenAuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		Authentication authentication = null;
		try {
			authentication = authenticationService.getAuthentication(httpRequest, httpResponse);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}catch (Exception ex) {
			if (ex instanceof ExpiredJwtException) {
				log.error("Authentication of the user failed. " + ex.getMessage());
			}
		}

		filterChain.doFilter(request, httpResponse);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		SecurityContextHolder.getContext().setAuthentication(null);
	}
}
