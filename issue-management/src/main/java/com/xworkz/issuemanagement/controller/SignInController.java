package com.xworkz.issuemanagement.controller;

import com.xworkz.issuemanagement.dto.SignUpDTO;
import com.xworkz.issuemanagement.model.service.ForgotPasswordService;
import com.xworkz.issuemanagement.model.service.MailService;
import com.xworkz.issuemanagement.model.service.SignInService;
import com.xworkz.issuemanagement.model.service.SignUpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.DelegatingServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    private HttpSession httpSession;


    public SignInController() {
        System.out.println("SignInController constructor:");
    }

    @PostMapping("signIn")
    public String signIn(@RequestParam String email, @RequestParam String password, RedirectAttributes redirectAttributes, Model model) {
        log.info("Running signIn method:");

        log.info("Email: {} ", email);
        log.info("password: {}" ,password);


        SignUpDTO signUpDTO = signInService.findByEmailAndPassword(email, password);
        if (signUpDTO != null)
        {
            // Reset failed attempts
            signInService.resetFailedAttempts(email);
           log.info("service password in controller successfully login with:{} ", signUpDTO.getEmail());
           redirectAttributes.addFlashAttribute("msgSignIn", signUpDTO.getFirstName() + " , Successfully login with : " + signUpDTO.getEmail() );
           redirectAttributes.addFlashAttribute("UserFirstName",signUpDTO.getFirstName());
           redirectAttributes.addFlashAttribute("UserLastName",signUpDTO.getLastName());


            //Sessions in a web application are used to store user-specific information across multiple requests.
            // Set user-specific information in the session
            httpSession.setAttribute("signedInUserEmail", email);
            //user Edit details :"set"
            httpSession.setAttribute("signUpDTO", signUpDTO);//also used for saving signUp user id in complaint table

            // Set the profile image in the session
            String profileImageUrl = "/images/" + signUpDTO.getImageName();
            httpSession.setAttribute("profileImage", profileImageUrl);

            // Set the default profile image before storing signUpDTO in the session
          //  signUpDTO.setImageName("ProfileIcon.png");

           // Redirect to the profile page
            return "redirect:ProfilePage"; // This will change the URL to /profilePage

        }
        else
        {
            // model.addAttribute("ErrorMsg","Invalid Email and password:");
            signInService.incrementFailedAttempts(email);
            int failedAttempts = signInService.getFailedAttempts(email);
            log.info("Failed attempts for{} " , email +": " + failedAttempts);


            if (failedAttempts >= 3)
            {
                signInService.lockAccount(email); // Lock account after 3 failed attempts
                redirectAttributes.addFlashAttribute("error", "Your account is locked due to too many failed attempts.");
                redirectAttributes.addFlashAttribute("accountLocked", true);
            }
            else
            {

                redirectAttributes.addFlashAttribute("error", "Invalid email id and password. Attempts: " + failedAttempts);
                redirectAttributes.addFlashAttribute("accountLocked", false);
            }


            return "redirect:log-in-page";

        }
    }

    @GetMapping("ProfilePage")
    public String profilePage()
    {
        return "Profile";
    }

    @GetMapping("log-in-page")
    public String logInPage()
    {
        return "SignIn";
    }


    @GetMapping("logout")
    public  String logout()
    {
        return "index";
    }

}
