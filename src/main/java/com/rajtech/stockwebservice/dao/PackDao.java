/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rajtech.stockwebservice.dao;

import com.rajtech.stockwebservice.model.PackMaster;
import com.rajtech.stockwebservice.model.Products;
import java.util.List;

/**
 *
 * @author node
 */
public interface PackDao {

    PackMaster findById(int id);

    PackMaster findByProduct(Products products);

    void save(PackMaster packMaster);

    List<PackMaster> findAllCategories();

    void update(PackMaster packMaster);
    
    List<PackMaster> findByPage(int page, int no_of_record);
    
    int count();
}
