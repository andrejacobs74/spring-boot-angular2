package org.aj.angular2.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Main of the ui angular micro service
 *
 * @author Andre Jacobs
 * Created by andre on 09.08.16.
 */
@EnableAutoConfiguration
@ComponentScan(basePackages = {"org.aj"})
public class Application {

    public static void main(String ... args) {
        SpringApplication.run(Application.class, args);
    }
}
