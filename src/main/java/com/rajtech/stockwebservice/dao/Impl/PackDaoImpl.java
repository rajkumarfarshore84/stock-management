/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rajtech.stockwebservice.dao.Impl;

import com.rajtech.stockwebservice.dao.AbstractDao;
import com.rajtech.stockwebservice.dao.PackDao;
import com.rajtech.stockwebservice.model.PackMaster;
import com.rajtech.stockwebservice.model.Products;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author node
 */
@Repository("packDao")
public class PackDaoImpl extends AbstractDao<Integer, PackMaster> implements PackDao{

    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public PackMaster findById(int id) {
        return getByKey(id);
    }

    @Override
    public PackMaster findByProduct(Products products) {
        return null;
    }
  
    @Override
    public void save(PackMaster packMaster) {
        sessionFactory.getCurrentSession().save(packMaster);
    }

    @Override
    public List<PackMaster> findAllCategories() {
        List<PackMaster> packMasters = null;
        Criteria crit = createEntityCriteria();
        crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        packMasters = crit.list();
        return packMasters;
    }

    @Override
    public void update(PackMaster packMaster) {
       sessionFactory.getCurrentSession().update(packMaster);
    }

    @Override
    public List<PackMaster> findByPage(int page, int no_of_record) {
        List<PackMaster> packMasters = null;
        if (page == 0) {
            page = 1;
        }
        int indexPage = (page * no_of_record) - no_of_record;
        Criteria criteria = createEntityCriteria();
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.setFirstResult(indexPage);
        criteria.setMaxResults(no_of_record);
        packMasters = criteria.list();
        return packMasters;
    }

    @Override
    public int count() {
        int count = 0;
        Criteria criteria = createEntityCriteria();
        count = (int) (long) (criteria.setProjection(Projections.rowCount()).uniqueResult());
        return count;
    }

    
    
}
