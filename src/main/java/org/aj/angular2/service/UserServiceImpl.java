package org.aj.angular2.service;

import org.aj.angular2.model.User;
import org.aj.angular2.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

/**
 * User service implementation
 *
 * @author Andre Jacobs
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public Optional<User> getUserById(long id) {
		return Optional.ofNullable(userRepository.findOne(id));

	}

	@Override
	public Optional<User> getUserByUserName(String name) {
		return Optional.ofNullable(userRepository.findByName(name));
	}

	@Override
	public Collection<User> getAllUsers() {
		return null;
	}

}
