package org.aj.angular2.controller;

import org.aj.angular2.model.User;
import org.aj.angular2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Rest authentication controller
 *
 * Created by andre on 16.07.16.
 * @author Andre Jacobs
 */
@RestController
@RequestMapping("/rest")
public class AuthenticationController {

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/auth", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User authenticateUser() {
        return userService.getUserByUserName(httpServletRequest.getUserPrincipal().getName()).get();
    }
}
