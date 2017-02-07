/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rajtech.stockwebservice.dao.Impl;

import com.rajtech.stockwebservice.dao.AbstractDao;
import com.rajtech.stockwebservice.dao.RoleDao;
import com.rajtech.stockwebservice.model.Roles;
import java.util.List;
import java.util.Set;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

/**
 *
 * @author node
 */
@Repository("roleDao")
public class RolesDaoImpl extends AbstractDao<Integer, Roles> implements RoleDao{

    @Override
    public Roles findById(int id) {
        return getByKey(id);
    }

    @Override
    public Set<Roles> fildAllRoles() {
        Criteria crit = createEntityCriteria();
        //crit.add(Restrictions.eq("username", name) );
        return (Set<Roles>) (Roles) crit.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public List<Roles> getRoles() {
        List<Roles> roles;
        Criteria crit = createEntityCriteria();
        crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        roles = (List<Roles>) crit.list();
        return roles;
    }
    
}
