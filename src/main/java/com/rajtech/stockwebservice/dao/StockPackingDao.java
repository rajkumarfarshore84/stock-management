/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rajtech.stockwebservice.dao;

import com.rajtech.stockwebservice.model.Products;
import com.rajtech.stockwebservice.model.StockPacking;
import java.util.List;

/**
 *
 * @author node
 */
public interface StockPackingDao {

    StockPacking findById(int id);

    StockPacking findByProduct(Products name);

    void save(StockPacking stockPacking);

    List<StockPacking> findAll();

    void update(StockPacking stockPacking);
    
    List<StockPacking> findByPage(int page, int no_of_record);
    
    int count();
}
