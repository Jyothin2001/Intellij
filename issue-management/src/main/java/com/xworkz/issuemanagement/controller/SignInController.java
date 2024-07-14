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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
@Slf4j
public class SignInController
{
    @Autowired
    private SignInService signInService;

    @Autowired
    private ForgotPasswordService forgotPasswordService;

    @Autowired
   private  HttpSession httpSession;


    public SignInController()
    {
        System.out.println("SignInController constructor:");
    }

    @PostMapping("signin")
    public String signIn(SignUpDTO signUpDTO,@RequestParam  String email, @RequestParam String password, Model model)
    {
        log.info("Running signIn method:");

        SignUpDTO signUpDTO1=signInService.findByEmailAndPassword(email,password);
        if(signUpDTO1!=null)
        {
            signInService.resetFailedAttempts(email);

            log.info("service password in controller successfully login with:{} " , signUpDTO1.getEmail());

            model.addAttribute("msg", signUpDTO1.getFirstName() + " , Successfully login with : "+signUpDTO1.getEmail());

            // User Details: "Set" the signed-in user's email in the session
            httpSession.setAttribute("SignedInUserEmail",email);

            //user Edit details :"set"
            httpSession.setAttribute("signUpDTO",signUpDTO);


            return "Profile";
        }
        else
        {
            // model.addAttribute("ErrorMsg","Invalid Email and password:");
            signInService.incrementFailedAttempts(email);
            int failedAttempts = signInService.getFailedAttempts(email);
            System.out.println("Failed attempts for " + email + ": " + failedAttempts);


            if (failedAttempts >= 3  )
            {
                signInService.lockAccount(email); // Lock account after 3 failed attempts
                model.addAttribute("error", "Your account is locked due to too many failed attempts.");
                model.addAttribute("accountLocked", true);
            }
            else {

                model.addAttribute("error", "Invalid email id and password. Attempts: " + failedAttempts);
                model.addAttribute("accountLocked", false);
             }


            return "SignIn";

        }
    }


}
