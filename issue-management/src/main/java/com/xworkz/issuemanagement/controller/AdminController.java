package com.xworkz.issuemanagement.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.xworkz.issuemanagement.dto.AdminDTO;
import com.xworkz.issuemanagement.dto.ComplaintRaiseDTO;
import com.xworkz.issuemanagement.dto.DepartmentDTO;
import com.xworkz.issuemanagement.dto.SignUpDTO;
import com.xworkz.issuemanagement.model.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.jws.WebParam;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequestMapping("/")
public class AdminController {

    @Autowired
    private AdminService adminService;



    public AdminController()
    {
        log.info("AdminController constructor:");
    }
    @PostMapping("admin")
    public String Admin(@RequestParam String email, @RequestParam  String password, RedirectAttributes redirectAttributes, Model model)
    {
        String adminUsername=adminService.getAdminName(email, password);
        log.info("username:{}",adminUsername);


         boolean data=adminService.findByEmailAndPassword(email,password);
        if (data) {
            log.info("findByEmailAndPassword successful in AdminController..");

            //for next page to display msg
            //model.addAttribute("AdminProfilePageMessage", "Welcome to Admin profile");
            redirectAttributes.addFlashAttribute("AdminProfilePageMessage", "Welcome to Admin profile: "+email);
            redirectAttributes.addFlashAttribute("username",adminUsername);

            //return "AdminProfilePage";
            return "redirect:/AdminProfilePage";
        } else {
            log.info("findByEmailAndPassword not successful in AdminController");
            redirectAttributes.addFlashAttribute("errorAdminMessage", "Failed to login. Please check your email and password.");

            return "redirect:/adminPage";
        }


    }
    @GetMapping("adminPage")
public String admin()
{
    return "AdminPage";
}

    @GetMapping("AdminProfilePage")
    public String AdminProfilePage()
    {
        return "AdminProfilePage";
    }

    //view user details(SignUp details)
@GetMapping("viewUserDetails")
public String viewUserDetails(SignUpDTO signUpDTO,Model model)
{
    System.out.println("viewUserDetails method in AdminController..");
    List<SignUpDTO> signUpDTOData = adminService.findByUserId(signUpDTO);

    if (signUpDTOData != null) {
        log.info("viewUserDetails successful in AdminController..");
        model.addAttribute("ViewUserDetails", signUpDTOData);
        return "AdminViewUserDetails";
    } else {
        log.info("view-user-details not  successful in AdminController..");
    }
    return"AdminViewUserDetails";
}



    //view Raise complaint details
    //IllegalStateException: Ambiguous mapping while same action in two methods
    //displaying viewComplaintRaiseDetails and searchByComplaintTypeAndCity in same jsp page so, we make sure model key same in both method
    @GetMapping("viewComplaintRaiseDetails")
    public String viewComplaintRaiseDetails(ComplaintRaiseDTO complaintRaiseDTO,RedirectAttributes redirectAttributes, Model model, DepartmentDTO departmentDTO)
    {
        List<ComplaintRaiseDTO> listOfCities=adminService.findAllCities();

        log.info("viewUserDetails method running in AdminController");

        List<ComplaintRaiseDTO> viewData = adminService.findByComplaintRaiseId(complaintRaiseDTO);

        // Fetch the list of complaints and departments
        List<DepartmentDTO> departments = adminService.findByDepartmentName(departmentDTO.getDepartmentName());


        if (viewData!=null || departments!=null) {
            model.addAttribute("viewRaiseComplaint", viewData);
            model.addAttribute("departments", departments);// Fetch the list of complaints and departments
            model.addAttribute("cities",listOfCities);

            log.info("View raise complaint data successful in AdminController");
            //return "redirect:ViewComplaintRaise";// data will gone so, use model(viewName problem)
            return "AdminViewComplaintRaiseDetails";
        } else {
            log.info("View raise complaint data not successful in AdminController.");
        }

        //return "redirect:ViewComplaintRaise";
        return"AdminViewComplaintRaiseDetails";
    }

//    @GetMapping("ViewComplaintRaise")
//    public String viewComplaintRaiseDetails()
//    {
//        return "AdminViewComplaintRaiseDetails";
//    }

