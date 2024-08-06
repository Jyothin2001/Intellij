package com.xworkz.issuemanagement.controller;

import com.xworkz.issuemanagement.dto.SignUpDTO;
import com.xworkz.issuemanagement.model.service.MailService;
import com.xworkz.issuemanagement.model.service.SignUpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    }


    @PostMapping("signUp")
    public String signUp(@Valid SignUpDTO signUpDTO, BindingResult bindingResult,Model model,RedirectAttributes redirectAttributes, @RequestParam("email") String email)
    {
        log.info("SignUp data:{}", signUpDTO);

        if (bindingResult.hasErrors())
        {
            bindingResult.getAllErrors().forEach(objectError -> log.error(objectError.getDefaultMessage()));

            model.addAttribute("errors", bindingResult.getAllErrors());
            model.addAttribute("signUpDTO", signUpDTO);

            return "SignUp";
        } else
        {
            boolean validate = this.signUpService.saveAndValidate(signUpDTO);
            if (validate) {
                log.info("service saveAndValidate() in controller successfully:{} ", validate);
                signUpDTO.setImageName("ProfileIcon.png");

//                String subject = "Welcome to issue management";
//                String body = "Hi, " + signUpDTO.getFirstName() + "\n\n Your Registration is successfull.  Your Password is  " + signUpDTO.getPassword();
//                this.mailService.sendSimpleEmail(email, subject, body);


                redirectAttributes.addFlashAttribute("msg", "Signup successful " + signUpDTO.getFirstName() + " Please check your email for your password.");
                //return "SignIn";
                return "redirect:sign-up-success";
            } else {
                log.info("service saveAndValidate() in service not successfully:{} ", validate);
            }

            //return "SignUp";
            return "redirect:sign-up-success";
        }
    }

    //use redirect for redirect: to display data otherwise do like this get model in @ModelAttribute
    @GetMapping("/sign-up-success")
    public String signUpSuccess() {
        // Return the success view
        return "SignUp";
    }
}

