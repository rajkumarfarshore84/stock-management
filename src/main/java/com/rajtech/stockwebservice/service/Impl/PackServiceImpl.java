/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rajtech.stockwebservice.service.Impl;

/**
 *
 * @author node
 */


import com.rajtech.stockwebservice.dao.AbstractDao;
import com.rajtech.stockwebservice.dao.PackDao;
import com.rajtech.stockwebservice.model.PackMaster;
import com.rajtech.stockwebservice.model.Products;
import com.rajtech.stockwebservice.service.PackService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service("packService")
@Transactional
public class PackServiceImpl extends AbstractDao<Integer, PackMaster> implements PackService{

    @Autowired
    private PackDao packDao;
    
    @Override
    public PackMaster findById(int id) {
        return packDao.findById(id);
    }

    @Override
    public PackMaster findByProduct(Products products) {
        return packDao.findByProduct(products);
    }

    @Override
    public void save(PackMaster packMaster) {
        packDao.save(packMaster);
    }

    @Override
    public List<PackMaster> findAllCategories() {
        return packDao.findAllCategories();
    }

    @Override
    public void update(PackMaster packMaster) {
        packDao.update(packMaster);
    }

    @Override
    public List<PackMaster> findByPage(int page, int no_of_record) {
        return packDao.findByPage(page, no_of_record);
    }

    @Override
    public int count() {
        return packDao.count();
    }
    
}
