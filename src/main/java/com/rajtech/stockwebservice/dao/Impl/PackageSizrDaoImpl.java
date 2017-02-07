/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rajtech.stockwebservice.dao.Impl;

import com.rajtech.stockwebservice.dao.AbstractDao;
import com.rajtech.stockwebservice.dao.PackageSizeDao;
import com.rajtech.stockwebservice.model.PackagesSize;
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
@Repository("packageSizeDao")
public class PackageSizrDaoImpl extends AbstractDao<Integer, PackagesSize> implements PackageSizeDao{

    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public PackagesSize findById(int id) {
        return getByKey(id);
    }

    @Override
    public PackagesSize findByName(String name) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("name", name) );
        return (PackagesSize) crit.uniqueResult();
    }

    @Override
    public void save(PackagesSize packagesSize) {
        sessionFactory.getCurrentSession().save(packagesSize);
    }

    @Override
    public List<PackagesSize> findAllCategories() {
        List<PackagesSize> packagesSizes = null;
        Criteria crit = createEntityCriteria();
        crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        packagesSizes = crit.list();
        return packagesSizes;
    }

    @Override
    public void update(PackagesSize packagesSize) {
        sessionFactory.getCurrentSession().update(packagesSize);
    }

    @Override
    public List<PackagesSize> findByPage(int page, int no_of_record) {
        List<PackagesSize> packagesSizes = null;
        if (page == 0) {
            page = 1;
        }
        int indexPage = (page * no_of_record) - no_of_record;
        Criteria criteria = createEntityCriteria();
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.setFirstResult(indexPage);
        criteria.setMaxResults(no_of_record);
        packagesSizes = criteria.list();
        return packagesSizes;
    }

    @Override
    public int count() {
        int count = 0;
        Criteria criteria = createEntityCriteria();
        count = (int) (long) (criteria.setProjection(Projections.rowCount()).uniqueResult());
        return count;
    }
    
}
