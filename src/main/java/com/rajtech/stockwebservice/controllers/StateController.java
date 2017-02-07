/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rajtech.stockwebservice.controllers;

import com.rajtech.stockwebservice.model.Countries;
import com.rajtech.stockwebservice.model.States;
import com.rajtech.stockwebservice.utilities.JsonResponse;
import com.rajtech.stockwebservice.utilities.Utility;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
 * @author Rajkumar Ramasamy
 */
@Controller
public class StateController extends AppController implements Serializable{

    @Autowired
    private Utility utilHashSecure;

    @RequestMapping(value = {"/admin/statesls/listbycountry/{countryId}","/supervisor/statesls/listbycountry/{countryId}"
            ,"/user/statesls/listbycountry/{countryId}"},method = RequestMethod.GET)
    public String listStateByCountry(@PathVariable int countryId,ModelMap model){
        
        List<States> stateList = null;
        
        try {
            //int countryId = Integer.parseInt(id);
            Countries country = countryService.findById(countryId);
            stateList = statesService.findByCountry(country);
            model.addAttribute("stateList", stateList);
        } catch (Exception e) {
            System.err.println(e);
        }
        
        return "state/ajax/listState";
    }

}
