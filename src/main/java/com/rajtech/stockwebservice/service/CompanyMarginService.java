/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rajtech.stockwebservice.service;
import com.rajtech.stockwebservice.dao.*;
import com.rajtech.stockwebservice.model.CompanyMargin;
import java.util.List;

/**
 *
 * @author Rajkumar Ramasamy
 */
public interface CompanyMarginService {
    CompanyMargin findById(int id);
    List<CompanyMargin> findByAll();
    void save(CompanyMargin companyMargin);
    void update(CompanyMargin companyMargin);
}
