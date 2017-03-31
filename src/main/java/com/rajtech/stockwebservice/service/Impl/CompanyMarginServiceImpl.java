/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rajtech.stockwebservice.service.Impl;

import com.rajtech.stockwebservice.dao.AbstractDao;
import com.rajtech.stockwebservice.dao.CompanyMarginDao;
import com.rajtech.stockwebservice.model.CompanyMargin;
import com.rajtech.stockwebservice.service.CompanyMarginService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author Rajkumar Ramasamy
 */
@Service("companyMarginService")
@Transactional
public class CompanyMarginServiceImpl extends AbstractDao<Integer, CompanyMargin> implements CompanyMarginService{
    
    @Autowired
    CompanyMarginDao companyMarginDao;

    @Override
    public CompanyMargin findById(int id) {
        return companyMarginDao.findById(id);
    }

    @Override
    public List<CompanyMargin> findByAll() {
        return companyMarginDao.findByAll();
    }

    @Override
    public void save(CompanyMargin companyMargin) {
        companyMarginDao.save(companyMargin);
    }

    @Override
    public void update(CompanyMargin companyMargin) {
        companyMarginDao.update(companyMargin);
    }
    
}
