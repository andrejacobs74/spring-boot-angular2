package org.aj.angular2.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

/**
 * Route controller for angular2 single page requests
 *
 * @author Andre Jacobs
 * Created by andre on 05.07.16.
 */
@Controller
public class RoutesController {

    @RequestMapping({
            "/home",
            "/admin",
            "/loginForm"
    })
    public String index() {
        return "forward:/index.html";
    }

}
