/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rajtech.stockwebservice.validate;

import com.rajtech.stockwebservice.model.Products;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author node
 */
@Component
public class ProductValidator implements Validator{

    @Override
    public boolean supports(Class<?> type) {
        return Products.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Products product = (Products) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error-name", "*Product name is required.");
        
        if(product.getCategoryId().getCategoryId() ==0){
            errors.rejectValue("categoryId.categoryId", "error-categotyId","* Product category is required");
        }
    }
    
}
