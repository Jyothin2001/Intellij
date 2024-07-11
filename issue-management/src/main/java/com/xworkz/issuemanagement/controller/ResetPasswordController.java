package com.xworkz.issuemanagement.controller;

import com.xworkz.issuemanagement.model.service.ResetPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.jws.WebParam;

@Controller
@RequestMapping("/")
public class ResetPasswordController
{
    @Autowired
    private ResetPasswordService resetPasswordService;


    public ResetPasswordController()
    {
        System.out.println("ResetPasswordController constructor: ");

    }

    @PostMapping("resetPassword")
    public String resetPassword(@RequestParam String email, String oldPassword, String newPassword, String confirmPassword,Model model)
    {
        boolean password=resetPasswordService.resetPassword(email,oldPassword,newPassword,confirmPassword);
        if(password) {
            model.addAttribute("passwordResetMessage", "Password reset successful");
            return "SignIn";
        }
        else {
            model.addAttribute("passwordResetError", "Failed to reset password.Please check your password");
            return "PasswordReset";
        }


    }
}
