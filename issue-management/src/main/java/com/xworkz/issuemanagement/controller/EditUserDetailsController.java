package com.xworkz.issuemanagement.controller;

import com.xworkz.issuemanagement.dto.SignUpDTO;
import com.xworkz.issuemanagement.model.service.EditUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
@SessionAttributes("signUpDTO")
@Slf4j
public class EditUserDetailsController {

    @Autowired
    private EditUserDetailsService editUserDetailsService;

    @Autowired
    private HttpSession httpSession;


    public EditUserDetailsController()
    {
        log.info("EditUserDetailsController constructor:");
    }

    @GetMapping("edit")
    public String editUserProfile(@RequestParam("email") String email, Model model)
    {

        //call httpSession on signInController also
        String userData=(String) httpSession.getAttribute("SignedInUserEmail");

        log.info("user data in editUserProfile() :{}",userData);

        if(userData!=null)
        {
            SignUpDTO signUpDTO=editUserDetailsService.getUserDetails(email);

            model.addAttribute("signUpDTO",signUpDTO);
        }
        return "EditUserProfile";
    }


    @PostMapping("edit-profile")
    public String updateUserProfile(SignUpDTO signUpDTO,Model model)
    {
       SignUpDTO userData= editUserDetailsService.updateUserDetails(signUpDTO);

       if(userData!=null)
       {
           log.info("updated User Data:{}",signUpDTO);
           model.addAttribute("signUpDTO", userData);
           model.addAttribute("msg","Profile updated Successfully!..");

           return "EditUserProfile";
       }
       model.addAttribute("errorMessageProfile","Error while Updating profile.");
       log.info("Error while Updating profile.");

       return "Profile";
    }

}
