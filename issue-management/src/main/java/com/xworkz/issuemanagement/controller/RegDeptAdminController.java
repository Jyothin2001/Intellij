package com.xworkz.issuemanagement.controller;

import com.xworkz.issuemanagement.constants.Status;
import com.xworkz.issuemanagement.dto.ComplaintRaiseDTO;
import com.xworkz.issuemanagement.dto.DepartmentDTO;
import com.xworkz.issuemanagement.dto.EmployeeDTO;
import com.xworkz.issuemanagement.dto.RegDeptAdminDTO;
import com.xworkz.issuemanagement.model.service.AdminService;
import com.xworkz.issuemanagement.model.service.ChangePasswordService;
import com.xworkz.issuemanagement.model.service.ForgotPasswordService;
import com.xworkz.issuemanagement.model.service.RegDeptAdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/")
@SessionAttributes("regDeptAdminDTO")
public class RegDeptAdminController
{

    @Autowired
    private RegDeptAdminService regDeptAdminService;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private AdminService adminService;

    @Autowired
    private ForgotPasswordService forgotPasswordService;

    @Autowired
    private ChangePasswordService changePasswordService;





    public RegDeptAdminController()
    {
        log.info("RegDeptAdminController constructor:");
    }

    @PostMapping("DepartmentSignUp")
    public String saveRegDeptAdmin(@Valid RegDeptAdminDTO regDeptAdminDTO, BindingResult bindingResult,
                                   Model model, RedirectAttributes redirectAttributes,
                                   DepartmentDTO departmentDTO, HttpSession httpSession)
    {
        if (bindingResult.hasErrors()) {
            log.info("RegisterDepartmentAdminDTO has invalid data");
            bindingResult.getAllErrors().forEach(objectError -> log.error(objectError.getDefaultMessage()));
            model.addAttribute("errors", bindingResult.getAllErrors());
            model.addAttribute("regDeptAdminDTO", regDeptAdminDTO); // Retain form values
            return "DepartmentSignUpPage";
        } else {
            try {
                // Fetch department based on the department name
                DepartmentDTO departmentDTO1 = regDeptAdminService.findByDepartmentType(regDeptAdminDTO.getDepartmentName());
                if (departmentDTO1 != null) {
                    regDeptAdminDTO.setDepartmentDTO(departmentDTO1);
                } else {
                    log.error("Department not found: {}", regDeptAdminDTO.getDepartmentName());
                    redirectAttributes.addFlashAttribute("saveDeptAdmin", "Department not found: " + regDeptAdminDTO.getDepartmentName());
                    return "redirect:getDepartmentName";
                }

                log.info("RegDeptAdminDTO data:{}", regDeptAdminDTO);

                boolean saveRegDeptAdmin = regDeptAdminService.saveRegDeptAdmin(regDeptAdminDTO);
                if (saveRegDeptAdmin) {
                    httpSession.setAttribute("subAdminEmail", regDeptAdminDTO.getEmail());
                    log.info("regDeptAdminService() in RegDeptAdminController successful:{}", saveRegDeptAdmin);
                    redirectAttributes.addFlashAttribute("saveDeptAdmin",
                            "Sign Up successful: " + regDeptAdminDTO.getAdminName() +
                                    "\n\n, Please check your email for your password.");
                    return "redirect:getDepartmentName";
                } else {
                    log.error("regDeptAdminService() in RegDeptAdminController not successful:");
                    redirectAttributes.addFlashAttribute("saveDeptAdmin",
                            "Sign Up is not successful: " + regDeptAdminDTO.getAdminName());
                    return "redirect:getDepartmentName";
                }
            } catch (Exception e) {
                log.error("Exception during saveRegDeptAdmin: ", e);
                redirectAttributes.addFlashAttribute("saveDeptAdmin", "An error occurred during Sign Up. Please try again.");
                return "redirect:getDepartmentName";
            } finally {
                // Clear session attributes or handle as needed
                httpSession.removeAttribute("subAdminEmail");
            }
        }
    }

