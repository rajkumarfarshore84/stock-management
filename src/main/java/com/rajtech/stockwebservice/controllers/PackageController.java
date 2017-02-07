/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rajtech.stockwebservice.controllers;

import com.rajtech.stockwebservice.model.PackagesSize;
import com.rajtech.stockwebservice.utilities.JsonResponse;
import com.rajtech.stockwebservice.utilities.Utility;
import java.io.Serializable;
import java.util.Date;
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
 * @author Rajkumar Ramasamy
 */
@Controller
public class PackageController extends AppController implements Serializable{

    @Autowired
    private Utility utilHashSecure;

    @RequestMapping(value = {"/admin/packing/list/{page}","/supervisor/packing/list/{page}"
            ,"/user/packing/list/{page}"},method = RequestMethod.GET)
    public String listCustomer(@PathVariable("page") String page,ModelMap model){
        try {
            utilHashSecure = new Utility();
            int _page = Integer.parseInt(utilHashSecure.decrypt(page));
        } catch (Exception e) {
            System.err.println(e);
        }
        
        return "customers/list_customer";
    }
    @RequestMapping(value = {"/admin/packing/add","/supervisor/packing/add"
            ,"/user/packing/add/{page}"},method = RequestMethod.GET)
    public String addForm(ModelMap model){
        
        try {
            utilHashSecure = new Utility();
            model.addAttribute("", "");
        } catch (Exception e) {
            System.err.println(e);
        }
        
        return "";
    }
    @RequestMapping(value = {"/admin/packing/save","/supervisor/packing/save"
            ,"/user/packing/save"},method = RequestMethod.POST)
    public @ResponseBody JsonResponse postSave(@ModelAttribute("modelName") PackagesSize packagesSize,BindingResult result){
        JsonResponse res = new JsonResponse();
        
        Date currentDate = new Date();
        if (!result.hasErrors()) {
//            if(customer.getId()!=null){
//                Customer customer_cur = customerService.findById(customer.getId());
//                customer_cur.setCreatedAt(customer_cur.getCreatedAt());
//                customer_cur.setUpdatedAt(currentDate);
//                customerService.update(stock_cur);
//            }else{
//                customerService.save(customer);
//            }
            res.setStatus("Success");
        } else {
            res.setStatus("FAIL");
            res.setResult(result.getAllErrors());
        }
        
        return res;
    }

}
