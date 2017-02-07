/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rajtech.stockwebservice.service.Impl;

import com.rajtech.stockwebservice.dao.AbstractDao;
import com.rajtech.stockwebservice.dao.PackageSizeDao;
import com.rajtech.stockwebservice.model.PackagesSize;
import com.rajtech.stockwebservice.service.PackageSizeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author node
 */
@Service("packageSizeService")
@Transactional
public class PackageSizeServiceImpl extends AbstractDao<Integer, PackagesSize> implements PackageSizeService{

    @Autowired
    private PackageSizeDao packageSizeDao;
    
    @Override
    public PackagesSize findById(int id) {
        return packageSizeDao.findById(id);
    }

    @Override
    public PackagesSize findByName(String name) {
        return packageSizeDao.findByName(name);
    }

    @Override
    public void save(PackagesSize packagesSize) {
        packageSizeDao.save(packagesSize);
    }

    @Override
    public List<PackagesSize> findAllCategories() {
        return packageSizeDao.findAllCategories();
    }

    @Override
    public void update(PackagesSize packagesSize) {
        packageSizeDao.update(packagesSize);
    }

    @Override
    public List<PackagesSize> findByPage(int page, int no_of_record) {
        return packageSizeDao.findByPage(page, no_of_record);
    }

    @Override
    public int count() {
        return packageSizeDao.count();
    }
    
}