    @GetMapping("getDepartmentName")
    public String getDepartmentName(Model model) {
        // Fetch the list of departments for departmentName
        List<DepartmentDTO> departments = adminService.findByDepartmentName();
        if (!departments.isEmpty()) {
            log.info("departmentsName:{}", departments);
            model.addAttribute("departments", departments); // Fetch the list of departments for departmentNames
        }
        return "DepartmentSignUpPage";
    }


//    @PostMapping("DepartmentSignUp")
//    public String saveRegDeptAdmin(@Valid RegDeptAdminDTO regDeptAdminDTO, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes,DepartmentDTO departmentDTO)
//    {
//
//        if(bindingResult.hasErrors())
//        {
//            log.info("RegisterDepartmentAdminDTO has invalid data");
//            bindingResult.getAllErrors().forEach(objectError-> System.out.println(objectError.getDefaultMessage()));
//            model.addAttribute("errors",bindingResult.getAllErrors());
//            //redirectAttributes.addFlashAttribute("errors",bindingResult.getAllErrors());
//            model.addAttribute("regDeptAdminDTO",regDeptAdminDTO);//this retaining values  form page
//
//            return"DepartmentSignUpPage";
//        }
//        else
//        {
//            DepartmentDTO departmentDTO1 = regDeptAdminService.findByDepartmentType(regDeptAdminDTO.getDepartmentName());
//            regDeptAdminDTO.setDepartmentDTO(departmentDTO1);
//
//            log.info("RegDeptAdminDTO data:{}", regDeptAdminDTO);
//
//           boolean saveRegDeptAdmin = regDeptAdminService.saveRegDeptAdmin(regDeptAdminDTO);
//           if (saveRegDeptAdmin)
//           {
//                    httpSession.setAttribute("subAdminEmail", regDeptAdminDTO.getEmail());
//                    log.info("regDeptAdminService() in RegDeptAdminController successful:{}", saveRegDeptAdmin);
//                    redirectAttributes.addFlashAttribute("saveDeptAdmin", "Sign Up successful: " + regDeptAdminDTO.getAdminName() + "\n\n, Please check your email for your password.");
//
//                    return "redirect:getDepartmentName";
//           }
//
//            else
//            {
//                log.info("regDeptAdminService() in RegDeptAdminController not successful:");
//                redirectAttributes.addFlashAttribute("saveDeptAdmin", "Sign Up is not successful: " + regDeptAdminDTO.getAdminName());
//                return "redirect:getDepartmentName";
//            }
//
//        }
//
//    }
//
//
//    @GetMapping("getDepartmentName")
//    public String getDepartmentName(Model model)
//    {
//        // Fetch the list of departments for departmentName
//        List<DepartmentDTO> departments = adminService.findByDepartmentName();
//        if(!departments.isEmpty())
//        {
//            log.info("departmentsName:{}", departments);
//            model.addAttribute("departments", departments);// Fetch the list of departments for departmentNames
//        }
//        return "DepartmentSignUpPage";
//    }

    @PostMapping("DepartmentLogInPage")
    public String departmentAdminLoginIn(@RequestParam("email") String email, @RequestParam String password, @RequestParam String departmentName, RedirectAttributes redirectAttributes, Model model, HttpServletRequest httpServletRequest )
    {
        log.info("departmentAdminLoginIn method running in RegDeptAdminController..");
         httpSession.setAttribute("subAdminEmail",email);


        RegDeptAdminDTO logIn=regDeptAdminService.getEmailAndPassword(email,password,departmentName);
        if (logIn!=null)
        {
            // Store AdminName in the session
            httpSession.setAttribute("SubAdminName", logIn);


              log.info("department name going:"+departmentName);
           List<EmployeeDTO> employeeDTO= regDeptAdminService.getAllEmployeeNames(departmentName);

            log.info("Retrieved employeeNames: " + employeeDTO);
            if (employeeDTO != null && !employeeDTO.isEmpty()) {
                model.addAttribute("employees", employeeDTO);
            }

            HttpSession httpSession1= httpServletRequest.getSession();
           httpSession1.setAttribute("departmentName",logIn.getDepartmentName());

            log.info("departmentAdminLoginIn successful AdminController..");
            regDeptAdminService.resetFailedAttempts(email);

            return "redirect:subAdminProfilePage";
        }
        else
        {
            log.info("departmentAdminLoginIn not successful in AdminController..");
            regDeptAdminService.incrementFailedAttempts(email);

            int failedAttempts=regDeptAdminService.getFailedAttempts(email);
            //redirectAttributes.addFlashAttribute("Msg", "Failed to login please check your email and password");
            if(failedAttempts>=3)
            {
                regDeptAdminService.lockAccount(email);
                redirectAttributes.addFlashAttribute("accountError", "Your account is locked due to too many failed attempts.");
            }
            else
            {
                redirectAttributes.addFlashAttribute("errorMsg", "Invalid email id,password and Department Name. Attempts:" + failedAttempts);
            }
            return"redirect:DepartmentLoginPage";
        }
    }

    @GetMapping("DepartmentLoginPage")
    public String departmentLogInPage(Model model)
    {

        // Fetch the list of departments for departmentName
        List<DepartmentDTO> departments = adminService.findByDepartmentName();
        if(!departments.isEmpty())
        {
            log.info("departments name:{}", departments);
            model.addAttribute("departments", departments);// Fetch the list of departments for departmentNames
        }
        return"DepartmentLogInPage";
    }


    @GetMapping("subAdminProfilePage")
    public String subAdminProfilePage(Model model)
    {
        //Retrieve SubAdminName from the session
        RegDeptAdminDTO subAdminName = (RegDeptAdminDTO) httpSession.getAttribute("SubAdminName");

        if (subAdminName != null) {
           model.addAttribute("subAdminNames", subAdminName.getAdminName());
           model.addAttribute("msg","Welcome to your page " + subAdminName.getAdminName());
       }


        return "SubAdminProfilePage";
    }


