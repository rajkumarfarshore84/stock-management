/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rajtech.stockwebservice.service;

import com.rajtech.stockwebservice.model.Tax;
import java.util.List;

/**
 *
 * @author Rajkumar Ramasamy
 */
public interface TaxService {

    Tax findById(int id);

    Tax findByName(String name);

    List<Tax> findByAll();

    void save(Tax tax);

    void update(Tax tax);
    
    List<Tax> findByPage(int page, int no_of_record);
    
    int count();
}
