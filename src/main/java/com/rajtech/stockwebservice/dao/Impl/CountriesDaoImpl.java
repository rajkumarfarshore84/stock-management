/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rajtech.stockwebservice.dao.Impl;

import com.rajtech.stockwebservice.dao.AbstractDao;
import com.rajtech.stockwebservice.dao.CountryDao;
import com.rajtech.stockwebservice.model.Countries;
import com.rajtech.stockwebservice.model.States;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Rajkumar Ramasamy
 */
@Repository("countriesDao")
public class CountriesDaoImpl extends AbstractDao<Integer, Countries> implements CountryDao{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Countries findById(int id) {
        return getByKey(id);
    }

    @Override
    public Countries findByName(String name) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("name", name));
        return (Countries) crit.uniqueResult();
    }

    @Override
    public List<Countries> findByAll() {
         List<Countries> countryList = null;
        Criteria criteria = createEntityCriteria();
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        countryList = criteria.list();
        return countryList;
    }
    
}
