/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rajtech.stockwebservice.validate;

import com.rajtech.stockwebservice.model.ManufacturedStocks;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author node
 */
@Component
public class StockFormValidator implements Validator{

    @Override
    public boolean supports(Class<?> type) {
        return ManufacturedStocks.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ManufacturedStocks stock = (ManufacturedStocks) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "batchNo", "error-batchno","* Batch number is required.");
        //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "basePrice", "error-baseprice","* Base price is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "qty", "error-qty","* Qty is required.");
        //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "companyMargin", "error-cmargin","* Company margin is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "retailerMargin", "error-rmargin","* Retailer margin is required.");
        
        if(stock.getProductId().getId() == 0){
            errors.rejectValue("productId.id", "error-product","* Product is required");
        }
        if(stock.getWeightIn() == "0"){
            errors.rejectValue("weightIn", "error-weight","* Weight is required");
        }
    }
    
}
