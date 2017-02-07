/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rajtech.stockwebservice.service.Impl;


import com.rajtech.stockwebservice.dao.AbstractDao;
import com.rajtech.stockwebservice.dao.PurchaseDao;
import com.rajtech.stockwebservice.model.PurchaseMaster;
import com.rajtech.stockwebservice.service.PurchaseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author node
 */
@Service ("purchaseService")
@Transactional
public class PurchaseServiceImpl extends AbstractDao<Integer, PurchaseMaster> implements PurchaseService{

    @Autowired
    PurchaseDao purchaseDao;
    
    @Override
    public PurchaseMaster findById(int id) {
        return purchaseDao.findById(id);
    }

    @Override
    public List<PurchaseMaster> findAll() {
        return purchaseDao.findAll();
    }

    @Override
    public void save(PurchaseMaster purchaseMaster) {
        purchaseDao.save(purchaseMaster);
    }

    @Override
    public void update(PurchaseMaster purchaseMaster) {
        purchaseDao.update(purchaseMaster);
    }

    @Override
    public void delete(String deleteBy, int id) {
        purchaseDao.delete(deleteBy, id);
    }

    @Override
    public List<PurchaseMaster> findByPage(int page, int no_of_record) {
        return purchaseDao.findByPage(page, no_of_record);
    }

    @Override
    public int countPage() {
        return purchaseDao.count();
    }
    
}
