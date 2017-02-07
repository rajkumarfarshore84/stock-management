/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rajtech.stockwebservice.dao;

import com.rajtech.stockwebservice.model.Users;
import java.util.List;

/**
 *
 * @author node
 */
public interface UserDao {
    Users findById(int id);

    void saveUser(Users user);

    List<Users> findAllUsers();

    Users findByName(String name);
}
