package org.aj.angular2.configuration;

import org.aj.angular2.configuration.filter.StatelessAuthenticationFilter;
import org.aj.angular2.configuration.filter.StatelessLoginFilter;
import org.aj.angular2.configuration.service.TokenAuthenticationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import javax.servlet.http.HttpServletRequest;

/**
 * Security configuration
 *
 * @author Andre Jacobs
 * Created by andre on 09.08.16.
 */

@Configuration
@EnableWebSecurity
@Order(SecurityProperties.IGNORED_ORDER)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private Logger log = Logger.getLogger(SecurityConfiguration.class);

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private TokenAuthenticationService tokenAuthenticationService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**", "/lib/**", "/app/**", "/loginForm", "/index.html","/partials/**", "/error/**", "/*.js", "/favicon.ico");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .exceptionHandling()
        .authenticationEntryPoint((request, response, exception) -> {

            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            log.info("authenticationEntryPoint " + exception);
            if (httpServletRequest.getRequestURI().contains("/rest")) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
            } else {
                response.sendRedirect("/loginForm");
            }
        })
        .accessDeniedHandler((request, response, accessDeniedException) -> {
            log.info("accessDeniedHandler " + accessDeniedException);
            response.setStatus(HttpStatus.FORBIDDEN.value());
        })
        .and()
        .authorizeRequests()
        .antMatchers("/loginForm", "/index.html").permitAll()
        .anyRequest().hasRole("USER")
        .anyRequest().authenticated()
        .and()
        .formLogin()
            .loginPage("/loginForm")
            .permitAll()
        .and()
        .logout()
            .logoutUrl("/logout")
            .logoutSuccessHandler(getLogoutSuccessHandler())
            .invalidateHttpSession(true)
            .permitAll()
        .and()
            .csrf()
            .disable()
        .addFilterBefore(new StatelessLoginFilter("/login", tokenAuthenticationService, userDetailsService, authenticationManager()), UsernamePasswordAuthenticationFilter.class)
        .addFilterBefore(new StatelessAuthenticationFilter(tokenAuthenticationService), UsernamePasswordAuthenticationFilter.class);

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    /**
     * Logout handler
     *
     * @return LogoutSuccessHandler
     */
    private LogoutSuccessHandler getLogoutSuccessHandler() {
        return (request, response, authentication) -> {
            log.info("logout successful");
            response.setStatus(HttpStatus.OK.value());
            redirectStrategy.sendRedirect(request, response, "/loginForm");
        };
    }
}

