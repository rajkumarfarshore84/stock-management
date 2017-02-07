/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rajtech.stockwebservice.dao.Impl;


import com.rajtech.stockwebservice.dao.AbstractDao;
import com.rajtech.stockwebservice.dao.CategoryDao;
import com.rajtech.stockwebservice.model.Categories;
import java.util.Date;
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
@Repository("categoryDao")
public class CategotyImpl extends AbstractDao<Integer, Categories> implements CategoryDao{

    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public Categories findById(int id) {
        return getByKey(id);
    }

    @Override
    public Categories findByName(String name) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("name", name) );
        return (Categories) crit.uniqueResult();
    }

    @Override
    public void saveCategory(Categories categories) {
        sessionFactory.getCurrentSession().save(categories);
    }

    @Override
    public List<Categories> findAllCategories() {
        List<Categories> categories = null;
        Criteria crit = createEntityCriteria();
        crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        categories = crit.list();
        return categories;
    }

    @Override
    public void updateCategory(Categories categories) {
        sessionFactory.getCurrentSession().update(categories);
    }
    
}
