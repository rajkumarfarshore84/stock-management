/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rajtech.stockwebservice.dao.Impl;

import com.rajtech.stockwebservice.dao.AbstractDao;
import com.rajtech.stockwebservice.dao.StateDao;
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
@Repository("stateDao")
public class StatesDaoImpl extends AbstractDao<Integer, States> implements StateDao{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public States findById(int id) {
        return getByKey(id);
    }

    @Override
    public States findByName(String name) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("name", name));
        return (States) crit.uniqueResult();
    }

    @Override
    public List<States> findByAll() {
        List<States> stateses = null;
        Criteria criteria = createEntityCriteria();
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        stateses = criteria.list();
        return stateses;
    }

    @Override
    public List<States> findByCountry(Countries countries) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("countryId", countries));
        return ((List<States>) criteria.list());
    }
    
}
