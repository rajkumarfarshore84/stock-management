/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rajtech.stockwebservice.security;


import javax.faces.component.behavior.AjaxBehavior;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author node
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    
    @Autowired
    @Qualifier("customUserDetailsService")
    UserDetailsService userDetailsService;
    
    @Autowired
    CustomAuthenticationSuccessHandler authenticationSuccessHandler;
    
    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
     
    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http.authorizeRequests()
        .antMatchers("/", "/home","loginprcess").permitAll()
        .antMatchers("/admin","/admin/**","/users/**","/categories/**").access("hasRole('ADMIN')")
        .antMatchers("/users/**").access("hasRole('USER')")
        .antMatchers("/supervisor/**").access("hasRole('SUPERVISOR')")
        .and().formLogin().loginPage("/login")
        .usernameParameter("username").passwordParameter("password")
        .successHandler(authenticationSuccessHandler)
        .and().csrf()
        .and().exceptionHandling().accessDeniedPage("/Access_Denied");
    }
}
