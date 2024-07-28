package com.xworkz.issuemanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String homePage()
    {
        return"Home";
    }
    @GetMapping("HomePage")
    public String HomePage() {
        return "Home";
    }

    @GetMapping("SignUpPage")
    public String signUpPage()
    {
        return "SignUp";
    }

    @GetMapping("LogInPage")
    public String signInPage()
    {
        return "SignIn";
    }

    @GetMapping("ForgotPasswordPage")
    public String ForgotPasswordPage()
    {
        return "ForgotPassword";
    }
    @GetMapping("AdminPage")
    public String admin()
    {
        return "Admin";
    }

    @GetMapping("PasswordReset")
    public String asswordReset()
    {
        return "PasswordReset";
    }


    @GetMapping("SearchComplaintRaise")
    public String searchComplaintType()
    {
        return "SearchComplaintRaise";
    }

    @GetMapping("ComplaintRaisePage")
    public String complaintRaisePage()
    {
        return "ComplaintRaise";
    }



}
