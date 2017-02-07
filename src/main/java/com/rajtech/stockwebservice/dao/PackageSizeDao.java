/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rajtech.stockwebservice.dao;

import com.rajtech.stockwebservice.model.PackagesSize;
import java.util.List;

/**
 *
 * @author node
 */
public interface PackageSizeDao {

    PackagesSize findById(int id);

    PackagesSize findByName(String name);

    void save(PackagesSize packagesSize);

    List<PackagesSize> findAllCategories();

    void update(PackagesSize packagesSize);
    
    List<PackagesSize> findByPage(int page, int no_of_record);
    
    int count();
}
