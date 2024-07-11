package com.xworkz.issuemanagement.controller;

import com.xworkz.issuemanagement.dto.SignUpDTO;
import com.xworkz.issuemanagement.model.service.MailService;
import com.xworkz.issuemanagement.model.service.SignUpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
@Slf4j
public class SignUpController
{
    @Autowired
    private SignUpService signUpService;

    @Autowired
    private MailService mailService;



    public SignUpController()
    {
        log.info("IssueManagementController constructor:");
        System.out.println("IssueManagementController constructor");
    }


    @PostMapping("signup")
    public String signUp(@Valid SignUpDTO signUpDTO, BindingResult bindingResult, Model model, @RequestParam("email") String email)
    {
        System.out.println("SignUp data:"+signUpDTO);
        log.info("Jyothi SignUp data:{}",signUpDTO);

        if(bindingResult.hasErrors())
        {
            bindingResult.getAllErrors().forEach(objectError -> System.out.println(objectError.getDefaultMessage()));

            model.addAttribute("errors",bindingResult.getAllErrors());
            model.addAttribute("signUpDTO",signUpDTO);

            return "SignUp";
        }
        else {

            boolean validate = this.signUpService.saveAndValidate(signUpDTO);
            if (validate) {
                System.out.println("service saveAndvalidate() in controller successfully: " + validate);
                String subject = "Welcome to issue management";
                String body = "Hi, " + signUpDTO.getFirstName() + "\n\n Your Registration is successfull.  Your Password is  " + signUpDTO.getPassword();
                this.mailService.sendSimpleEmail(email, subject, body);

               model.addAttribute("msg", "Signup successful. Please check your email for your password.");

                return "SignIn";
            }
            else {
                System.out.println("service saveAndvalidate() in service not successfully: " + validate);
            }
            model.addAttribute("msg", "successfully Sign Up: " + signUpDTO.getFirstName());

        }
        return "SignUp";
    }
}

