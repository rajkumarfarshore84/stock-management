/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rajtech.stockwebservice.controllers;


import com.rajtech.stockwebservice.model.Categories;
import com.rajtech.stockwebservice.service.CategoryService;
import com.rajtech.stockwebservice.utilities.JsonResponse;
import com.rajtech.stockwebservice.validate.CategoryValidator;
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
public class CategoriesController {

    @Autowired
    CategoryValidator categoryValidator;

    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = {"/admin/categories/list","/supervisor/categories/list"}, method = RequestMethod.GET)
    public String newAdminUser(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        List<Categories> categories = categoryService.findAllCategories();
        model.addAttribute("categories",categories);
        return "categories/list";
    }

    @RequestMapping(value = {"/admin/categories/add","/supervisor/categories/add"}, method = RequestMethod.GET)
    public String newCategory(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        Categories categoriesDate = new Categories();
        model.addAttribute("editCategory", categoriesDate);
        return "categories/ajax/add";
    }

    @ModelAttribute("defaultCategory")
    public Categories defaultCategory() {
        return new Categories();
    }

    @RequestMapping(value = {"/admin/categories/addPost","/supervisor/categories/addPost"}, method = RequestMethod.POST)
    public @ResponseBody JsonResponse newPostCategory(@ModelAttribute("categories") 
       @Valid Categories categories, BindingResult result) throws Exception {
        Date currentDate = new Date();
        
        categoryValidator.validate(categories, result);
        JsonResponse res = new JsonResponse();
        if (!result.hasErrors()) {
            if(categories.getCategoryId() !=null){
                Categories catedet = categoryService.findById(categories.getCategoryId());
                categories.setCreatedAt(catedet.getCreatedAt());
                categories.setUpdatedAt(currentDate);
                categoryService.updateCategory(categories);
            }else{
                categoryService.saveCategory(categories);
            }
            res.setStatus("Success");
        } else {
            res.setStatus("FAIL");
            res.setResult(result.getAllErrors());
        }
        return res;
    }
    
    @RequestMapping(value = {"/admin/categories/edit/{id}","/supervisor/categories/edit/{id}"}, method = RequestMethod.GET)
    public String editCategory(@PathVariable int id,ModelMap model) {
        Categories categoriesDate = categoryService.findById(id);
        model.addAttribute("editCategory", categoriesDate);
        return "categories/ajax/add";
    }
}
