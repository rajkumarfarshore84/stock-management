/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rajtech.stockwebservice.service.Impl;

import com.rajtech.stockwebservice.dao.AbstractDao;
import com.rajtech.stockwebservice.dao.CustomerDao;
import com.rajtech.stockwebservice.model.Customer;
import com.rajtech.stockwebservice.service.CustomerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author node
 */
@Service("customerService")
@Transactional
public class CustomerServiceImpl extends AbstractDao<Integer, Customer> implements CustomerService{
    
    @Autowired
    CustomerDao customerDao;

    @Override
    public Customer findById(int id) {
        return customerDao.findById(id);
    }

    @Override
    public Customer findByName(String name) {
        return customerDao.findByName(name);
    }

    @Override
    public void save(Customer customer) {
        customerDao.save(customer);
    }

    @Override
    public List<Customer> findAll() {
        return customerDao.findAll();
    }

    @Override
    public void update(Customer customer) {
        customerDao.update(customer);
    }

    @Override
    public List<Customer> findByPage(int page, int no_of_record) {
        return customerDao.findByPage(page, no_of_record);
    }
    
    @Override
    public int countPage() {
        return customerDao.count();
    }
}
