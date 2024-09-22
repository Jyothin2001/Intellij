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

import javax.servlet.http.HttpSession;
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

    @Autowired
    private HttpSession httpSession;


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
            String emailStatus = this.signUpService.saveAndValidate(signUpDTO);
            if ("success".equals(emailStatus))
            {
                log.info("service saveAndValidate() in controller successfully:{} ", emailStatus);
                signUpDTO.setImageName("ProfileIcon.png");
                redirectAttributes.addFlashAttribute("msg", "Signup successful " + signUpDTO.getFirstName() + "<br>" + "Please check your email for your password.");
                //return "SignIn";
                return "redirect:sign-up-success";
            }
           else if ("network_error".equals(emailStatus)) {
                model.addAttribute("SignUpError", "Network issue while sending the email.<br>Please try again later");
                return "SignUp";
            }

            else {
                log.info("service saveAndValidate() in service not successfully:{} ", emailStatus);
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

