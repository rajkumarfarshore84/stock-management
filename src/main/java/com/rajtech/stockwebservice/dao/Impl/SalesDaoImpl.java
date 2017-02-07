/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rajtech.stockwebservice.dao.Impl;

import com.rajtech.stockwebservice.dao.AbstractDao;
import com.rajtech.stockwebservice.dao.SalesDao;
import com.rajtech.stockwebservice.model.Products;
import com.rajtech.stockwebservice.model.SalesMaster;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Rajkumar Ramasamy
 */
@Repository("Dao")
public class SalesDaoImpl extends AbstractDao<Integer, SalesMaster> implements SalesDao{
    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public SalesMaster findById(int id) {
        return getByKey(id);
    }

    @Override
    public SalesMaster findByProduct(Products products) {
        return null;
    }
  
    @Override
    public void save(SalesMaster salesMaster) {
        sessionFactory.getCurrentSession().save(salesMaster);
    }

    @Override
    public List<SalesMaster> findAll() {
        List<SalesMaster> salesMasters = null;
        Criteria crit = createEntityCriteria();
        crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        salesMasters = crit.list();
        return salesMasters;
    }

    @Override
    public void update(SalesMaster salesMaster) {
       sessionFactory.getCurrentSession().update(salesMaster);
    }

    @Override
    public List<SalesMaster> findByPage(int page, int no_of_record) {
        List<SalesMaster> salesMasters = null;
        if (page == 0) {
            page = 1;
        }
        int indexPage = (page * no_of_record) - no_of_record;
        Criteria criteria = createEntityCriteria();
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.setFirstResult(indexPage);
        criteria.setMaxResults(no_of_record);
        salesMasters = criteria.list();
        return salesMasters;
    }

    @Override
    public int count() {
        int count = 0;
        Criteria criteria = createEntityCriteria();
        count = (int) (long) (criteria.setProjection(Projections.rowCount()).uniqueResult());
        return count;
    }
}
