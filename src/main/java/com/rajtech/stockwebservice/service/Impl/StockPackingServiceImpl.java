/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rajtech.stockwebservice.service.Impl;

import com.rajtech.stockwebservice.dao.AbstractDao;
import com.rajtech.stockwebservice.dao.StockPackingDao;
import com.rajtech.stockwebservice.model.ManufacturedStocks;
import com.rajtech.stockwebservice.model.Products;
import com.rajtech.stockwebservice.model.StockPacking;
import com.rajtech.stockwebservice.service.StockPackingService;
import com.rajtech.stockwebservice.service.StockService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author Rajkumar Ramasamy
 */
@Service("stockPackingService")
@Transactional
public class StockPackingServiceImpl extends AbstractDao<Integer, StockPacking> implements StockPackingService{
    
    @Autowired
    StockPackingDao stockPackingDao;

    @Override
    public StockPacking findById(int id) {
        return stockPackingDao.findById(id);
    }

    @Override
    public StockPacking findByProduct(Products name) {
        return stockPackingDao.findByProduct(name);
    }

    @Override
    public List<StockPacking> findByAll() {
        return stockPackingDao.findAll();
    }

    @Override
    public void save(StockPacking stockPacking) {
        stockPackingDao.save(stockPacking);
    }

    @Override
    public void update(StockPacking stockPacking) {
        stockPackingDao.update(stockPacking);
    }

    @Override
    public List<StockPacking> findByPage(int page, int no_of_record) {
        return stockPackingDao.findByPage(page, no_of_record);
    }

    @Override
    public int count() {
        return stockPackingDao.count();
    }
   
}
