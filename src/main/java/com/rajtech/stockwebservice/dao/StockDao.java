/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rajtech.stockwebservice.dao;

import com.rajtech.stockwebservice.model.ManufacturedStocks;
import com.rajtech.stockwebservice.model.Products;
import java.util.List;

/**
 *
 * @author node
 */
public interface StockDao {

    ManufacturedStocks findById(int id);

    List<ManufacturedStocks> findAll();

    void save(ManufacturedStocks manufacturedStocks);

    void update(ManufacturedStocks manufacturedStocks);

    List<ManufacturedStocks> findByProductId(Products producct);
    
    List<ManufacturedStocks> findByPage(int page, int no_of_record);
    
    int count();
}
