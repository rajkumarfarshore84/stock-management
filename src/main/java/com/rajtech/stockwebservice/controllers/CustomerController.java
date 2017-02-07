/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rajtech.stockwebservice.controllers;

import com.rajtech.stockwebservice.model.Customer;
import com.rajtech.stockwebservice.model.ManufacturedStocks;
import com.rajtech.stockwebservice.service.CustomerService;
import com.rajtech.stockwebservice.utilities.JsonResponse;
import com.rajtech.stockwebservice.utilities.Utility;
import com.rajtech.stockwebservice.validate.CustomerValidator;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author node
 */
@Controller
public class CustomerController extends AppController implements Serializable{
    
    @Autowired
    private Utility utilHashSecure;
    
    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private CustomerValidator customerValidator;
    
    @RequestMapping(value = {"/admin/customer/list/{page}","/supervisor/customer/list/{page}"
            ,"/user/customer/list/{page}"},method = RequestMethod.GET)
    public String listCustomer(@PathVariable("page") String page,ModelMap model){
        try {
            int _page = Integer.parseInt(utilHashSecure.decrypt(page));
            List<Customer> customers = customerService.findAll();
            int pageCount = Math.round((customerService.countPage())/5);
        
        int currentPage = _page;
        if(_page ==0){
            currentPage = 1;
        }
        model.addAttribute("customerList", customers);
        model.addAttribute("pageCount", pageCount);
        model.addAttribute("currentPage", currentPage);
        } catch (Exception e) {
            System.err.println(e);
        }
        
        return "customers/list_customer";
    }
    @RequestMapping(value = {"/admin/customer/add","/supervisor/customer/add"
            ,"/user/customer/add/{page}"},method = RequestMethod.GET)
    public String addCustomers(ModelMap model){
        
        
        Customer mCustomer = new Customer();
        
        try {
            utilHashSecure = new Utility();
            model.addAttribute("editCustomer", mCustomer);
        } catch (Exception e) {
            System.err.println(e);
        }
        
        return "customers/ajax/add_customer";
    }
    @RequestMapping(value = {"/admin/customer/save","/supervisor/customer/save"
            ,"/user/customer/save"},method = RequestMethod.POST)
    public @ResponseBody JsonResponse postCustomer(@ModelAttribute("customer") Customer customer,BindingResult result){
        JsonResponse res = new JsonResponse();
        customerValidator.validate(customer, result);
        Date currentDate = new Date();
        if (!result.hasErrors()) {
            if(customer.getId()!=null){
                Customer customer_cur = customerService.findById(customer.getId());
                customer.setCreatedAt(customer_cur.getCreatedAt());
                customer.setUpdatedAt(currentDate);
                customerService.update(customer);
            }else{
                customerService.save(customer);
            }
            res.setStatus("Success");
        } else {
            res.setStatus("FAIL");
            res.setResult(result.getAllErrors());
        }
        
        return res;
    }
    
    @RequestMapping(value = {"/admin/customer/edit/{id}","/supervisor/customer/edit/{id}"
            ,"/user/customer/edit/{id}"},method = RequestMethod.GET)
    public String editCustomers(@PathVariable String id,ModelMap model) throws Exception{
        
        int customerId = Integer.parseInt(utilHashSecure.decrypt(id));
        Customer mCustomer = new Customer();
        try {
            mCustomer = customerService.findById(customerId);
            model.addAttribute("editCustomer", mCustomer);
        } catch (Exception e) {
            System.err.println(e);
        }
        
        return "customers/ajax/add_customer";
    }
}
