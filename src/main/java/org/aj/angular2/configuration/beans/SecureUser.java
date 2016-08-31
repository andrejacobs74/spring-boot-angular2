package org.aj.angular2.configuration.beans;


import org.aj.angular2.configuration.role.Role;
import org.aj.angular2.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;

/**
 * This class represents a secured user
 *
 * @author Andre Jacobs
 */
public class SecureUser extends org.springframework.security.core.userdetails.User {

    private User user;

    private static final long serialVersionUID = 1L;

    public SecureUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);

    }

    public SecureUser(User user) {
        this(user.getUserName(), user.getPasswordHash(), AuthorityUtils.createAuthorityList(user.getRole().toString()));
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public Long getId() {
        return user.getId();
    }

    public Role getRole() {
        return user.getRole();
    }
}
