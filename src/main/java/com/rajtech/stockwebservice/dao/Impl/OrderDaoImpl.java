/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rajtech.stockwebservice.dao.Impl;


import com.rajtech.stockwebservice.dao.AbstractDao;
import com.rajtech.stockwebservice.dao.OrderDao;
import com.rajtech.stockwebservice.model.OrderMaster;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author node
 */
@Repository("orderDao")
public class OrderDaoImpl extends AbstractDao<Integer, OrderMaster> implements OrderDao{
    
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public OrderMaster findById(int id) {
        return getByKey(id);
    }

    @Override
    public List<OrderMaster> findAll() {
        List<OrderMaster> orderMasters = null;
        Criteria criteria = createEntityCriteria();
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        orderMasters = criteria.list();
        return orderMasters;
    }

    @Override
    public void save(OrderMaster orderMaster) {
        sessionFactory.getCurrentSession().saveOrUpdate(orderMaster);
    }

    @Override
    public void update(OrderMaster orderMaster) {
        sessionFactory.getCurrentSession().update(orderMaster);
    }
    
}
