package org.aj.angular2.configuration.service;

import org.aj.angular2.configuration.beans.SecureUser;
import org.aj.angular2.model.User;
import org.aj.angular2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Implementation of the spring UserDetailsService
 *
 * @author Andre Jacobs
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.getUserByUserName(username).orElseThrow(
				() -> new UsernameNotFoundException(String.format("User with username=%s was not found", username)));
		return new SecureUser(user);
	}

}
