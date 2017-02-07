/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rajtech.stockwebservice.dao.Impl;

import com.rajtech.stockwebservice.dao.AbstractDao;
import com.rajtech.stockwebservice.dao.StockDao;
import com.rajtech.stockwebservice.model.ManufacturedStocks;
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
@Repository("stockDao")
public class StockImpl extends AbstractDao<Integer, ManufacturedStocks> implements StockDao{

    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public ManufacturedStocks findById(int id) {
        return getByKey(id);
    }

    @Override
    public List<ManufacturedStocks> findAll() {
        List<ManufacturedStocks> manufacturedStocks = null;
        Criteria crit = createEntityCriteria();
        crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        manufacturedStocks = crit.list();
        return manufacturedStocks;
    }

    @Override
    public void save(ManufacturedStocks manufacturedStocks) {
        sessionFactory.getCurrentSession().save(manufacturedStocks);
    }

    @Override
    public void update(ManufacturedStocks manufacturedStocks) {
        sessionFactory.getCurrentSession().update(manufacturedStocks);
    }

    @Override
    public List<ManufacturedStocks> findByProductId(Products producct) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("categoryId", producct));
        return ((List<ManufacturedStocks>) criteria.list());
    }

    @Override
    public List<ManufacturedStocks> findByPage(int page, int no_of_record) {
        List<ManufacturedStocks> manufacturedStockses = null;
        if (page == 0) {
            page = 1;
        }
        int indexPage = (page * no_of_record) - no_of_record;
        Criteria criteria = createEntityCriteria();
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.setFirstResult(indexPage);
        criteria.setMaxResults(no_of_record);
        manufacturedStockses = criteria.list();
        return manufacturedStockses;
    }

    @Override
    public int count() {
        int count = 0;
        Criteria criteria = createEntityCriteria();
        count = (int) (long) (criteria.setProjection(Projections.rowCount()).uniqueResult());
        return count;
    }
    
}
