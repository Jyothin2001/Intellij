package com.xworkz.issuemanagement.controller;

import com.xworkz.issuemanagement.dto.SignUpDTO;
import com.xworkz.issuemanagement.model.service.SignInService;
import com.xworkz.issuemanagement.model.service.ViewPageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
@SessionAttributes("signUpDTO")
@Slf4j
public class ViewPageController
{
    @Autowired
    private ViewPageService viewPageService;

    @Autowired
    private HttpSession httpSession;

    public ViewPageController()
    {
        log.info("ViewPageController constructor:");
    }

    @GetMapping("view-Page")
public String showUserDetails(Model model)
    {
        // Fetch the signed-in user's email from the session
        //get the userDetails based on email while signIn
        //after user signIn go to profilePage then we provided "viewPage" action it comes here
        //and write session in signIn controller

           String userEmail=(String) httpSession.getAttribute("SignedInUserEmail");

           log.info("Signed-in user email: "+userEmail);

           if(userEmail!=null)
           {
               // Fetch user data based on the email
               SignUpDTO signUpDTO1=viewPageService.getUserByEmail(userEmail);

               // Add the user data to the viewPage
               model.addAttribute("signUpDTO1",signUpDTO1);
           }
           else
           {
               log.info("user email not found in session.");
           }

        return "ViewPage";
    }

}
