/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rajtech.stockwebservice.validate;

import com.rajtech.stockwebservice.model.Vendors;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author node
 */
@Component
public class VendorsValidator implements Validator{

    @Override
    public boolean supports(Class<?> type) {
        return Vendors.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error-name", "* Supplier name required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address1", "error-address1", "* Supplier address required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "error-email", "* Supplier email required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "error-phone", "* Supplier phone required.");
    }
    
}
