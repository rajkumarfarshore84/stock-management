/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rajtech.stockwebservice.dao;

import com.rajtech.stockwebservice.model.Categories;
import com.rajtech.stockwebservice.model.Products;
import java.util.List;

/**
 *
 * @author node
 */
public interface ProductDao {
    Products findById(int id);
    List<Products> findAll();
    void save(Products product);
    void update(Products product);
    List<Products> findByCategory(Categories category_id);
}
