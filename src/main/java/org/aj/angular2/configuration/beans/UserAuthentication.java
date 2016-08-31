package org.aj.angular2.configuration.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Implementation of the spring authentication
 *
 * @author Andre Jacobs
 */
public class UserAuthentication implements Authentication {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final UserDetails user;
    private boolean authenticated = true;
    
    public UserAuthentication(){
    	this.user = null;
    }

    public UserAuthentication(UserDetails user) {
        this.user = user;
    }

     @Override
    public String getName() {
        return user.getUsername();
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getAuthorities();
    }

    @JsonIgnore
    @Override
    public Object getCredentials() {
        return user.getPassword();
    }

     @Override
    public UserDetails getDetails() {
        return user;
    }

     @Override
    public Object getPrincipal() {
        return user.getUsername();
    }

     @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

     @Override
    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }
}
