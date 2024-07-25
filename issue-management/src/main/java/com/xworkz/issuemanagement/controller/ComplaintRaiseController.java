package com.xworkz.issuemanagement.controller;

import com.xworkz.issuemanagement.dto.ComplaintRaiseDTO;
import com.xworkz.issuemanagement.dto.SignUpDTO;
import com.xworkz.issuemanagement.model.service.ComplaintRaiseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.jws.WebParam;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/")
@SessionAttributes("signUpDTO")
public class ComplaintRaiseController
{
    @Autowired
    private ComplaintRaiseService complaintRaiseService;

    public ComplaintRaiseController()
    {
        log.info("ComplaintRaiseController constructor:");
    }
    /*1.if you want session(without spring-boot-starter-web =it works fine because Spring's built-in mechanisms that handle session attributes implicitly.)
     then you need HttpSession,@SessionAttributes(), @ModelAttribute(with("Object") which is there in or without ())
     2.if you use (spring-boot-starter-web) then you need HttpSession,@SessionAttributes(for getting session object (not required in login controller)) that's  it(but i add @ModelAttribute(sumne))
     1.The "Model" interface is fundamental in Spring MVC for transferring data between the controller and the view, making it a crucial component in rendering dynamic web pages.

     */
    @PostMapping("complaintRaise")
    public String complaintRaise(@ModelAttribute("signUpDTO") SignUpDTO signUpDTO,@ModelAttribute("complaintRaiseDTO") ComplaintRaiseDTO complaintRaiseDTO, RedirectAttributes redirectAttributes,Model model)

    {
        //Accessing from signup dto
        int signedUserId=signUpDTO.getId();
        log.info("signed in user id:"+signedUserId);

        //set the signed in user id in raiseComplaintDTO
        SignUpDTO userId=new SignUpDTO();
        userId.setId(signedUserId);
        complaintRaiseDTO.setSignUpDTO(userId);

       boolean data=complaintRaiseService.saveComplaintRaiseDetails(complaintRaiseDTO);
       if(data)
       {
           log.info("complaintRaiseService registration successful in complaintRaiseController.");
           redirectAttributes.addFlashAttribute("complaintRaiseMsg","ComplaintRaise Registration Successful: "+complaintRaiseDTO.getComplaintId()
                   +" , "+complaintRaiseDTO.getComplaintType());
           model.addAttribute("complaintRaiseMsg","success");
       }
       else
       {
           log.info("complaintRaiseService registration is not  successful in complaintRaiseController.");
           redirectAttributes.addFlashAttribute("complaintRaiseMsg","ComplaintRaise Registration failed: "+complaintRaiseDTO.getComplaintId()
                   +" , "+complaintRaiseDTO.getComplaintType());
           model.addAttribute("complaintRaiseMsg","not success");
       }


        return "redirect:/viewComplaintRaise"; //the request is redirect to another page that page should get the request

    }
//    @GetMapping("complaintRaise")
//    public String showRaiseComplaintPage(Model model) {
//        return "ComplaintRaise";
//    }

    @GetMapping("viewComplaintRaise")
   public String viewComplaintRaise(@ModelAttribute("signUpDTO")SignUpDTO signUpDTO,Model model)
{
    int userId=signUpDTO.getId();
    log.info("signUpDTO Id:{}",userId);

    List<ComplaintRaiseDTO> listOfComplaintData = complaintRaiseService.findByComplaintsByUserId(userId);
    model.addAttribute("viewComplaintRaise",listOfComplaintData);
    //log.info("complaint data in view page:{}",listOfComplaintData);
    return "ComplaintRaiseView";
}
//just getting complaintId
    @GetMapping("/editComplaintRaise/{complaintId}")
    public String showEditComplaintRaise(@PathVariable("complaintId") int complaintId,Model model)
    {
       ComplaintRaiseDTO complaintRaiseDTO= complaintRaiseService.findByComplaintId(complaintId);
       model.addAttribute("complaintRaiseDTO",complaintRaiseDTO);//values should be retain in page


        return "EditComplaintRaise";
    }


    @PostMapping("updateComplaintDetails")
    public String updateComplaintDetails(@ModelAttribute("complaintRaiseDTO")ComplaintRaiseDTO complaintRaiseDTO,Model model)
    {
       List<ComplaintRaiseDTO> complaintUpdated=complaintRaiseService.updateEditedComplaints(complaintRaiseDTO);
        if (complaintUpdated!=null)
        {
            model.addAttribute("updateMsg", "Complaint updated successfully!");
            model.addAttribute("viewComplaintRaise", complaintUpdated); //for table data view raise complaint
            return "ComplaintRaiseView";
        } else
        {
            model.addAttribute("updateErrorMsg", "Failed to update complaint. Please try again.");
        }
        return "EditComplaintRaise";
    }
}