    //********sub Admin********
    @PostMapping("sub-admin-forgot-password")
    public String subAdminForgotPassword(@RequestParam String email, Model model)
    {
        // Fetch the list of departments for departmentName
        List<DepartmentDTO> departments = adminService.findByDepartmentName();
        if(!departments.isEmpty())
        {
            log.info("departments name:{}", departments);
            model.addAttribute("departments", departments);// Fetch the list of departments for departmentNames
        }

        if(forgotPasswordService.forgotPasswordBySubAdmin(email))
        {
            model.addAttribute("forgotPasswordMsg", "A new password has been sent to your email.");
            return "DepartmentLogInPage";
        }
        else
        {
            model.addAttribute("forgotPasswordError", "Email address not found.");

        }
        return"SubAdminForgotPassword";
    }

    @PostMapping("subAdminChangePassword")
    public String changePasswordSubAdmin(@RequestParam String email, String oldPassword, String newPassword, String confirmPassword,Model model)
    {
        // Fetch the list of departments for departmentName
        List<DepartmentDTO> departments = adminService.findByDepartmentName();
        if(!departments.isEmpty())
        {
            log.info("departments name:{}", departments);
            model.addAttribute("departments", departments);// Fetch the list of departments for departmentNames
        }


        boolean password=changePasswordService.subAdminChangePassword(email,oldPassword,newPassword,confirmPassword);
        if(password) {
            model.addAttribute("passwordResetMessage", "Password reset successful");
            return "SubAdminChangePassword";
        }
        else {
            model.addAttribute("passwordResetError", "Failed to reset password.Please check your password");
            return "SubAdminChangePassword";
        }


    }

    @GetMapping("department-admin-complaintViewPage")
    public String deptAdminViewComplaint( Model model,HttpServletRequest httpServletRequest) {
        log.info("departments name in RegDeptController:");

       HttpSession httpSession1= httpServletRequest.getSession();
       String departmentName1= (String) httpSession1.getAttribute("departmentName");


       List<EmployeeDTO> employeeNames=regDeptAdminService.getAllEmployeeNames(departmentName1);
        if(!employeeNames.isEmpty())
        {
            log.info("employee names:{}", employeeNames);
            model.addAttribute("employeeNames", employeeNames);// Fetch the list of departments for departmentNames
        }


        List<ComplaintRaiseDTO> complaintRaiseDetails = regDeptAdminService.deptAdminView(departmentName1);
        log.info("dept name: {}", departmentName1);

        if (complaintRaiseDetails != null && !complaintRaiseDetails.isEmpty()) {
            log.info("departments name in RegDeptController is successful:");
            model.addAttribute("viewRaiseComplaint", complaintRaiseDetails);
            return "DepartmentAdminComplaintViewPage";
        } else {
            log.info("No complaints found for the department or an error occurred.");
            // Optionally, you can add a message or an empty list to the model to handle this case on the UI side
            model.addAttribute("viewRaiseComplaint", Collections.emptyList());
            model.addAttribute("message", "No complaints found for the department.");
            return "DepartmentAdminComplaintViewPage";
        }
    }

@PostMapping("updateEmployee")
public String updateEmployee(@RequestParam("complaintId") int complaintId,@RequestParam("employee_id") Integer employeeId,ComplaintRaiseDTO complaintRaiseDTO,RedirectAttributes redirectAttributes,Model model)
{
    if (employeeId == null) {
        // Handle the case where no employee is selected
        redirectAttributes.addFlashAttribute("error", "Please select an employee.");
        return "redirect:/viewComplaintRaiseDetails";
    }
    boolean data= regDeptAdminService.updateStatusAndEmployeeId(complaintId,employeeId,complaintRaiseDTO.getStatus());
    if(data)
    {
        log.info("update:"+data);
    }
    else
    {
        log.info("No update:" + data);
    }
    redirectAttributes.addFlashAttribute("msg","Updated Successfully");
    return "redirect:/department-admin-complaintViewPage";
}

//    @GetMapping("UpdateDepartment")
//    public String updateDepartment()
//    {
//        return "AdminViewComplaintRaiseDetails";
//    }




    @PostMapping("deactivateEmployeeStatus/{employee_id}")
    public String deactivateEmployeeStatus(@PathVariable("employee_id") int employee_id ,RedirectAttributes redirectAttributes)
    {
        log.info("update deactivate status of employee in RegDeptAdminController ");

        try
        {
            regDeptAdminService.deactivateStatus(employee_id, Status.INACTIVE);
            redirectAttributes.addFlashAttribute("message", "Employee Deleted updated successfully.");
        }
        catch (Exception e)
        {
        redirectAttributes.addFlashAttribute("message", "Failed to Delete employee status.");
         }

        return "redirect:/department-admin-complaintViewPage";
    }

 @GetMapping("deactivate-EmployeeStatus")
    public String inactivateEmployeeStatus()
 {
     return "DepartmentAdminComplaintViewPage";

 }




}
