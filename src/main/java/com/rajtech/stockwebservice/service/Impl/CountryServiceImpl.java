/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rajtech.stockwebservice.service.Impl;

import com.rajtech.stockwebservice.dao.AbstractDao;
import com.rajtech.stockwebservice.dao.CountryDao;
import com.rajtech.stockwebservice.dao.Impl.CountriesDaoImpl;
import com.rajtech.stockwebservice.model.Countries;
import com.rajtech.stockwebservice.service.CountryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author Rajkumar Ramasamy
 */
@Service("Service")
@Transactional
public class CountryServiceImpl extends AbstractDao<Integer, Countries> implements CountryService{
    
    @Autowired
    CountryDao countryDao;
    
    @Override
    public Countries findById(int id) {
        return countryDao.findById(id);
    }

    @Override
    public Countries findByName(String name) {
        return countryDao.findByName(name);
    }

    @Override
    public List<Countries> findByAll() {
        return countryDao.findByAll();
    }
    
}
