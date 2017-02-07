/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rajtech.stockwebservice.service.Impl;


import com.rajtech.stockwebservice.dao.AbstractDao;
import com.rajtech.stockwebservice.dao.CategoryDao;
import com.rajtech.stockwebservice.model.Categories;
import com.rajtech.stockwebservice.service.CategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author node
 */
@Service("categoryService")
@Transactional
public class CategoryServiceImpl extends AbstractDao<Integer,Categories> implements CategoryService{

    @Autowired
    CategoryDao categoryDao;
    
    @Override
    public Categories findById(int id) {
        return categoryDao.findById(id);
    }

    @Override
    public Categories findByName(String name) {
        return categoryDao.findByName(name);
    }

    @Override
    public void saveCategory(Categories categories) {
        categoryDao.saveCategory(categories);
    }

    @Override
    public List<Categories> findAllCategories() {
        return categoryDao.findAllCategories();
    }

    @Override
    public void updateCategory(Categories categories) {
        categoryDao.updateCategory(categories);
    }
    
}
