package org.aj.angular2.model.repository;

import org.aj.angular2.configuration.beans.SecureUser;
import org.aj.angular2.configuration.role.Role;
import org.aj.angular2.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * User repository
 *
 * Created by andre on 09.08.16.
 * @author Andre Jacobs
 */
@Service
public class UserRepository {

    private List<User> userList;

    public UserRepository() {
        userList = new ArrayList<>();
        userList.add(getTestUser("josh", 1L, Role.USER));
    }

    public User findOne(long id){
        return new User();
    }

    public User findByName(String name) {
        Optional<User> user = userList.stream().filter(u -> u.getUserName().equals(name)).findFirst();
        if (user.isPresent()) {
            return user.get();
        }
        return null;
    }

    public User save(User user) {
        user.setId(userList.size() + 1L);
        userList.add(user);
        return user;
    }

    public void delete(SecureUser user) {
        userList.remove(user);
    }

    /**
     * Creates dummy user for testing
     *
     * @return User
     */
    private User getTestUser(String name, long id, Role role) {
        User user = new User();
        user.setUserName(name);
        user.setId(id);
        user.setPasswordHash("$2a$10$KAVa7TItVm.w7XHqCDo5uOqm9tlkcA2LBKp5fsLXEO738ijZVws7C");
        user.setRole(role);
        return user;
    }
}
