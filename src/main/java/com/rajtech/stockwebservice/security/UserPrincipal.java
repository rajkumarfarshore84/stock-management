/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rajtech.stockwebservice.security;

import com.rajtech.stockwebservice.model.Roles;
import com.rajtech.stockwebservice.model.Users;
import com.rajtech.stockwebservice.service.CustomUserDetailsService;
import com.rajtech.stockwebservice.service.RoleService;
import com.rajtech.stockwebservice.utilities.HashSecure;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author node
 */
public class UserPrincipal extends Users implements UserDetails{
    
    private HashSecure hashSecure;

    @Autowired
    private RoleService roleService;

    public UserPrincipal(Users user) {
        this.setUsername(user.getUsername());
        this.setPassword(user.getPassword());
        this.setRoleId(user.getRoleId());
        UserPrincipal(user);
    }
    
    public org.springframework.security.core.userdetails.User UserPrincipal(Users user){
        this.setUsername(user.getUsername());
        this.setPassword(user.getPassword());
        this.setRoleId(user.getRoleId());
        
        String pass = "";
        try {
            pass = hashSecure.decrypt(user.getPassword());
        } catch (Exception ex) {
            Logger.getLogger(CustomUserDetailsService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), pass,true,true,true,true, getAuthorities());
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
         //System.out.println("UserProfile : "+roles);
        List<Roles> roles = roleService.getRoles();
        for(Roles role : roles){
            if(this.getRoleId().getRoleName().equals(role.getRoleName())){
                authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
            }
        }
        System.out.print("authorities :"+authorities);
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
