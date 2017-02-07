/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rajtech.stockwebservice.service;

import com.rajtech.stockwebservice.model.Customer;
import java.util.List;

/**
 *
 * @author node
 */
public interface CustomerService {
    Customer findById(int id);

    Customer findByName(String name);

    void save(Customer customer);

    List<Customer> findAll();

    void update(Customer customer);
    
    List<Customer> findByPage(int page, int no_of_record);
    
    int countPage();
}
