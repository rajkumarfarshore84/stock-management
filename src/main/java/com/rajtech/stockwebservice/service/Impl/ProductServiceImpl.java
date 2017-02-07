/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rajtech.stockwebservice.service.Impl;

import com.rajtech.stockwebservice.dao.AbstractDao;
import com.rajtech.stockwebservice.dao.ProductDao;
import com.rajtech.stockwebservice.model.Categories;
import com.rajtech.stockwebservice.model.Products;
import com.rajtech.stockwebservice.service.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author node
 */
@Service("productService")
@Transactional
public class ProductServiceImpl extends AbstractDao<Integer,Products> implements ProductService{

    @Autowired
    ProductDao productDao;
    @Override
    public Products findById(int id) {
        return productDao.findById(id);
    }

    @Override
    public List<Products> findAll() {
        return productDao.findAll();
    }

    @Override
    public void save(Products product) {
        productDao.save(product);
    }

    @Override
    public void update(Products product) {
        productDao.update(product);
    }

    @Override
    public List<Products> findByCategory(Categories category_id) {
        return productDao.findByCategory(category_id);
    }
    
}
