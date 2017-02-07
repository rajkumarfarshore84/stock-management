/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rajtech.stockwebservice.service;

import com.rajtech.stockwebservice.model.Products;
import com.rajtech.stockwebservice.model.StockPacking;
import java.util.List;

/**
 *
 * @author Rajkumar Ramasamy
 */
public interface StockPackingService {

    StockPacking findById(int id);

    StockPacking findByProduct(Products name);

    List<StockPacking> findByAll();

    void save(StockPacking stockPacking);

    void update(StockPacking stockPacking);
    
    List<StockPacking> findByPage(int page, int no_of_record);
    
    int count();
}
