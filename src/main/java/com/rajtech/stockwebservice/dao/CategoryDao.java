/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rajtech.stockwebservice.dao;

import com.rajtech.stockwebservice.model.Categories;
import java.util.List;

/**
 *
 * @author node
 */
public interface CategoryDao {
    Categories findById(int id);
    
    Categories findByName(String name);
    
    void saveCategory(Categories categories);
    
    List<Categories> findAllCategories();
    
    void updateCategory(Categories categories);
}
