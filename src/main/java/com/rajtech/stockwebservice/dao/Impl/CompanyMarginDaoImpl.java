/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rajtech.stockwebservice.dao.Impl;

import com.rajtech.stockwebservice.dao.AbstractDao;
import com.rajtech.stockwebservice.dao.CompanyMarginDao;
import com.rajtech.stockwebservice.model.CompanyMargin;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Rajkumar Ramasamy
 */
@Repository("companyMarginDao")
public class CompanyMarginDaoImpl extends AbstractDao<Integer, CompanyMargin> implements CompanyMarginDao{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public CompanyMargin findById(int id) {
        return getByKey(id);
    }

    @Override
    public List<CompanyMargin> findByAll() {
        List<CompanyMargin> companyList = null;
        Criteria criteria = createEntityCriteria();
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        companyList = criteria.list();
        return companyList;
    }

    @Override
    public void save(CompanyMargin companyMargin) {
        sessionFactory.getCurrentSession().save(companyMargin);
    }

    @Override
    public void update(CompanyMargin companyMargin) {
        sessionFactory.getCurrentSession().update(companyMargin);
    }
    
}
