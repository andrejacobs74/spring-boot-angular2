package org.aj.angular2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.aj.angular2.configuration.role.Role;

/**
 * This User class simulate a JPA entity.
 *
 * @author andre
 *
 */
public class User {

    private Long id;

    private String userName;

    @JsonIgnore
    private String passwordHash;

    private Role role;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

    public static User createUser() {
    	return new User();
    }

}
