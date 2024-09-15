package com.xworkz.issuemanagement.controller;

import com.xworkz.issuemanagement.emailSending.MailSend;
import com.xworkz.issuemanagement.model.service.ForgotPasswordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.mail.javamail.JavaMailSenderImpl;

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

    @Autowired
    private MailSend mailSend;




    public ForgotPasswordController()
    {
        System.out.println("ForgotPasswordController constructor: ");
    }
    //Forgot Password
//    @PostMapping("forgotPassword")
//    public String forgotPassword(@RequestParam String email, Model model)
//    {
//        log.info("forgotPassword in controller");
//
//        boolean password=forgotPasswordService.forgotPassword(email);
//        if(password) {
//            model.addAttribute("forgotPasswordMsg", "A new password has been sent to your email.");
//
//            return "SignIn";
//        }
//        else
//        {
//            model.addAttribute("forgotPasswordError", "Email address not found.");
//        }
//        return "ForgotPassword";
//
//    }
    @PostMapping("forgotPassword")
    public String forgotPassword(@RequestParam String email, Model model) {
        log.info("Processing forgotPassword request for email: {}", email);

        // Call the service to handle password reset logic
        boolean passwordReset = forgotPasswordService.forgotPassword(email);

        if (passwordReset) {
            // Prepare the email message
            SimpleMailMessage emailMessage = new SimpleMailMessage();
            emailMessage.setTo(email);
            emailMessage.setSubject("Password Reset Request");
            emailMessage.setText("A new password has been generated and sent to your email. Please check your inbox.");

            // Send the email
            String emailStatus = mailSend.sendEmail(emailMessage);

            // Handle email sending status
            if ("network_error".equals(emailStatus)) {
                model.addAttribute("forgotPasswordError", "Network issue while sending the reset email. Please try again later. or Do Forgot Password");
                return "ForgotPassword";
            } else if ("send_error".equals(emailStatus)) {
                model.addAttribute("forgotPasswordError", "Password reset successful, but email sending failed. Please check your email settings.");
                return "ForgotPassword";
            } else {
                model.addAttribute("msg", "A new password has been sent to your email.");
                return "SignIn"; // Redirect to login page or sign-in page after success
            }
        } else {
            model.addAttribute("forgotPasswordError", "Email address not found.");
            return "ForgotPassword";
        }
    }


}
