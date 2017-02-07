/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rajtech.stockwebservice.dao.Impl;

import com.rajtech.stockwebservice.dao.AbstractDao;
import com.rajtech.stockwebservice.dao.TaxDao;
import com.rajtech.stockwebservice.model.Tax;
import java.util.List;
import org.eclipse.persistence.internal.oxm.schema.model.Restriction;
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
@Repository("taxDao")
public class TaxDaoImpl extends AbstractDao<Integer, Tax> implements TaxDao{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Tax findById(int id) {
        return getByKey(id);
    }

    @Override
    public Tax findByName(String name) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("name", name));
        return (Tax) crit.uniqueResult();
    }

    @Override
    public void save(Tax tax) {
        sessionFactory.getCurrentSession().save(tax);
    }

    @Override
    public List<Tax> findAll() {
        List<Tax> taxs = null;
        Criteria crit = createEntityCriteria();
        crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        taxs = crit.list();
        return taxs;
    }

    @Override
    public void update(Tax tax) {
        sessionFactory.getCurrentSession().update(tax);
    }

    @Override
    public List<Tax> findByPage(int page, int no_of_record) {
        List<Tax> taxs = null;
        if(page == 0){
            page =1;
        }
        int indexPage = (page*no_of_record) - no_of_record;
        Criteria criteria = createEntityCriteria();
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.setFirstResult(indexPage);
        criteria.setMaxResults(no_of_record);
        taxs = criteria.list();
        return taxs;
    }

    @Override
    public int count() {
        int count=0;
        Criteria criteria = createEntityCriteria();
        count = (int) (long) (criteria.setProjection(Projections.rowCount()).uniqueResult());
        return count;
    }
    
}
