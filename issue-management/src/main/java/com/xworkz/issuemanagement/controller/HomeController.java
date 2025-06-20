package com.xworkz.issuemanagement.controller;

import com.xworkz.issuemanagement.dto.DepartmentDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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
    @GetMapping("ChangePassword")
    public String passwordReset()
    {
        return "ChangePassword";
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




    @GetMapping("SearchComplaintRaise")
    public String searchComplaintType()
    {
        return "SearchComplaintRaise";
    }


    @GetMapping("AddDepartment")
    public String SaveDepartment()
    {
        return "AdminAddDepartment";
    }



    @GetMapping("SubAdminForgotPasswordPage")
    public String subAdminForgotPasswordPage()
    {
        return "SubAdminForgotPassword";
    }

    @GetMapping("SubAdminChangePassword")
    public String subAdminChangePassword()
    {
        return "SubAdminChangePassword";
    }

    @GetMapping("addEmployee")
    public String addEmployee()
    {
        return "EmployeeRegistration";
    }

    @GetMapping("employeeLoginPage")
    public String employeeLoginPage()
    {
        return "EmployeeLoginPage";
    }




}
