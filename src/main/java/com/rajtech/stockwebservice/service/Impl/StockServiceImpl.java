/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rajtech.stockwebservice.service.Impl;

import com.rajtech.stockwebservice.dao.AbstractDao;
import com.rajtech.stockwebservice.dao.StockDao;
import com.rajtech.stockwebservice.model.ManufacturedStocks;
import com.rajtech.stockwebservice.model.Products;
import com.rajtech.stockwebservice.service.StockService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author node
 */
@Service("stockService")
@Transactional
public class StockServiceImpl extends AbstractDao<Integer,ManufacturedStocks> implements StockService{
    
    @Autowired
    private StockDao stockDao;

    @Override
    public ManufacturedStocks findById(int id) {
        return stockDao.findById(id);
    }

    @Override
    public List<ManufacturedStocks> findAll() {
        return stockDao.findAll();
    }

    @Override
    public void save(ManufacturedStocks manufacturedStocks) {
        stockDao.save(manufacturedStocks);
    }

    @Override
    public void update(ManufacturedStocks manufacturedStocks) {
        stockDao.update(manufacturedStocks);
    }

    @Override
    public List<ManufacturedStocks> findByProductId(Products producct) {
        return stockDao.findByProductId(producct);
    }

    @Override
    public List<ManufacturedStocks> findByPage(int page, int no_of_record) {
        return stockDao.findByPage(page, no_of_record);
    }

    @Override
    public int count() {
        return stockDao.count();
    }
    
}
