/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rajtech.stockwebservice.service.Impl;

import com.rajtech.stockwebservice.dao.AbstractDao;
import com.rajtech.stockwebservice.dao.TaxDao;
import com.rajtech.stockwebservice.model.Tax;
import com.rajtech.stockwebservice.service.TaxService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author Rajkumar Ramasamy
 */
@Service("taxService")
@Transactional
public class TaxServiceImpl extends AbstractDao<Integer, Tax> implements TaxService{
    
    @Autowired
    private TaxDao taxDao;

    @Override
    public Tax findById(int id) {
        return taxDao.findById(id);
    }

    @Override
    public Tax findByName(String name) {
        return taxDao.findByName(name);
    }

    @Override
    public List<Tax> findByAll() {
        return taxDao.findAll();
    }

    @Override
    public void save(Tax tax) {
        taxDao.save(tax);
    }

    @Override
    public void update(Tax tax) {
        taxDao.update(tax);
    }

    @Override
    public List<Tax> findByPage(int page, int no_of_record) {
        return taxDao.findByPage(page, no_of_record);
    }

    @Override
    public int count() {
        return taxDao.count();
    }
    
}
