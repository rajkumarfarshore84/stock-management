/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rajtech.stockwebservice.controllers;


import com.rajtech.stockwebservice.model.Roles;
import com.rajtech.stockwebservice.model.Users;
import com.rajtech.stockwebservice.service.RoleService;
import com.rajtech.stockwebservice.service.UserService;
import com.rajtech.stockwebservice.utilities.HashSecure;
import com.rajtech.stockwebservice.utilities.JsonResponse;
import com.rajtech.stockwebservice.validate.UserValidator;
import java.util.ArrayList;
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
public class UserController {
    @Autowired
    UserValidator userValidator;
    
    @Autowired
    UserService userService;
    
    @Autowired
    RoleService roleService;
    
    private List<Users> userList = new ArrayList<Users>();
    
    private HashSecure hashSecure;
    
    @RequestMapping(value = {"/admin/users/list","/supervisor/users/list"}, method = RequestMethod.GET)
    public String newAdminUser(ModelMap model){
        List<Users> usersList = userService.findAllUsers();
        model.addAttribute("usersList", usersList);
        return "users/list";
    }
    
    @RequestMapping(value = {"/admin/users/add","/supervisor/users/add"}, method = RequestMethod.GET)
    public String newUser(HttpServletRequest request, HttpServletResponse response,ModelMap model){
        Users modalData = new Users();
        model.addAttribute("editUser",modalData);
        return "users/ajax/add";
    }
    @RequestMapping(value = {"/admin/users/edit/{id}","/supervisor/users/edit/{id}"}, method = RequestMethod.GET)
    public String editUser(@PathVariable int id,ModelMap model) throws Exception{
        Users modalData = userService.findById(id);
        modalData.setPasswordconfirm(hashSecure.decrypt(modalData.getPassword()));
        modalData.setPassword(hashSecure.decrypt(modalData.getPassword()));
        modalData.setCreatedAt(modalData.getCreatedAt());
        model.addAttribute("editUser",modalData);
        model.addAttribute("editing","edit");
        return "users/ajax/add";
    }
    @ModelAttribute("defaultUser")
    public Users defaultUser() {
        Users user = new Users();
        
        return user;
    }
    @ModelAttribute("roles")
    public Map< Integer, String >  roleList(){
        Map< Integer, String > roles = new HashMap<Integer, String>();
        List<Roles> rolesModel = roleService.getRoles();
        for(Roles role: rolesModel){
            roles.put(role.getId(), role.getRoleName());
        }
        return roles;
    }
    @RequestMapping(value = {"/admin/users/addPost","/supervisor/users/addPost"}, method = RequestMethod.POST)
    public @ResponseBody JsonResponse newPostUser(@Valid @ModelAttribute("users") Users user,BindingResult result) throws Exception {
        userValidator.validate(user, result);
        JsonResponse res = new JsonResponse();
        if(!result.hasErrors()){
            int extra = (user.getPassword().length()) % 16;
            user.setPassword(hashSecure.encrypt(user.getPassword()));
            userService.saveUser(user);
            res.setStatus("Success");
        }else{
            res.setStatus("FAIL");
            res.setResult(result.getAllErrors());
        }
       return res;
    }
}
