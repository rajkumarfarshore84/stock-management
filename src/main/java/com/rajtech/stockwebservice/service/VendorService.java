/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rajtech.stockwebservice.service;

import com.rajtech.stockwebservice.model.Vendors;
import java.util.List;

/**
 *
 * @author node
 */
public interface VendorService {
    Vendors findById(int id);
    List<Vendors> findAll();
    void save(Vendors vendors);
    void update(Vendors vendors);
    void delete(int id);
}
