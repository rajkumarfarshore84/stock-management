/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rajtech.stockwebservice.dao;

import com.rajtech.stockwebservice.model.Tax;
import java.util.List;

/**
 *
 * @author node
 */
public interface TaxDao {

    Tax findById(int id);

    Tax findByName(String name);

    void save(Tax tax);

    List<Tax> findAll();

    void update(Tax tax);
    
    List<Tax> findByPage(int page, int no_of_record);
    
    int count();
}
