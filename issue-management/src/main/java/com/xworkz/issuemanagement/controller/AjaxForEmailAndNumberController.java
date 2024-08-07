package com.xworkz.issuemanagement.controller;

import com.xworkz.issuemanagement.model.service.AjaxEmailAndNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class AjaxForEmailAndNumberController
{
    @Autowired
    private AjaxEmailAndNumberService ajaxEmailService;


    public AjaxForEmailAndNumberController()
    {
        System.out.println("AjaxForEmailAndNumberController constructor:");
    }

    //GetMapping are used to map the action in init class
    //extract the data from url using @PathVariable
    @GetMapping("/validateEmail/{email}")
    public String emailValidation(@PathVariable String email)
    {
        System.out.println("Ajax email in controller:"+ email);

         if(ajaxEmailService.existsByEmail(email)) {
             //existsEmail is true or false
             return "<span style='color:red;'>This Email  exists </span>";
         }
        else {
             return null;
         }


    }
    @GetMapping("/validateNumber/{contactNumber}")
    public String numberValidation(@PathVariable Long contactNumber)
    {
        System.out.println("Ajax number in controller:"+ contactNumber);
      boolean existsNumber=  ajaxEmailService.existsByNumber(contactNumber);
      if(existsNumber)
      {
          return "<span style='color:red';>This Number exists</span>";
      }
      else
      {
          return null;
      }

    }

}




