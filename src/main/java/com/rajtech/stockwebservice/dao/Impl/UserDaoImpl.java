/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rajtech.stockwebservice.dao.Impl;


import com.rajtech.stockwebservice.dao.AbstractDao;
import com.rajtech.stockwebservice.dao.UserDao;
import com.rajtech.stockwebservice.model.Users;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 *
 * @author node
 */
@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, Users> implements UserDao{

    @Override
    public Users findById(int id) {
        return getByKey(id);
    }

    @Override
    public void saveUser(Users user) {
        persist(user);
    }

    @Override
    public List<Users> findAllUsers() {
        Criteria criteria = createEntityCriteria();
        return (List<Users>) criteria.list();
    }

    @Override
    public Users findByName(String name) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("username", name));
        return (Users) criteria.uniqueResult();
    }
    
}
