package com.xworkz.issuemanagement.controller;

import com.xworkz.issuemanagement.dto.SignUpDTO;
import com.xworkz.issuemanagement.emailSending.MailSend;
import com.xworkz.issuemanagement.model.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.DelegatingServerHttpResponse;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.mail.javamail.JavaMailSenderImpl;


import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
@SessionAttributes("signUpDTO")
@Slf4j

public class SignInController {
    @Autowired
    private SignInService signInService;

    @Autowired
    private ForgotPasswordService forgotPasswordService;

    @Autowired
    private ChangePasswordService changePasswordService;


    @Autowired
    private HttpSession httpSession;

    @Autowired
    private MailSend mailSend;


    public SignInController() {
        System.out.println("SignInController constructor:");
    }

    @PostMapping("signIn")
    public String signIn(@RequestParam String email, @RequestParam String password, RedirectAttributes redirectAttributes, Model model) {
        log.info("Running signIn method:");

        log.info("Email: {} ", email);
        log.info("password: {}", password);

        httpSession.setAttribute("signedInUserEmail", email);

        SignUpDTO signUpDTO = signInService.findByEmailAndPassword(email, password);
        if (signUpDTO != null) {
            // Reset failed attempts
            signInService.resetFailedAttempts(email);
            log.info("service password in controller successfully login with:{} ", signUpDTO.getEmail());

            //user Edit details :"set"
            httpSession.setAttribute("signUpDTO", signUpDTO);//also used for saving signUp user id in complaint table

            // Set the profile image in the session
            String profileImageUrl = "/images/" + signUpDTO.getImageName();
            httpSession.setAttribute("profileImage", profileImageUrl);

            // Set the default profile image before storing signUpDTO in the session
            signUpDTO.setImageName("ProfileIcon.png");

            // Redirect to the profile page
            //to avoid resubmission
            return "redirect:ProfilePage"; // This will change the URL to /profilePage

        } else {
            // model.addAttribute("ErrorMsg","Invalid Email and password:");
            signInService.incrementFailedAttempts(email);
            int failedAttempts = signInService.getFailedAttempts(email);
            log.info("Failed attempts for{} ", email + ": " + failedAttempts);


            if (failedAttempts > 3) {
                signInService.lockAccount(email); // Lock account after 3 failed attempts
                redirectAttributes.addFlashAttribute("error", "Your account is locked due to too many failed attempts.");
                redirectAttributes.addFlashAttribute("accountLocked", true);
            } else {

                redirectAttributes.addFlashAttribute("error", "Invalid email id and password. Attempts: " + failedAttempts);
                redirectAttributes.addFlashAttribute("accountLocked", false);
            }


            return "redirect:log-in-page";

        }
    }

    @GetMapping("ProfilePage")
    public String profilePage(Model model) {
        // Retrieve user information from the session
        SignUpDTO signUpDTO = (SignUpDTO) httpSession.getAttribute("signUpDTO");
        if (signUpDTO != null) {
            model.addAttribute("msgSignIn", "Welcome to your page " + signUpDTO.getFirstName() + " " + signUpDTO.getLastName());
            model.addAttribute("UserFirstName", signUpDTO.getFirstName());
            model.addAttribute("UserLastName", signUpDTO.getLastName());
        } else {
            return "redirect:log-in-page"; // Redirect to login if session is null
        }
        return "Profile";

    }

    @GetMapping("log-in-page")
    public String logInPage() {
        return "SignIn";
    }


    @GetMapping("logout")
    public String logout() {
        return "index";
    }

    @PostMapping("resetPassword")
    public String resetPassword(@RequestParam String email,
                                @RequestParam String oldPassword,
                                @RequestParam String newPassword,
                                @RequestParam String confirmPassword,
                                Model model,
                                RedirectAttributes redirectAttributes) {

        SignUpDTO signUpDTO = (SignUpDTO) httpSession.getAttribute("signUpDTO"); // Ensure session is not null
        if (signUpDTO == null) {
            return "ChangePassword"; // Redirect to login if session is null
        }

        String changePasswordStatus = changePasswordService.changePassword(email, oldPassword, newPassword, confirmPassword);
        switch (changePasswordStatus) {
            case "success":
                redirectAttributes.addFlashAttribute("message", "Password updated successfully!");
                return "redirect:/changePassword"; // Redirect to a success page

            case "password_mismatch":
                redirectAttributes.addFlashAttribute("error", "New password and confirm password do not match.");
                return "redirect:/changePassword"; // Redirect back to the change password page

            case "user_not_found":
                redirectAttributes.addFlashAttribute("error", "User with the provided email was not found.");
                return "redirect:/changePassword";

            case "old_password_incorrect":
                redirectAttributes.addFlashAttribute("error", "Old password is incorrect.");
                return "redirect:/changePassword";

            case "network_error":
                model.addAttribute("error", "Network issue while sending the email. <br>Please try again later.");
                return "ChangePassword";

            case "send_error":
                model.addAttribute("error", "An error occurred while sending the email. Please try again.");
                return "ChangePassword";

            case "update_failed":
                redirectAttributes.addFlashAttribute("error", "Password update failed. Please try again.");
                return "redirect:/changePassword";

            default:
                redirectAttributes.addFlashAttribute("error", "An unknown error occurred.");
                return "redirect:/changePassword";
        }
//        if (passwordChanged) {
//            SimpleMailMessage emailMessage = new SimpleMailMessage();
//            emailMessage.setTo(email);
//            emailMessage.setSubject("Password Reset Successful");
//            emailMessage.setText("Your password has been reset successfully.");
//
//            String emailStatus = mailSend.sendEmail(emailMessage);
//            if ("network_error".equals(emailStatus)) {
//                model.addAttribute("passwordResetMessage", "Network issue while sending the reset email. Please try again later.");
//                return "ChangePassword";  // Redirect to a custom error page
//            } else if ("send_error".equals(emailStatus)) {
//                model.addAttribute("passwordResetMessage", "Password reset successful, but email sending failed.");
//                return "ChangePassword";
//            } else {
//                redirectAttributes.addFlashAttribute("msg", "Password Reset Successful");
//            }
//
//            return "redirect:/changePassword";
//        } else {
//            redirectAttributes.addFlashAttribute("passwordResetMessage", "Failed to reset password. Please check your old password.");
//            return "redirect:/changePassword";
//        }
    }

    @GetMapping("changePassword")
    public String changePassword()
    {
       return"ChangePassword";
    }

//    @PostMapping("resetPassword")
//    public String resetPassword(@RequestParam String email, String oldPassword, String newPassword, String confirmPassword, Model model) {
//        boolean password = changePasswordService.changePassword(email, oldPassword, newPassword, confirmPassword);
//        if (password) {
//            model.addAttribute("passwordResetMessage", "Password Reset Successful");
//            return "ChangePassword";
//        } else {
//            model.addAttribute("passwordResetError", "Failed to reset password.Please check your password");
//            return "ChangePassword";
//        }
//
//
//    }

}
