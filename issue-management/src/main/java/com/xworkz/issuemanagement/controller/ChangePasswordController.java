package com.xworkz.issuemanagement.controller;

import com.xworkz.issuemanagement.model.service.ChangePasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class ChangePasswordController
{
    @Autowired
    private ChangePasswordService changePasswordService;


    public ChangePasswordController()
    {
        System.out.println("ResetPasswordController constructor: ");

    }

    @PostMapping("resetPassword")
    public String resetPassword(@RequestParam String email, String oldPassword, String newPassword, String confirmPassword,Model model)
    {
        boolean password=changePasswordService.changePassword(email,oldPassword,newPassword,confirmPassword);
        if(password) {
            model.addAttribute("passwordResetMessage", "Password reset successful");
            return "SignIn";
        }
        else {
            model.addAttribute("passwordResetError", "Failed to reset password.Please check your password");
            return "ChangePassword";
        }


    }
}
