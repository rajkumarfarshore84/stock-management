/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rajtech.stockwebservice.controllers;


import com.rajtech.stockwebservice.model.Vendors;
import com.rajtech.stockwebservice.service.VendorService;
import com.rajtech.stockwebservice.utilities.JsonResponse;
import com.rajtech.stockwebservice.validate.VendorsValidator;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
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
public class SuppliersController {
    
    @Autowired
    VendorService vendorService;
    
    @Autowired
    VendorsValidator vendorsValidator;
    
    @RequestMapping(value = {"/admin/suppliers/list","/supervisor/suppliers/list"}, method = RequestMethod.GET)
    public String listSupplier(HttpServletRequest request, HttpServletResponse response,ModelMap model){
        List<Vendors> vendorsList = vendorService.findAll();
        System.out.println(vendorsList);
        model.addAttribute("vendorsList", vendorsList);
        return "suppliers/list";
    }
    
    @RequestMapping(value = {"/admin/suppliers/add","/supervisor/suppliers/add"}, method = RequestMethod.GET)
    public String newSupplier(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        Vendors vendorsData = new Vendors();
        model.addAttribute("editSupplier", vendorsData);
        return "suppliers/ajax/add";
    }
    
    @ModelAttribute("defaultSupplier")
    public Vendors defaultSupplier() {
        return new Vendors();
    }
    
    @RequestMapping(value = {"/admin/suppliers/edit/{id}","/supervisor/suppliers/edit/{id}"}, method = RequestMethod.GET)
    public String editSupplier(@PathVariable int id,ModelMap model) {
        Vendors vendorsData = vendorService.findById(id);
        model.addAttribute("editSupplier", vendorsData);
        return "suppliers/ajax/add";
    }
    
    @RequestMapping(value = {"/admin/suppliers/addPost","/supervisor/suppliers/addPost"}, method = RequestMethod.POST)
    public @ResponseBody JsonResponse newPostSupplier(@ModelAttribute("vendors") 
       @Valid Vendors vendors, BindingResult result) throws Exception {
        Date currentDate = new Date();
        
        vendorsValidator.validate(vendors, result);
        JsonResponse res = new JsonResponse();
        if (!result.hasErrors()) {
            if(vendors.getVendorId()!=null){
                Vendors vendData = vendorService.findById(vendors.getVendorId());
                vendors.setCreatedAt(vendData.getCreatedAt());
                vendors.setUpdatedAt(currentDate);
                vendorService.update(vendors);
            }else{
                vendorService.save(vendors);
            }
            res.setStatus("Success");
        } else {
            res.setStatus("FAIL");
            res.setResult(result.getAllErrors());
        }
        return res;
    }
}
