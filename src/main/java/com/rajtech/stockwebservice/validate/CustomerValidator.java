/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rajtech.stockwebservice.validate;

import com.rajtech.stockwebservice.model.Customer;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author node
 */
@Component
public class CustomerValidator implements Validator{

    @Override
    public boolean supports(Class<?> type) {
        return Customer.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"name","error-name","* Customer name is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"comapnyName","error-comapnyName","* Company name is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"contactNumber","error-contactNumber","* Contact number is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"address","error-address","* Communication address is required");
    }
    
}
