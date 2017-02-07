/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rajtech.stockwebservice.controllers;

import com.rajtech.stockwebservice.utilities.HashSecure;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

/**
 *
 * @author node
 */
@Controller
public class HomeController {
    
    private HashSecure hashSecure;
    
    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String homePage(ModelMap model) throws Exception {
        System.out.println(hashSecure.encrypt("test123"));
        model.addAttribute("greeting", "Hi, Welcome to mysite");
        return "login";
    }

    @RequestMapping(value = "/admin/dashboard", method = RequestMethod.GET)
    public String adminPage(ModelMap model) {
        model.addAttribute("user", getPrincipal());
        return "admin";
    }
    @RequestMapping(value = "/supervisor/dashboard", method = RequestMethod.GET)
    public String supervisorPage(ModelMap model) {
        model.addAttribute("user", getPrincipal());
        return "supervisor";
    }
    @RequestMapping(value = "/accountant/dashboard", method = RequestMethod.GET)
    public String accountantPage(ModelMap model) {
        model.addAttribute("user", getPrincipal());
        return "accountant";
    }
    @RequestMapping(value = "/user/dashboard", method = RequestMethod.GET)
    public String userPage(ModelMap model) {
        model.addAttribute("user", getPrincipal());
        return "user";
    }

    @RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        model.addAttribute("user", getPrincipal());
        return "accessDenied";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!auth.getName().equals("anonymousUser")) {
            User authUser = (User) auth.getPrincipal();
            Set<String> authorities = AuthorityUtils.authorityListToSet(authUser.getAuthorities());
            if (authorities.contains("ROLE_ADMIN")) {
                    return "redirect:/admin";
            } else if (authorities.contains("ROLE_USER")) {
                    return "redirect:/user";
            } else if (authorities.contains("ROLE_DBA")) {
                    return "redirect:/db";
            }else {
                return "redirect:/";
            }
        }
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response,SessionStatus status) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
            status.setComplete();
            request.getSession().removeAttribute("udetails");
        }
        return "redirect:/login?logout";
    }

    private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
}
