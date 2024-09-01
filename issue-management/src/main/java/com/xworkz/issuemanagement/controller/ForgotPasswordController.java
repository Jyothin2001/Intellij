package com.xworkz.issuemanagement.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.xworkz.issuemanagement.model.service.ForgotPasswordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
@Slf4j
public class ForgotPasswordController
{
    @Autowired
    private ForgotPasswordService forgotPasswordService;

    @Autowired
    private HttpSession httpSession;



    public ForgotPasswordController()
    {
        System.out.println("ForgotPasswordController constructor: ");
    }
    //Forgot Password
    @PostMapping("forgotPassword")
    public String forgotPassword(@RequestParam String email, Model model)
    {
        log.info("forgotPassword in controller");

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
