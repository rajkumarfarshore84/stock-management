/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rajtech.stockwebservice.controllers;

import com.rajtech.stockwebservice.model.Customer;
import com.rajtech.stockwebservice.model.Tax;
import com.rajtech.stockwebservice.service.TaxService;
import com.rajtech.stockwebservice.utilities.JsonResponse;
import com.rajtech.stockwebservice.utilities.Utility;
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
 * @author Rajkumar Ramasamy
 */
@Controller
public class TaxController extends AppController implements Serializable {

    @Autowired
    private Utility utilHashSecure;

    @Autowired
    private TaxService taxService;

    @RequestMapping(value = {"/admin/tax/list/{page}", "/supervisor/tax/list/{page}",
         "/user/tax/list/{page}"}, method = RequestMethod.GET)
    public String listCustomer(@PathVariable("page") String page, ModelMap model) {
        try {
            int _page = Integer.parseInt(utilHashSecure.decrypt(page));
            List<Tax> taxs = taxService.findByAll();
            int pageCount = Math.round((taxService.count()) / 5);

            int currentPage = _page;
            if (_page == 0) {
                currentPage = 1;
            }
            model.addAttribute("taxList", taxs);
            model.addAttribute("pageCount", pageCount);
            model.addAttribute("currentPage", currentPage);
        } catch (Exception e) {
            System.err.println(e);
        }

        return "tax/list_tax";
    }

    @RequestMapping(value = {"/admin/tax/add", "/supervisor/tax/add",
         "/user/customer/add/{page}"}, method = RequestMethod.GET)
    public String addForm(ModelMap model) {
        Tax tax = null;
        try {
            tax = new Tax();
            model.addAttribute("editTax", tax);
        } catch (Exception e) {
            System.err.println(e);
        }

        return "tax/ajax/add_tax";
    }

    @RequestMapping(value = {"/admin/tax/save", "/supervisor/tax/save",
         "/user/tax/save"}, method = RequestMethod.POST)
    public @ResponseBody
    JsonResponse postSave(@ModelAttribute("modelName") Tax tax, BindingResult result) {
        JsonResponse res = new JsonResponse();
        System.out.println("Tax___"+tax);
        if (!result.hasErrors()) {
            if (tax.getId() != null) {
                taxService.update(tax);
            } else {
                taxService.save(tax);
            }
            res.setStatus("Success");
        } else {
            res.setStatus("FAIL");
            res.setResult(result.getAllErrors());
        }

        return res;
    }

    @RequestMapping(value = {"/admin/tax/edit/{id}", "/supervisor/tax/edit/{id}",
         "/user/customer/edit/{id}"}, method = RequestMethod.GET)
    public String editForm(@PathVariable String id,ModelMap model) throws Exception {
        Tax tax = null;
        int taxId = Integer.parseInt(utilHashSecure.decrypt(id));
        try {
            tax = taxService.findById(taxId);
            model.addAttribute("editTax", tax);
        } catch (Exception e) {
            System.err.println(e);
        }

        return "tax/ajax/add_tax";
    }
}
