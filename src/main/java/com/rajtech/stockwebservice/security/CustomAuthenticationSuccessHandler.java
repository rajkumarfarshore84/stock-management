/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rajtech.stockwebservice.security;


import com.rajtech.stockwebservice.model.Users;
import com.rajtech.stockwebservice.service.UserService;
import java.io.IOException;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 *
 * @author node
 */
@Configuration
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    
    @Autowired
    private UserService userService;
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        HttpSession session = request.getSession();

        /*Set some session variables*/
        User authUser = (User) authentication.getPrincipal();
        
        Users userLogged = userService.findByName(authUser.getUsername());
        session.setAttribute("loggeduser", userLogged);
        session.setAttribute("uname", userLogged.getCreatedAt());
        session.setAttribute("udetail", userLogged);
        
       
        session.setAttribute("authorities", authentication.getAuthorities());
        //authentication.get

        /*Set target URL to redirect*/
        String targetUrl = determineTargetUrl(authentication);
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    protected String determineTargetUrl(Authentication authentication) {
        Set<String> authorities = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        System.out.println(authentication);
        if (authorities.contains("ROLE_ADMIN")) {
        	return "/admin/dashboard";
        } else if (authorities.contains("ROLE_USER")) {
        	return "/user/dashboard";
        } else if (authorities.contains("ROLE_SUPERVISOR")) {
        	return "/supervisor/dashboard";
        } else if (authorities.contains("ROLE_ACCOUNTANT")) {
        	return "/accountant/dashboard";
        }
        else {
            throw new IllegalStateException();
        }
    }
 
	public RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}
 
	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}
}
