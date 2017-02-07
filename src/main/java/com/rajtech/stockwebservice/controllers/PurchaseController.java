/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rajtech.stockwebservice.controllers;


import com.rajtech.stockwebservice.model.OrderItems;
import com.rajtech.stockwebservice.model.OrderMaster;
import com.rajtech.stockwebservice.model.Users;
import com.rajtech.stockwebservice.service.OrderService;
import com.rajtech.stockwebservice.service.ProductService;
import com.rajtech.stockwebservice.service.VendorService;
import com.rajtech.stockwebservice.utilities.HashSecure;
import com.rajtech.stockwebservice.utilities.Utility;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Rajkumar
 */
@Controller
public class PurchaseController extends AppController{
    
    @Autowired
    VendorService vendorService;
    
    @Autowired
    ProductService productService;
    
    @Autowired
    OrderService orderService;
    
    private HashSecure hashSecure;
    
    private Utility utils;
    
    HttpSession session ;
    
    @RequestMapping(value = {"/admin/inventory/placeorder","/supervisor/inventory/placeorder","/user/inventory/placeorder"},method = RequestMethod.GET)
    public String purchaseOrderList(ModelMap model) throws UnsupportedEncodingException{
        List<OrderMaster> listOrdered = orderService.findAll();
        for(OrderMaster order:listOrdered){
            System.out.println(order.getEntityId()+": "+order.getVendorId().getName());
        }
        int entity_id = Integer.parseInt(URLEncoder.encode("1", "UTF-8"));
        System.out.println("Encoded string::"+entity_id);
        model.addAttribute("listorders", listOrdered);
        model.addAttribute("pageTitle","Order Placed List");
        return "purchase/purchaseorder";
    }
    
    @RequestMapping(value = {"/admin/inventory/placeorder/add","/supervisor/inventory/placeorder/add","/user/inventory/placeorder/add"},method = RequestMethod.GET)
    public String placeOrder(ModelMap model,HttpSession session){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        OrderMaster orderMaster = new OrderMaster();
        Users loggedUser = (Users) session.getAttribute("loggeduser");
        orderMaster.setOrderPlacedBy(loggedUser);
        model.addAttribute("pageTitle","Item Orders");
        model.addAttribute("editOrder", orderMaster);
        return "purchase/placeOrder";
    }
    
    @ModelAttribute("defaultorder")
    public OrderMaster defaultOrder(){
        OrderMaster orderMaster = new OrderMaster();
        
        return orderMaster;
    }
    
    @RequestMapping(value = {"/admin/inventory/placeorder/postOrder","/supervisor/inventory/placeorder/postOrder","/user/inventory/placeorder/postOrder"},method = RequestMethod.POST)
    public String placeOrderSave(@ModelAttribute("orderMaseter") OrderMaster orderMaseter,BindingResult result,HttpSession session){
      //HttpSession session = request.getSession();
        System.out.println(orderMaseter.getOrderPlacedBy().getId());
        List<OrderItems> itemList = orderMaseter.getOrderItemsList();
        Users loggedUser = (Users) session.getAttribute("udetail");
        itemList.remove(0);
        orderMaseter.setOrderItemsList(itemList);
        //System.out.println("Order total:"+loggedUser.getRoleId().getRoleName().toLowerCase());
        for(OrderItems item:orderMaseter.getOrderItemsList()){
            orderMaseter.addItem(item);
        }
        if(orderMaseter.getEntityId() ==null){
            orderService.save(orderMaseter);
        }else{
            orderService.update(orderMaseter);
        }
        return "redirect:/"+loggedUser.getRoleId().getRoleName().toLowerCase()+"/inventory/placeorder";
    }
    @RequestMapping(value = {"/admin/inventory/placeorder/viewOrder/{id}",
        "/supervisor/inventory/placeorder/viewOrder/{id}","/user/inventory/placeorder/viewOrder/{id}"},method = RequestMethod.GET)
    public String viewPlacedOrder(@PathVariable String id,ModelMap model) throws Exception{
        hashSecure = new HashSecure();
        utils = new Utility();
        int entity_id = Integer.parseInt(utils.decrypt(id));
        OrderMaster itemOrder = orderService.findById(entity_id);
        System.out.println("Dycripted Id: "+itemOrder.getPurchaseMasterListSave());
        model.addAttribute("pageTitle","View Order Placed");
        model.addAttribute("itemDetails",itemOrder);
        return "purchase/viewOrderedItem";
    }
    /*
      Edit the placed order. 
    */
    @RequestMapping(value = {"/admin/inventory/placeorder/edit/{id}",
        "/supervisor/inventory/placeorder/edit/{id}","/user/inventory/placeorder/edit/{id}"},method = RequestMethod.GET)
    public String edirPlacedOrder(@PathVariable String id,ModelMap model) throws Exception{
        utils = new Utility();
        int entity_id = Integer.parseInt(utils.decrypt(id));
        OrderMaster itemOrder = orderService.findById(entity_id);
        OrderItems itemEmpty = new OrderItems();
        List<OrderItems> itemFirstIndex = itemOrder.getOrderItemsList();
        itemFirstIndex.add(0, itemEmpty);
        itemOrder.setOrderItemsList(itemFirstIndex);
        model.addAttribute("pageTitle","Edit Order Placed");
        model.addAttribute("editOrder",itemOrder);
        return "purchase/placeOrder";
    }
}
