/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rajtech.stockwebservice.controllers;

import com.rajtech.stockwebservice.model.Categories;
import com.rajtech.stockwebservice.model.Countries;
import com.rajtech.stockwebservice.model.Customer;
import com.rajtech.stockwebservice.model.OrderMaster;
import com.rajtech.stockwebservice.model.Products;
import com.rajtech.stockwebservice.model.States;
import com.rajtech.stockwebservice.model.Vendors;
import com.rajtech.stockwebservice.service.CountryService;
import com.rajtech.stockwebservice.service.OrderService;
import com.rajtech.stockwebservice.service.ProductService;
import com.rajtech.stockwebservice.service.StatesService;
import com.rajtech.stockwebservice.service.VendorService;
import com.rajtech.stockwebservice.utilities.Utility;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author node
 */
@Controller
public class AppController {
    
    @Autowired
    VendorService vendorService;
    
    @Autowired
    ProductService productService;
    
    @Autowired
    OrderService orderService;
    
    @Autowired
    CountryService countryService;
    
    @Autowired
    StatesService statesService;
    
    @ModelAttribute("weightUnit")
    public Map< String, String > listWeight(){
        Map< String, String > listWeight = new HashMap<String, String>();
        
        listWeight.put("g","Gram");
        listWeight.put("kg","Kg");
        listWeight.put("lt","Litre");
        listWeight.put("ml","Milliliter");
        return listWeight;
    }
    
    @ModelAttribute("suppliers")
    public Map< Integer, String >  categoriesList(){
        Map< Integer, String > suppliers = new HashMap<Integer, String>();
        List<Vendors> suppliersModel = vendorService.findAll();
        for(Vendors supplier: suppliersModel){
            suppliers.put(supplier.getVendorId(), supplier.getName());
        }
        return suppliers;
    }
    @ModelAttribute("productsListByRaw")
    public Map< Integer, String > listProductRaw(){
        Map< Integer, String > productsList = new HashMap<Integer, String>();
        Categories categ = new Categories(1);
        List<Products> prodList = productService.findByCategory(categ);
        for(Products prod: prodList){
            productsList.put(prod.getId(), prod.getCode()+" -"+prod.getName());
        }
        return productsList;
    }
    
    @ModelAttribute("productsListName")
    public Map< Integer, String > listProductName(){
        Map< Integer, String > productsList = new HashMap<Integer, String>();
        List<Products> prodList = productService.findAll();
        for(Products prod: prodList){
            productsList.put(prod.getId(), prod.getName());
        }
        return productsList;
    }
    
    @ModelAttribute("productsListCode")
    public Map< Integer, String > listProductCode(){
        Map< Integer, String > productsList = new HashMap<Integer, String>();
        List<Products> prodList = productService.findAll();
        for(Products prod: prodList){
            
            productsList.put(prod.getId(), prod.getCode()+" -"+prod.getName());
        }
        return productsList;
    }
    @ModelAttribute("preOrders")
    public Map< Integer, String > listPreOrders(){
        Map< Integer, String > listWeight = new HashMap<Integer, String>();
        List<OrderMaster> orderList = orderService.findAll();
        for(OrderMaster orderL:orderList){
            listWeight.put(orderL.getEntityId(),orderL.getEntityId().toString()+" : From "+orderL.getVendorId().getName());
        }
        return listWeight;
    }
    @ModelAttribute("productsListByInventory")
    public Map< Integer, String > listProductInventory(){
        Map< Integer, String > productsList = new HashMap<Integer, String>();
        Categories categ = new Categories(2);
        List<Products> prodList = productService.findByCategory(categ);
        for(Products prod: prodList){
            productsList.put(prod.getId(), prod.getCode()+" -"+prod.getName());
        }
        return productsList;
    }
    @ModelAttribute("countryList")
    public Map<Integer, String> listCountries(){
        Map< Integer, String > countryList = new HashMap<Integer, String>();
        List<Countries> conList = countryService.findByAll();
        for(Countries prcountry: conList){
            countryList.put(prcountry.getId(), prcountry.getName());
        }
        return countryList;
    }
   
}
