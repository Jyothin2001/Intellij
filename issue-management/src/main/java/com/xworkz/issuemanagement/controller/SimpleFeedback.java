package com.xworkz.issuemanagement.controller;

import com.xworkz.issuemanagement.dto.SignUpDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.jws.WebParam;
import javax.validation.Valid;

@Controller
@RequestMapping("/")
@Slf4j

public class SimpleFeedback {

    public SimpleFeedback()
    {
        log.info("Feedback constructor:");

    }


    @PostMapping("feedback")
    public String signUp(Model model, @RequestParam("email") String email,@RequestParam("name") String name,@RequestParam("feedback") String feedback) {

        String[] details={name,email,feedback};
        log.info(" data:{}", details);
        model.addAttribute("FeedbackName","Feedback Received <br>Thank you  " + name);


        return"FeedbackForm";
    }



}
