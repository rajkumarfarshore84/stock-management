/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rajtech.stockwebservice.controllers;

import com.rajtech.stockwebservice.utilities.Utility;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author node
 */
@Controller
public class SalesController extends AppController implements Serializable{
    
    @Autowired
    private Utility utilHashSecure;
    
    @RequestMapping(value = {"/admin/sales/invoice/{page}","/supervisor/sales/invoice/{page}"
            ,"/user/sales/invoice/{page}"},method = RequestMethod.GET)
    public String listSales(@PathVariable("page") String page,ModelMap model){
        try {
            utilHashSecure = new Utility();
            int _page = Integer.parseInt(utilHashSecure.decrypt(page));
        } catch (Exception e) {
            System.err.println(e);
        }
        
        return "sales/list";
    }
}
