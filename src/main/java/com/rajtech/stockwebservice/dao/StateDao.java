/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rajtech.stockwebservice.dao;

import com.rajtech.stockwebservice.model.Countries;
import com.rajtech.stockwebservice.model.States;
import java.util.List;

/**
 *
 * @author Rajkumar Ramasamy
 */
public interface StateDao {

    States findById(int id);

    States findByName(String name);

    List<States> findByAll();

    List<States> findByCountry(Countries countries);
}
