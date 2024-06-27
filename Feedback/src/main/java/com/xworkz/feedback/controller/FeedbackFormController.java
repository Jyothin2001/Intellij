package com.xworkz.feedback.controller;

import com.xworkz.feedback.dto.FeedbackDTO;
import com.xworkz.feedback.dto.SearchDTO;
import com.xworkz.feedback.model.service.FeedbackService;
import com.xworkz.feedback.model.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/")
@EnableWebMvc
public class FeedbackFormController
{
    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private SearchService searchService;


    public FeedbackFormController()
    {
        System.out.println("FeedbackForm constructor:");
    }

    @PostMapping("/feedback")
    public String display(@Valid FeedbackDTO feedbackDTO, BindingResult bindingResult, Model model)
    {
        System.out.println("display() mapping: errors");

        if(bindingResult.hasErrors())
        {
            bindingResult.getAllErrors().forEach(objectError -> System.out.println(objectError.getDefaultMessage()));

            model.addAttribute("error",bindingResult.getAllErrors());
            model.addAttribute("feedbackDto1",feedbackDTO);

            return "FeedbackForm";
        }


         boolean data=this.feedbackService.save(feedbackDTO);

            if(data)
                System.out.println("Service is save in Controller successfully: "+ feedbackDTO);
            else
                System.out.println("Service is not save in Controller successfully: " + feedbackDTO);



            model.addAttribute("msg","yours information stored successfully: "+feedbackDTO.getName());
            model.addAttribute("dto",feedbackDTO);


        return "FeedbackSuccess";
    }

    //do not give method="" because by default anchor tag has Get
    @GetMapping("/feedback-edit")
    public String edit(@RequestParam int id,Model model)
    {
        System.out.println("running edit() in controller");

        FeedbackDTO feedbackDTO= searchService.findById(id);

        if(feedbackDTO!=null)
        {
            System.out.println("service findById() is in controller"+feedbackDTO);
            model.addAttribute("feedbackDto1",feedbackDTO);
        }
        else
        {
            System.out.println("service findById() is not in controller"+feedbackDTO);
        }

        return "FeedbackForm";
    }

    @PostMapping("/search")
    public String display(@Valid SearchDTO searchDTO, Model model)
    {
        List<FeedbackDTO> list= this.searchService.search(searchDTO);
        if(!list.isEmpty())
        {
            System.out.println("Search in controller success: "+searchDTO);

        }
        else
        {
            System.out.println("Search in controller not success:"+searchDTO);
        }
        model.addAttribute("Rating",searchDTO.getRating());
        model.addAttribute("listOfRatings",list);

        return "Search";
    }

      // <!-- <input type="hidden" name="id" value="${feedbackDto1.id !=null? feedbackDto1.id:''}"/>--!>

    @GetMapping("/feedback-delete")
    public String deleteDisplay(@RequestParam int id,Model model)
    {
       boolean data= feedbackService.delete(id);
        if(data)
        {
            System.out.println("service delete() in controller successful:" +data);
            model.addAttribute("deleteMsg","Feedback id " +id+ "  Deleted Successfully");
        }
        else
        {
            System.out.println("service delete() in controller is not  successful:" +data);
        }
        return "FeedbackSuccess";
    }

}
