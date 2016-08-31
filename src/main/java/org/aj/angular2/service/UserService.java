package org.aj.angular2.service;

import org.aj.angular2.model.User;

import java.util.Collection;
import java.util.Optional;

/**
 * User Service
 *
 * @author Andre Jacobs
 */
public interface UserService {

    /**
     * Get the user by id
     * @param id
     * @return The User
     */
	Optional<User> getUserById(long id);

    /**
     * Get the user by email
     * @param email
     * @return The User
     */
    Optional<User> getUserByUserName(String email);

    /**
     * Get all user
     *
     * @return List of user
     */
    Collection<User> getAllUsers();

}
