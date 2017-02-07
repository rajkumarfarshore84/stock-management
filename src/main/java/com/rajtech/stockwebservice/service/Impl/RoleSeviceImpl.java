/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rajtech.stockwebservice.service.Impl;

import com.rajtech.stockwebservice.dao.RoleDao;
import com.rajtech.stockwebservice.model.Roles;
import com.rajtech.stockwebservice.service.RoleService;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author node
 */
@Service("roleService")
@Transactional
public class RoleSeviceImpl implements RoleService{
    
    @Autowired
    private RoleDao rolesDao;
    
    @Override
    public Roles findById(int id) {
        return rolesDao.findById(id);
    }

    @Override
    public Set<Roles> fildAllRoles() {
        return rolesDao.fildAllRoles();
    }

    @Override
    public List<Roles> getRoles() {
        return rolesDao.getRoles();
    }
    
}
