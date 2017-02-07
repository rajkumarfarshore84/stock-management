/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rajtech.stockwebservice.controllers;


import com.rajtech.stockwebservice.model.Categories;
import com.rajtech.stockwebservice.model.Products;
import com.rajtech.stockwebservice.service.CategoryService;
import com.rajtech.stockwebservice.service.ProductService;
import com.rajtech.stockwebservice.utilities.JsonResponse;
import com.rajtech.stockwebservice.validate.ProductValidator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class ProductController {
    
    @Autowired
    ProductValidator productValidator;
    
    @Autowired
    ProductService productService;
    
    @Autowired
    CategoryService categoryService;
    
    @RequestMapping(value = {"/admin/products/list","/supervisor/products/list"}, method = RequestMethod.GET)
    public String listProducts(HttpServletRequest request, HttpServletResponse response,ModelMap model){
        List<Products> productList = productService.findAll();
        model.addAttribute("productList",productList);
        return "products/list";
    }
    @RequestMapping(value = {"/admin/products/add","/supervisor/products/add"}, method = RequestMethod.GET)
    public String newProduct(HttpServletRequest request, HttpServletResponse response,ModelMap model){
        Products modalData = new Products();
        model.addAttribute("editProduct",modalData);
        return "products/ajax/add";
    }
    @RequestMapping(value = {"/admin/products/edit/{id}","/supervisor/products/edit/{id}"}, method = RequestMethod.GET)
    public String editSupplier(@PathVariable int id,ModelMap model) {
        Products productData = productService.findById(id);
        model.addAttribute("editProduct", productData);
        return "products/ajax/add";
    }
    @ModelAttribute("defaultProduct")
    public Products defaultProduct() {
        return new Products();
    }
    
    @ModelAttribute("categories")
    public Map< Integer, String >  categoriesList(){
        Map< Integer, String > categories = new HashMap<Integer, String>();
        List<Categories> categoriesModel = categoryService.findAllCategories();
        for(Categories category: categoriesModel){
            categories.put(category.getCategoryId(), category.getName());
        }
        return categories;
    }
    @RequestMapping(value = {"/admin/products/addPost","/supervisor/products/addPost"}, method = RequestMethod.POST)
    public @ResponseBody JsonResponse newPostProduct(@ModelAttribute("products") 
       @Valid Products product, BindingResult result) throws Exception {
        Date currentDate = new Date();
        
        productValidator.validate(product, result);
        JsonResponse res = new JsonResponse();
        if (!result.hasErrors()) {
            if(product.getId()!=null){
                Products product_cur = productService.findById(product.getId());
                product.setCreatedAt(product_cur.getCreatedAt());
                product.setUpdatedAt(currentDate);
                productService.update(product);
            }else{
                productService.save(product);
            }
            res.setStatus("Success");
        } else {
            res.setStatus("FAIL");
            res.setResult(result.getAllErrors());
        }
        return res;
    }
}