    // Combined search endpoint for both OR and AND conditions
@PostMapping("searchComplaintTypeAndCity")
    public String searchByComplaintTypeAndCity(ComplaintRaiseDTO complaintRaiseDTO,DepartmentDTO departmentDTO,Model model)
{

    log.info("searchByComplaintType method running in AdminController..!!");
    List<ComplaintRaiseDTO> listOfTypeAndCity=adminService.searchByComplaintTypeAndCity(complaintRaiseDTO.getComplaintType(),complaintRaiseDTO.getCity());
    if(!listOfTypeAndCity.isEmpty())
    {
        List<DepartmentDTO> departments = adminService.findByDepartmentName(departmentDTO.getDepartmentName());
        model.addAttribute("departments", departments);// Fetch the list of complaints and departments

        model.addAttribute("viewRaiseComplaint",listOfTypeAndCity);
        return "AdminViewComplaintRaiseDetails";
    }
    else
    {
      List<ComplaintRaiseDTO> listOfTypeOrCity = adminService.searchByComplaintTypeOrCity(complaintRaiseDTO.getComplaintType(),complaintRaiseDTO.getCity());

           if(!listOfTypeOrCity.isEmpty())
           {
               List<DepartmentDTO> departments = adminService.findByDepartmentName(departmentDTO.getDepartmentName());
               model.addAttribute("departments", departments);// Fetch the list of complaints and departments

               model.addAttribute("viewRaiseComplaint",listOfTypeOrCity);
               return "AdminViewComplaintRaiseDetails";
           }
    }

    return "AdminViewComplaintRaiseDetails";
}
//@GetMapping()
////@GetMapping("cities")
//    public String cityNames(ComplaintRaiseDTO complaintRaiseDTO, Model model)
//{
//     List<ComplaintRaiseDTO> listOfCities=adminService.findAllCities();
//    if (listOfCities != null) {
//        log.info("listOfCities data successful in AdminController");
//        model.addAttribute("cities",listOfCities);
//        return "AdminViewComplaintRaiseDetails";
//    } else {
//        log.info("listOfCities data not successful in AdminController.");
//    }
//
//
//    return "AdminViewComplaintRaiseDetails";
//}

@PostMapping("saveDepartment")
    public String saveDepartment(DepartmentDTO departmentDTO,RedirectAttributes redirectAttributes)
{
   DepartmentDTO data= adminService.saveDepartment(departmentDTO);
    log.info("saveDepartment method running in AdminController..");

    if (data != null) {
        log.info("saveDepartment successful in AdminController..");
        redirectAttributes.addFlashAttribute("msgDepartment", "Successfully added department ");
//          return  "AdminAddComplaints";
        return "redirect:/saveDepartment";

    } else {
        log.info("saveDepartment not successful in AdminController..");

        redirectAttributes.addFlashAttribute("errorDepartment", "not Successfully added department");
    }

    //return "AdminAddComplaints";
    return "redirect:/saveDepartment";

}
@GetMapping("saveDepartment")
    public String saveDepartment()
{
    return "AdminAddDepartment";
}

@PostMapping("updateDepartment")
    public String updateComplaint(@RequestParam("complaintId") int complaintId,@RequestParam("departmentId") int departmentId,ComplaintRaiseDTO complaintRaiseDTO,RedirectAttributes redirectAttributes)
{

   boolean data= adminService.updateStatusAndDepartmentId(complaintId,departmentId,complaintRaiseDTO.getStatus());
   if(data)
   {
       System.out.println("update:"+data);
   }
   else {
       System.out.println("No update:"+data);
   }
    redirectAttributes.addFlashAttribute("msg","Updated Successfully");
    return "redirect:UpdateDepartment";
}

    @GetMapping("UpdateDepartment")
    public String updateDepartment()
    {
        return "AdminViewComplaintRaiseDetails";
    }





}

