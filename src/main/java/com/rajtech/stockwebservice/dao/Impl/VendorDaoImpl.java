/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rajtech.stockwebservice.dao.Impl;


import com.rajtech.stockwebservice.dao.AbstractDao;
import com.rajtech.stockwebservice.dao.VendorDao;
import com.rajtech.stockwebservice.model.Vendors;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author node
 */
@Repository("vendorDao")
public class VendorDaoImpl extends AbstractDao<Integer, Vendors> implements VendorDao{
    
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Vendors findById(int id) {
        return getByKey(id);
    }

    @Override
    public List<Vendors> findAll() {
        List<Vendors> vendors = null;
        Criteria criteria = createEntityCriteria();
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        vendors = criteria.list();
        return vendors;
    }

    @Override
    public void save(Vendors vendors) {
        sessionFactory.getCurrentSession().save(vendors);
    }

    @Override
    public void update(Vendors vendors) {
        sessionFactory.getCurrentSession().update(vendors);
    }

    @Override
    public void delete(int id) {
        sessionFactory.getCurrentSession().update("status", id);
    }
    
}
