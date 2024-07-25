package com.xworkz.issuemanagement.controller;

import com.xworkz.issuemanagement.dto.ComplaintRaiseDTO;
import com.xworkz.issuemanagement.model.service.SearchComplaintRaiseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class SearchComplaintRaiseController {

    @Autowired
    private SearchComplaintRaiseService searchComplaintRaiseService;


    public SearchComplaintRaiseController(){
        log.info("SearchComplaintRaiseController constructor:");
    }

    @PostMapping("searchComplaintType")
    public String searchComplaintRaise(ComplaintRaiseDTO complaintRaiseDTO, Model model)
    {
       List<ComplaintRaiseDTO> complaintType= searchComplaintRaiseService.getComplaintType(complaintRaiseDTO);
       List<ComplaintRaiseDTO> complaintCity= searchComplaintRaiseService.getComplaintRaiseArea(complaintRaiseDTO);

        // Combine both lists to ensure all relevant data is included
       List<ComplaintRaiseDTO> combinedData=new ArrayList<>();

       if(complaintType!=null && complaintCity!=null )
       {
           combinedData.addAll(complaintType);
           combinedData.addAll(complaintCity);
           log.info("Data found for the given criteria in AdminController.");
           model.addAttribute("msgComplaintRaise",combinedData); //for table data view raise complaint

           return "SearchComplaintType";
       }
       else {
           log.info("No data found for the given criteria in AdminController.");
           return "SearchComplaintType";
    }

    }

//    @PostMapping("ComplaintTypeSearch")
//    public String searchByComplaintType(RaiseComplaintDTO raiseComplaintDTO, Model model) {
//        System.out.println("searchByComplaintType method running in AdminController..");
//
//        List<RaiseComplaintDTO> data = adminService.searchByComplaintType(raiseComplaintDTO);
//        List<RaiseComplaintDTO> cityType = adminService.searchComplaintByCity(raiseComplaintDTO);
//
//        // Combine both lists to ensure all relevant data is included
//        List<RaiseComplaintDTO> combinedData = new ArrayList<>();
//        if (data != null) {
//            combinedData.addAll(data);
//        }
//        if (cityType != null) {
//            combinedData.addAll(cityType);
//        }
//
//        // Add combined data to the model
//        model.addAttribute("complaintType", combinedData);
//
//        if (combinedData.isEmpty()) {
//            System.out.println("No data found for the given criteria in AdminController.");
//        } else {
//            System.out.println("Data found for the given criteria in AdminController.");
//        }
//
//        return "SearchRaiseComplaint";
//    }

}






