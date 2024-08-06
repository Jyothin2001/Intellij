package com.xworkz.issuemanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    //for the first time
    @RequestMapping("/")
    public String homePage()
    {
        return"Home";
    }
    //for return to home like logout time
    @GetMapping("HomePage")
    public String homePages() {
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
        return "AdminPage";
    }

    @GetMapping("DepartmentSignUpPage")
    public String departmentSignUpPage()
    {
        return "DepartmentSignUpPage";
    }

    @GetMapping("DepartmentLogInPage")
    public String departmentLogInPage()
    {
        return "DepartmentLogInPage";
    }


    @GetMapping("ChangePassword")
    public String passwordReset()
    {
        return "ChangePassword";
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

    @GetMapping("AddDepartment")
    public String SaveDepartment()
    {
        return "AdminAddDepartment";
    }



}
