package com.xworkz.issuemanagement.controller;

import com.xworkz.issuemanagement.model.service.AdminService;
import com.xworkz.issuemanagement.model.service.AjaxEmailAndNumberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController //impo:for ajax
@RequestMapping("/")
@Slf4j
public class AjaxForEmailAndNumberController
{
    @Autowired
    private AjaxEmailAndNumberService ajaxEmailAndNumberService;

    @Autowired
    private AdminService adminService;


    public AjaxForEmailAndNumberController()
    {
        System.out.println("AjaxForEmailAndNumberController constructor:");
    }

    //GetMapping are used to map the action in init class
    //extract the data from url using @PathVariable
    @GetMapping("/validateEmail/{email}")
    public String emailValidation(@PathVariable String email)
    {
        log.info("Ajax email in controller:{}", email);

         if(ajaxEmailAndNumberService.existsByEmail(email)) {
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
        log.info("Ajax number in controller:{}", contactNumber);
      boolean existsNumber=  ajaxEmailAndNumberService.existsByNumber(contactNumber);
      if(existsNumber)
      {
          return "<span style='color:red';>This Number exists</span>";
      }
      else
      {
          return null;
      }

    }


    @GetMapping("/subAdminEmailValidation/{email}")
    public String subAdminEmailValidation(@PathVariable String email)
    {
        log.info("Ajax email in controller:{}", email);

        if(ajaxEmailAndNumberService.existsBySubAdminEmail(email)) {
            //existsEmail is true or false
            return "<span style='color:red;'>This  Email  exists </span>";
        }
        else {
            return null;
        }


    }
    @GetMapping("/subAdminNumberValidation/{contactNumber}")
    public String subAdminNumberValidation(@PathVariable Long contactNumber)
    {
        log.info("Ajax number in controller:{}", contactNumber);
        boolean existsNumber=  ajaxEmailAndNumberService.existsBySubAdminNumber(contactNumber);
        if(existsNumber)
        {
            return "<span style='color:red';>This Number exists</span>";
        }
        else
        {
            return null;
        }

    }



    @GetMapping("/validateServerEmail/{email}")
    public String validateEmail(@PathVariable String email)
    {
        log.info("Ajax email in controller:{}", email);
        boolean existsNumber=  ajaxEmailAndNumberService.isEmailExists(email);
        if(existsNumber)
        {
            return "<span style='color:red';>This email exists</span>";
        }
        else
        {
            return null;
        }
    }

    @GetMapping("/validateServerContactNumber/{contactNumber}")
    public String  validateContactNumber(@PathVariable Long contactNumber) {
        log.info("Ajax number in controller:{}", contactNumber);
        boolean existsNumber=  ajaxEmailAndNumberService.isContactNumberExists(contactNumber);
        if(existsNumber)
        {
            return "<span style='color:red';>This Number exists</span>";
        }
        else
        {
            return null;
        }
    }


    @GetMapping("/validateServerDepartmentName/{departmentName}")
    public String validateDepartmentName(@PathVariable String departmentName)
    {
        log.info("Ajax email in controller:{}", departmentName);
        boolean existsDepartmentName=  adminService.existsByDepartmentName(departmentName);
        if(existsDepartmentName)
        {
            return "<span style='color:red';>This "+ departmentName + " exists</span>";
        }
        else
        {
            return null;
        }
    }









}




