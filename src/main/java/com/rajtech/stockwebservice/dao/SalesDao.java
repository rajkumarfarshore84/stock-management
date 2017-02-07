/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rajtech.stockwebservice.dao;

import com.rajtech.stockwebservice.model.Products;
import com.rajtech.stockwebservice.model.SalesMaster;
import java.util.List;

/**
 *
 * @author node
 */
public interface SalesDao {

    SalesMaster findById(int id);

    SalesMaster findByProduct(Products name);

    void save(SalesMaster salesMaster);

    List<SalesMaster> findAll();

    void update(SalesMaster salesMaster);
    
    List<SalesMaster> findByPage(int page, int no_of_record);
    
    int count();
}
