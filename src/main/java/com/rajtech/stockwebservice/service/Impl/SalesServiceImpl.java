/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rajtech.stockwebservice.service.Impl;

import com.rajtech.stockwebservice.dao.AbstractDao;
import com.rajtech.stockwebservice.dao.SalesDao;
import com.rajtech.stockwebservice.model.Products;
import com.rajtech.stockwebservice.model.SalesMaster;
import com.rajtech.stockwebservice.service.SalesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Rajkumar Ramasamy
 */
@Service("salesService")
@Transactional
public class SalesServiceImpl extends AbstractDao<Integer, SalesMaster> implements SalesService{
    
    @Autowired
    SalesDao salesDao;

    @Override
    public SalesMaster findById(int id) {
        return salesDao.findById(id);
    }

    @Override
    public SalesMaster findByProduct(Products name) {
        return salesDao.findByProduct(name);
    }

    @Override
    public List<SalesMaster> findByAll() {
        return salesDao.findAll();
    }

    @Override
    public void save(SalesMaster salesMaster) {
        salesDao.save(salesMaster);
    }

    @Override
    public void update(SalesMaster salesMaster) {
        salesDao.update(salesMaster);
    }

    @Override
    public List<SalesMaster> findByPage(int page, int no_of_record) {
        return salesDao.findByPage(page, no_of_record);
    }

    @Override
    public int count() {
        return salesDao.count();
    }
    
}
