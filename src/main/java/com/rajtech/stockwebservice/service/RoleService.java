/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rajtech.stockwebservice.service;

import com.rajtech.stockwebservice.model.Roles;
import java.util.List;
import java.util.Set;

/**
 *
 * @author node
 */
public interface RoleService {
    Roles findById(int id);
    Set<Roles> fildAllRoles();
    List<Roles> getRoles();
}
