package com.xworkz.issuemanagement.controller;

import com.xworkz.issuemanagement.model.service.ForgotPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class ForgotPasswordController
{
    @Autowired
    private ForgotPasswordService forgotPasswordService;


    public ForgotPasswordController()
    {
        System.out.println("ForgotPasswordController constructor: ");
    }
    //Forgot Password
    @PostMapping("forgotPassword")
    public String forgotPassword(@RequestParam String email, Model model)
    {
        System.out.println("***********");
        boolean password=forgotPasswordService.forgotPassword(email);
        if(password) {
            model.addAttribute("forgotPasswordMsg", "A new password has been sent to your email.");
            return "SignIn";
        }
        else
        {
            model.addAttribute("forgotPasswordError", "Email address not found.");
        }
        return "ForgotPassword";

    }
}
