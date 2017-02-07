/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rajtech.stockwebservice.service.Impl;

import com.rajtech.stockwebservice.dao.AbstractDao;
import com.rajtech.stockwebservice.dao.StateDao;
import com.rajtech.stockwebservice.model.Countries;
import com.rajtech.stockwebservice.model.States;
import com.rajtech.stockwebservice.service.StatesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author Rajkumar Ramasamy
 */
@Service("stateService")
@Transactional
public class StateServiceImpl extends AbstractDao<Integer, States> implements StatesService{
    
    @Autowired
    private StateDao stateDao;

    @Override
    public States findById(int id) {
        return stateDao.findById(id);
    }

    @Override
    public States findByName(String name) {
        return stateDao.findByName(name);
    }

    @Override
    public List<States> findByAll() {
        return stateDao.findByAll();
    }

    @Override
    public List<States> findByCountry(Countries countries) {
        return stateDao.findByCountry(countries);
    }
    
}
