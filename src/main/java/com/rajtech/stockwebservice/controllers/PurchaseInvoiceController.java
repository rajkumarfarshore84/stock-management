/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rajtech.stockwebservice.controllers;

import com.rajtech.stockwebservice.model.PurchaseMaster;
import com.rajtech.stockwebservice.model.PurchasedItems;
import com.rajtech.stockwebservice.model.Users;
import com.rajtech.stockwebservice.model.Vendors;
import com.rajtech.stockwebservice.service.OrderService;
import com.rajtech.stockwebservice.service.ProductService;
import com.rajtech.stockwebservice.service.PurchaseService;
import com.rajtech.stockwebservice.service.VendorService;
import com.rajtech.stockwebservice.utilities.Utility;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author node
 */
@Controller
public class PurchaseInvoiceController extends AppController implements Serializable{
    @Autowired
    VendorService vendorService;
    
    @Autowired
    ProductService productService;
    
    @Autowired
    PurchaseService purchaseService;
    
    @Autowired
    OrderService orderService;
    
    private Utility utilHashSecure;
    
    @RequestMapping(value = {"/admin/inventory/purchaseinvoice/{page}","/supervisor/inventory/purchaseinvoice/{page}"
            ,"/user/inventory/purchaseinvoice/{page}"},method = RequestMethod.GET)
    public String purchaseInvoiceList(@PathVariable("page") String page,ModelMap model) throws Exception{
        utilHashSecure = new Utility();
        int _page = Integer.parseInt(utilHashSecure.decrypt(page));
        List<PurchaseMaster> purchaseList = purchaseService.findByPage(_page, 5);
        int pageCount = Math.round((purchaseService.countPage())/5);
        
        int currentPage = _page;
        if(_page ==0){
            currentPage = 1;
        }
        model.addAttribute("invoceList", purchaseList);
        model.addAttribute("pageCount", pageCount);
        model.addAttribute("currentPage", currentPage);
        return "purchaseinvoice/purchaseInvoice";
    }
    
    @RequestMapping(value = {"/admin/inventory/purchase/add","/supervisor/inventory/purchase/add","/user/inventory/purchase/add"},method = RequestMethod.GET)
    public String addForm(ModelMap model,HttpSession session){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        PurchaseMaster purchaseMaster = new PurchaseMaster();
        Users loggedUser = (Users) session.getAttribute("loggeduser");
        //orderMaster.setOrderPlacedBy(loggedUser);
        model.addAttribute("pageTitle","Item Orders");
        model.addAttribute("editInvoice", purchaseMaster);
        return "purchaseinvoice/addform";
    }
    
//    @ModelAttribute("defaultinvoice")
//    public PurchaseMaster defaultOrder(){
//        PurchaseMaster purchaseMaster = new PurchaseMaster();
//        return purchaseMaster;
//    }
    
    
    
    
    
    @RequestMapping(value = {"/admin/inventory/purchaseinvoice/postOrder","/supervisor/inventory/purchaseinvoice/postOrder","/user/inventory/purchaseinvoice/postOrder"},method = RequestMethod.POST)
    public String placeOrderSave(@ModelAttribute("purchaseMaseter") PurchaseMaster purchaseMaseter,BindingResult result,HttpSession session) throws Exception{
      //HttpSession session = request.getSession();
        utilHashSecure = new Utility();
        List<PurchasedItems> itemList = purchaseMaseter.getPurchasedItemsList();
        Users loggedUser = (Users) session.getAttribute("udetail");
        itemList.remove(0);
        purchaseMaseter.setPurchasedItemsList(itemList);
        for(PurchasedItems item:purchaseMaseter.getPurchasedItemsList()){
            purchaseMaseter.addItem(item);
        }
        if(purchaseMaseter.getId()==null){
            purchaseService.save(purchaseMaseter);
        }else{
            purchaseService.update(purchaseMaseter);
        }
        return "redirect:/"+loggedUser.getRoleId().getRoleName().toLowerCase()+"/inventory/purchaseinvoice/"+utilHashSecure.encrypt("0");
    }
    
    @RequestMapping(value ={"/admin/inventory/supplier/address/{id}","/supervisor/inventory/supplier/address/{id}"
            ,"/user/inventory/supplier/address/{id}"},method = RequestMethod.GET)
    public String getSupplier(@PathVariable String id,ModelMap model) throws Exception{
        
        Vendors vendor = vendorService.findById(Integer.parseInt(id));
        model.addAttribute("supplierAddress",vendor);
        return "suppliers/ajax/address";
    }
    
    @RequestMapping(value ={"/admin/inventory/purchase/viewbill/{id}","/supervisor/inventory/purchase/viewbill/{id}"
            ,"/user/inventory/purchase/viewbill/{id}"},method = RequestMethod.GET)
    public String viewPurchaseBill(@PathVariable String id,ModelMap model){
        try{
            utilHashSecure = new Utility();
            int invoiceId = Integer.parseInt(utilHashSecure.decrypt(id));
            PurchaseMaster purchasedInvoice = purchaseService.findById(invoiceId);
            model.addAttribute("purchasedInvoice",purchasedInvoice);
        }catch(Exception ex){
            System.err.print(ex);
        }
        return "purchaseinvoice/viewinvoice";
    }
    @RequestMapping(value ={"/admin/inventory/purchase/edit/{id}","/supervisor/inventory/purchase/edit/{id}"
            ,"/user/inventory/purchase/edit/{id}"},method = RequestMethod.GET)
    public String editBIll(@PathVariable String id,ModelMap model){
        try{
            utilHashSecure = new Utility();
            int invoiceId = Integer.parseInt(utilHashSecure.decrypt(id));
            PurchaseMaster purchasedInvoice = purchaseService.findById(invoiceId);
            Vendors vendor = vendorService.findById(purchasedInvoice.getVendorId().getVendorId());
            //System.out.println(vendor.getName()+"Vendor name .................................................................");
            purchasedInvoice.setVendorId(vendor);
            PurchasedItems itemEmpty = new PurchasedItems();
            List<PurchasedItems> itemFirstIndex = purchasedInvoice.getPurchasedItemsList();
            itemFirstIndex.add(0, itemEmpty);
            purchasedInvoice.setPurchasedItemsList(itemFirstIndex);
            
            model.addAttribute("pageTitle","Edit Purchased Bill");
            model.addAttribute("editInvoice",purchasedInvoice);
        }catch(Exception ex){
            System.err.print(ex);
        }
        return "purchaseinvoice/addform";
    }
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }
}
