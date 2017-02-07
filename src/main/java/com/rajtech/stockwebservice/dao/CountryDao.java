/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rajtech.stockwebservice.dao;

import com.rajtech.stockwebservice.model.Countries;
import java.util.List;

/**
 *
 * @author Rajkumar Ramasamy
 */
public interface CountryDao {

    Countries findById(int id);

    Countries findByName(String name);

    List<Countries> findByAll();


}
