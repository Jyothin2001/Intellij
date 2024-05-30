package com.xworkz.eventregister.beans;


import com.xworkz.eventregister.DTO.EventRegisterDTO;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Component
@RequestMapping
public class EventRegisterForm
{
    public EventRegisterForm()
        {
            System.out.println("no args EventRegisterForm Contstructor:");
        }

        @PostMapping("/submit")
        public String display(@Valid EventRegisterDTO eventRegisterDTO, BindingResult bindingResult, Model model)
        {
            if(bindingResult.hasErrors())
            {
                System.out.println("Dto has Invalid data");
                bindingResult.getAllErrors().forEach(objectError-> System.out.println(objectError.getDefaultMessage()));
                model.addAttribute("error",bindingResult.getAllErrors());
                model.addAttribute("dto",eventRegisterDTO);
     return "EventRegister";
            }
            System.out.println("Creating page");

            model.addAttribute("name"," Data stored Successfully : " + eventRegisterDTO.getFullname()); //key:value
            System.out.println("Event Register Data:"+eventRegisterDTO);
            return "EventRegisterSuccess";
        }
    }


