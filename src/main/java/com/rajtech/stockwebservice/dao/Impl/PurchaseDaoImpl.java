/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rajtech.stockwebservice.dao.Impl;


import com.rajtech.stockwebservice.dao.AbstractDao;
import com.rajtech.stockwebservice.dao.PurchaseDao;
import com.rajtech.stockwebservice.model.PurchaseMaster;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author node
 */
@Repository ("purchaseDoa")
public class PurchaseDaoImpl extends AbstractDao<Integer, PurchaseMaster> implements PurchaseDao{

    @Autowired
    SessionFactory sessionFactory;
    
    @Override
    public PurchaseMaster findById(int id) {
        return getByKey(id);
    }

    @Override
    public List<PurchaseMaster> findAll() {
        List<PurchaseMaster> purchaseMasters = null;
        Criteria criteria = createEntityCriteria();
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        purchaseMasters = criteria.list();
        return purchaseMasters;
    }

    @Override
    public void save(PurchaseMaster purchaseMaster) {
        sessionFactory.getCurrentSession().save(purchaseMaster);
    }

    @Override
    public void update(PurchaseMaster purchaseMaster) {
        sessionFactory.getCurrentSession().update(purchaseMaster);
    }

    @Override
    public void delete(String deleteBy, int id) {
        sessionFactory.getCurrentSession().delete(deleteBy, id);
    }

    @Override
    public List<PurchaseMaster> findByPage(int page,int no_of_record) {
        List<PurchaseMaster> purchaseMasters = null;
        if(page == 0){
            page =1;
        }
        int indexPage = (page*no_of_record) - no_of_record;
        
        Criteria criteria = createEntityCriteria();
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.setFirstResult(indexPage);
        criteria.setMaxResults(no_of_record);
        purchaseMasters = criteria.list();
        return purchaseMasters;
    }

    @Override
    public int count() {
        int count=0;
        Criteria criteria = createEntityCriteria();
        count = (int) (long) (criteria.setProjection(Projections.rowCount()).uniqueResult());
        return count;
    }
    
}
