/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rajtech.stockwebservice.service.Impl;


import com.rajtech.stockwebservice.dao.UserDao;
import com.rajtech.stockwebservice.model.Users;
import com.rajtech.stockwebservice.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author node
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao dao;
    
    @Override
    public Users findById(int id) {
        return dao.findById(id);
    }

    @Override
    public void saveUser(Users user) {
        dao.saveUser(user);
    }

    @Override
    public List<Users> findAllUsers() {
        return dao.findAllUsers();
    }

    @Override
    public Users findByName(String name) {
        return dao.findByName(name);
    }

    @Override
    public void updateUser(Users user) {
        Users entity = dao.findById(user.getId());
        if(entity!=null){
            entity.setUsername(user.getUsername());
        }
    }

    @Override
    public boolean isUserUnique(Integer id, String username) {
        Users user = findByName(username);
        return ( user == null || ((id != null) && (user.getId() == id)));
    }
}
