/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rajtech.stockwebservice.service;

import com.rajtech.stockwebservice.model.Roles;
import com.rajtech.stockwebservice.model.Users;
import com.rajtech.stockwebservice.utilities.HashSecure;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author node
 */
@Service("customUserDetailsService")
public class CustomUserDetailsService extends Users implements UserDetailsService{

    @Autowired
    private UserService userService;
    
    @Autowired
    private RoleService roleService;
    
    private HashSecure hashSecure;
    
    @Override
    public UserDetails loadUserByUsername(String name)  throws UsernameNotFoundException {
        Users user = userService.findByName(name);
        List<Roles> roles = roleService.getRoles();
        if(user==null){
            System.out.println("User not found");
            throw new UsernameNotFoundException("Username not found");
        }
        String pass = "";
        try {
            pass = hashSecure.decrypt(user.getPassword());
            System.out.println("Login Password:::"+pass+"User Name:"+user.getUsername()+"Role:"+user.getRoleId().getRoleName());
        } catch (Exception ex) {
            Logger.getLogger(CustomUserDetailsService.class.getName()).log(Level.SEVERE, null, ex);
        }
        org.springframework.security.core.userdetails.User userAuth = new org.springframework.security.core.userdetails.User(user.getUsername(), pass,true,true,true,true, getGrantedAuthorities(roles,user.getRoleId().getRoleName()));
        Authentication authentication =  new UsernamePasswordAuthenticationToken(userAuth, null,
        userAuth.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return userAuth;
    }
    
    private List<GrantedAuthority> getGrantedAuthorities(List<Roles> roles,String currentRole){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
         //System.out.println("UserProfile : "+roles);
        for(Roles role : roles){
            System.out.println("UserProfile : "+currentRole);
            if(currentRole.equals(role.getRoleName())){
                authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
            }
        }
        System.out.print("authorities :"+authorities);
        return authorities;
    }
    
}
