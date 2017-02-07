/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rajtech.stockwebservice.dao.Impl;

import com.rajtech.stockwebservice.dao.AbstractDao;
import com.rajtech.stockwebservice.dao.CustomerDao;
import com.rajtech.stockwebservice.model.Customer;
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
@Repository("customerDao")
public class CustomerDaoImpl extends AbstractDao<Integer, Customer> implements CustomerDao{

    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public Customer findById(int id) {
        return getByKey(id);
    }

    @Override
    public Customer findByName(String name) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("name", name) );
        return (Customer) crit.uniqueResult();
    }

    @Override
    public void save(Customer customer) {
        sessionFactory.getCurrentSession().save(customer);
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = null;
        Criteria crit = createEntityCriteria();
        crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        customers = crit.list();
        return customers;
    }

    @Override
    public void update(Customer customer) {
        sessionFactory.getCurrentSession().update(customer);
    }

    @Override
    public List<Customer> findByPage(int page, int no_of_record) {
        List<Customer> customers = null;
        if(page == 0){
            page =1;
        }
        int indexPage = (page*no_of_record) - no_of_record;
        Criteria criteria = createEntityCriteria();
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.setFirstResult(indexPage);
        criteria.setMaxResults(no_of_record);
        customers = criteria.list();
        return customers;
    }
    
    @Override
    public int count() {
        int count=0;
        Criteria criteria = createEntityCriteria();
        count = (int) (long) (criteria.setProjection(Projections.rowCount()).uniqueResult());
        return count;
    }
}
