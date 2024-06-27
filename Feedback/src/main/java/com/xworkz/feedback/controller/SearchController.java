package com.xworkz.feedback.controller;

import com.xworkz.feedback.dto.FeedbackDTO;
import com.xworkz.feedback.dto.SearchDTO;
import com.xworkz.feedback.model.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/")
public class SearchController
{
    @Autowired
    private SearchService searchService;

    public SearchController()

    {
        System.out.println("SearchController constructor:");
    }

    //@PostMapping("/search")
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

//         <!--file download
//            <form action="download" method="get">
//        <label for="fileDownload">Enter file Name:</label>
//        <input type="text" id="fileDownload" name="filename" /required>
//        <input type="submit" value="Download File">
//        </form>-->

        return "Search";


    }

}
