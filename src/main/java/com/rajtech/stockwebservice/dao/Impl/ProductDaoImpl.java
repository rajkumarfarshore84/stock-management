/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rajtech.stockwebservice.dao.Impl;

import com.rajtech.stockwebservice.dao.AbstractDao;
import com.rajtech.stockwebservice.dao.ProductDao;
import com.rajtech.stockwebservice.model.Categories;
import com.rajtech.stockwebservice.model.Products;
import com.rajtech.stockwebservice.model.Users;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author node
 */
@Repository("productDao")
public class ProductDaoImpl extends AbstractDao<Integer, Products> implements ProductDao{

    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public Products findById(int id) {
        return getByKey(id);
    }

    @Override
    public List<Products> findAll() {
        List<Products> products = null;
        Criteria crit = createEntityCriteria();
        crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        products = crit.list();
        return products;
    }

    @Override
    public void save(Products product) {
        sessionFactory.getCurrentSession().save(product);
    }

    @Override
    public void update(Products product) {
        sessionFactory.getCurrentSession().update(product);
    }

    @Override
    public List<Products> findByCategory(Categories category_id) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("categoryId", category_id));
        return ((List<Products>) criteria.list());
    }
    
}
