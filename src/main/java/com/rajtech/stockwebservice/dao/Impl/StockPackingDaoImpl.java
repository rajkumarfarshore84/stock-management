/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rajtech.stockwebservice.dao.Impl;

import com.rajtech.stockwebservice.dao.AbstractDao;
import com.rajtech.stockwebservice.dao.StockPackingDao;
import com.rajtech.stockwebservice.model.PackagesSize;
import com.rajtech.stockwebservice.model.Products;
import com.rajtech.stockwebservice.model.StockPacking;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Rajkumar Ramasamy
 */
@Repository("stockPackingDao")
public class StockPackingDaoImpl extends AbstractDao<Integer, StockPacking> implements StockPackingDao{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public StockPacking findById(int id) {
        return getByKey(id);
    }

    @Override
    public StockPacking findByProduct(Products name) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("name", name) );
        return (StockPacking) crit.uniqueResult();
    }

    @Override
    public void save(StockPacking stockPacking) {
        sessionFactory.getCurrentSession().save(stockPacking);
    }

    @Override
    public List<StockPacking> findAll() {
        List<StockPacking> stockPackings = null;
        Criteria crit = createEntityCriteria();
        crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        stockPackings = crit.list();
        return stockPackings;
        
    }

    @Override
    public void update(StockPacking stockPacking) {
        sessionFactory.getCurrentSession().update(stockPacking);
    }

    @Override
    public List<StockPacking> findByPage(int page, int no_of_record) {
        List<StockPacking> stockPackings = null;
        if (page == 0) {
            page = 1;
        }
        int indexPage = (page * no_of_record) - no_of_record;
        Criteria criteria = createEntityCriteria();
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.setFirstResult(indexPage);
        criteria.setMaxResults(no_of_record);
        stockPackings = criteria.list();
        return stockPackings;
    }

    @Override
    public int count() {
        int count = 0;
        Criteria criteria = createEntityCriteria();
        count = (int) (long) (criteria.setProjection(Projections.rowCount()).uniqueResult());
        return count;
    }
    
}
