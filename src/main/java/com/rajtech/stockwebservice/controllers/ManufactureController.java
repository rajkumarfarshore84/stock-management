/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rajtech.stockwebservice.controllers;

import com.rajtech.stockwebservice.model.CompanyMargin;
import com.rajtech.stockwebservice.model.ManufacturedStocks;
import com.rajtech.stockwebservice.model.Products;
import com.rajtech.stockwebservice.model.SalesItem;
import com.rajtech.stockwebservice.model.Tax;
import com.rajtech.stockwebservice.model.Users;
import com.rajtech.stockwebservice.service.CompanyMarginService;
import com.rajtech.stockwebservice.service.StockService;
import com.rajtech.stockwebservice.service.TaxService;
import com.rajtech.stockwebservice.utilities.JsonResponse;
import com.rajtech.stockwebservice.utilities.Utility;
import com.rajtech.stockwebservice.validate.StockFormValidator;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
public class ManufactureController extends AppController implements Serializable{
    
    private Utility utilHashSecure;

    @Autowired
    StockFormValidator stockFormValidator;
    
    @Autowired
    StockService stockService;
    
    @Autowired
    CompanyMarginService companyMarginService;
    
    @Autowired
    TaxService taxService;
    
    @RequestMapping(value = {"/admin/manufacture/oilstock/{page}","/supervisor/manufacture/oilstock/{page}"
            ,"/user/manufacture/oilstock/{page}"},method = RequestMethod.GET)
    public String listInventory(@PathVariable("page") String page,ModelMap model) throws Exception{
        utilHashSecure = new Utility();
        int _page = Integer.parseInt(utilHashSecure.decrypt(page));
        int pageCount = Math.round((stockService.count())/5);
        int currentPage = _page;
        if(_page ==0){
            currentPage = 1;
        }
        List<ManufacturedStocks> stockList = stockService.findByPage(_page, 5);
        
        model.addAttribute("stockList", stockList);
        model.addAttribute("pageCount", pageCount);
        model.addAttribute("currentPage", currentPage);
        return "inventory/list";
    }
    @RequestMapping(value = {"/admin/manufacture/oilstock/add","/supervisor/manufacture/oilstock/add"
            ,"/user/manufacture/add"},method = RequestMethod.GET)
    public String addInventory(ModelMap model){
        CompanyMargin mStock = new CompanyMargin();
        
        model.addAttribute("editStock", mStock);
        return "inventory/ajax/add";
    }
    @RequestMapping(value = {"/admin/manufacture/oilstock/postStock","/supervisor/manufacture/oilstock/postStock"
            ,"/user/manufacture/oilstock/postStock"},method = RequestMethod.POST)
    public @ResponseBody JsonResponse postInventory(@ModelAttribute("mStock") CompanyMargin manufacturedStocks,
            HttpSession session,BindingResult result) throws Exception{
        Date currentDate = new Date();
        
        stockFormValidator.validate(manufacturedStocks, result);
        JsonResponse res = new JsonResponse();
        double mrpTmp = 0;

        if (!result.hasErrors()) {
            if(manufacturedStocks.getId()!=null){
                ManufacturedStocks stock_cur = stockService.findById(manufacturedStocks.getId());
                stock_cur.setCreatedAt(stock_cur.getCreatedAt());
                stock_cur.setUpdatedAt(currentDate);
                SalesItem si = new SalesItem();
                si.setId(0);
                stockService.update(stock_cur);
            }else{
                
                Tax taxId = taxService.findById(manufacturedStocks.getTaxId().getId());
                manufacturedStocks.setTaxId(taxId);
                manufacturedStocks.setTaxAmount(Double.parseDouble(taxId.getValue().toString()));
                companyMarginService.save(manufacturedStocks);
            }
            res.setStatus("Success");
        } else {
            res.setStatus("FAIL");
            res.setResult(result.getAllErrors());
        }

        return res;
    }
    
    @RequestMapping(value = {"/admin/manufacture/packing/{page}","/supervisor/manufacture/packing/{page}"
            ,"/user/manufacture/packing/{page}"},method = RequestMethod.GET)
    public String listPacking(@PathVariable("page") String page,ModelMap model) throws Exception{
        utilHashSecure = new Utility();
        int _page = Integer.parseInt(utilHashSecure.decrypt(page));
        return "inventory/list_packing";
    }
    //
}
