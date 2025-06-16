package com.xworkz.issuemanagement.controller;

import com.xworkz.issuemanagement.constants.Status;
import com.xworkz.issuemanagement.dto.ComplaintRaiseDTO;
import com.xworkz.issuemanagement.dto.DepartmentDTO;
import com.xworkz.issuemanagement.dto.EmployeeDTO;
import com.xworkz.issuemanagement.dto.RegDeptAdminDTO;
import com.xworkz.issuemanagement.emailSending.MailSend;
import com.xworkz.issuemanagement.model.repo.EmployeeRepo;
import com.xworkz.issuemanagement.model.repo.RegDeptAdminRepo;
import com.xworkz.issuemanagement.model.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.mail.SimpleMailMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.awt.*;
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

    @Autowired
    private MailSend mailSend;

    @Autowired
    private ComplaintRaiseService complaintRaiseService;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private RegDeptAdminRepo regDeptAdminRepo;







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

                String  emailStatus = regDeptAdminService.saveRegDeptAdmin(regDeptAdminDTO);
                if ("success".equals(emailStatus)) {
                    httpSession.setAttribute("subAdminEmail", regDeptAdminDTO.getEmail());
                    log.info("regDeptAdminService() in RegDeptAdminController successful:{}", emailStatus);
                    redirectAttributes.addFlashAttribute("saveDeptAdmin",
                            "Sign Up successful: " + regDeptAdminDTO.getAdminName() +
                                    "<br>" + "Send  email for password.");
                    return "redirect:getDepartmentName";
                }
                else if ("network_error".equals(emailStatus)) {
                    redirectAttributes.addFlashAttribute("errorInMail", "Network issue while sending the email. Please try again later");
                    return "redirect:getDepartmentName";
                }

                else if ("error".equals(emailStatus))
                {
                    log.error("regDeptAdminService() in RegDeptAdminController not successful:");
                    redirectAttributes.addFlashAttribute("errorInMail",
                            "Sign Up is not successful: " + regDeptAdminDTO.getAdminName());
                    return "redirect:getDepartmentName";
                }

            }
            catch (NullPointerException e) {
                log.error("NullPointerException during saveRegDeptAdmin: Possibly a missing field or null value.", e);
                redirectAttributes.addFlashAttribute("errorInMail", "An unexpected error occurred. Please try again.");
                return "redirect:getDepartmentName";
            }
            catch (Exception e)
            {
                log.error("Exception during saveRegDeptAdmin: ", e);
                redirectAttributes.addFlashAttribute("errorInMail", "An error occurred during Sign Up. Please try again.");
                return "redirect:getDepartmentName";
            }
            finally {
                // Clear session attributes or handle as needed
                httpSession.removeAttribute("subAdminEmail");
            }
        }
        return "redirect:getDepartmentName";
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
        // httpSession.setAttribute("departmentName",departmentName);


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


//    //********sub Admin********
//    @PostMapping("sub-admin-forgot-password")
//    public String subAdminForgotPassword(@RequestParam String email, Model model)
//    {
//        // Fetch the list of departments for departmentName
//        List<DepartmentDTO> departments = adminService.findByDepartmentName();
//        if(!departments.isEmpty())
//        {
//            log.info("departments name:{}", departments);
//            model.addAttribute("departments", departments);// Fetch the list of departments for departmentNames
//        }
//
//        if(forgotPasswordService.forgotPasswordBySubAdmin(email))
//        {
//            model.addAttribute("forgotPasswordMsg", "A new password has been sent to your email.");
//            return "DepartmentLogInPage";
//        }
//        else
//        {
//            model.addAttribute("forgotPasswordError", "Email address not found.");
//
//        }
//        return"SubAdminForgotPassword";
//    }
//@PostMapping("sub-admin-forgot-password")
//public String subAdminForgotPassword(@RequestParam String email, Model model,RedirectAttributes redirectAttributes) {
//    // Fetch the list of departments for departmentName
//    List<DepartmentDTO> departments = adminService.findByDepartmentName();
//    if (!departments.isEmpty()) {
//        log.info("departments name:{}", departments);
//        model.addAttribute("departments", departments);
//    }
//
//    if (forgotPasswordService.forgotPasswordBySubAdmin(email)) {
//        // Prepare the email message
//        SimpleMailMessage emailMessage = new SimpleMailMessage();
//        emailMessage.setTo(email);
//        emailMessage.setSubject("Sub-Admin Password Reset");
//        emailMessage.setText("A new password has been generated and sent to your email.");
//
//        // Send the email and check for network issues
//        String emailStatus = mailSend.sendEmail(emailMessage);
//        if ("network_error".equals(emailStatus)) {
//            model.addAttribute("forgotPasswordError", "Network issue while sending the reset email. Please try again later.");
//            return "SubAdminForgotPassword";
//        } else if ("send_error".equals(emailStatus)) {
//            model.addAttribute("forgotPasswordError", "Password reset successful, but email sending failed. Please check your email settings.");
//            return "SubAdminForgotPassword";
//        }
//
//        model.addAttribute("msg", "A new password has been sent to your email.");
//        return "DepartmentLogInPage";
//    } else {
//        redirectAttributes.addFlashAttribute("forgotPasswordError", "Email address not found.");
//        return "redirect:/subAdminForgotPassword";
//    }
//}


    @PostMapping("sub-admin-forgot-password")
    public String subAdminForgotPassword(@RequestParam String email, Model model, RedirectAttributes redirectAttributes) {
        // Fetch the list of departments for departmentName
        List<DepartmentDTO> departments = adminService.findByDepartmentName();
        if (!departments.isEmpty()) {
            log.info("Departments name: {}", departments);
            model.addAttribute("departments", departments);
        }

        // Call the forgotPasswordBySubAdmin service method and handle the outcome
        String result = forgotPasswordService.forgotPasswordBySubAdmin(email);

        if ("success".equals(result)) {
            // If email was sent successfully
            model.addAttribute("msg", "A new password has been sent to your email.");
            return "DepartmentLogInPage"; // Redirect to login page
        } else if ("network_error".equals(result)) {
            // Handle network error while sending email
            model.addAttribute("forgotPasswordError", "Network issue while sending the reset email. Please try again later.");
            return "SubAdminForgotPassword"; // Stay on forgot password page
        } else if ("send_error".equals(result)) {
            // Handle email sending error
            model.addAttribute("forgotPasswordError", "Password reset successful, but email sending failed. Please check your email settings.");
            return "SubAdminForgotPassword"; // Stay on forgot password page
        } else if ("user_not_found".equals(result)) {
            // If the email is not found in the system
            redirectAttributes.addFlashAttribute("forgotPasswordError", "Email address not found.");
            return "redirect:/subAdminForgotPassword"; // Redirect with error message
        }

        // Default return for any unexpected outcomes
        return "SubAdminForgotPassword";
    }


    @GetMapping("subAdminForgotPassword")
public String subAdminForgotPassword()
{
    return "SubAdminForgotPassword";
}


    @PostMapping("subAdminChangePassword")
    public String changePasswordSubAdmin(@RequestParam String email, String oldPassword, String newPassword, String confirmPassword,Model model,RedirectAttributes redirectAttributes)
    {
        // Fetch the list of departments for departmentName
        List<DepartmentDTO> departments = adminService.findByDepartmentName();
        if(!departments.isEmpty())
        {
            log.info("departments name:{}", departments);
            model.addAttribute("departments", departments);// Fetch the list of departments for departmentNames
        }


        String passwordChangeStatus=changePasswordService.subAdminChangePassword(email,oldPassword,newPassword,confirmPassword);
        // Step 3: Handle the various return statuses
        switch (passwordChangeStatus) {
            case "password_mismatch":
                redirectAttributes.addFlashAttribute("error", "New password and confirm password do not match.");
                break;

            case "user_not_found":
                redirectAttributes.addFlashAttribute("error", "User with the provided email was not found.");
                break;

            case "old_password_incorrect":
                redirectAttributes.addFlashAttribute("error", "The old password is incorrect.");
                break;

            case "network_error":
                redirectAttributes.addFlashAttribute("error", "Network issue occurred while sending the email.");
                break;

            case "send_error":
                redirectAttributes.addFlashAttribute("error", "Failed to send confirmation email.");
                break;

            case "update_failed":
                redirectAttributes.addFlashAttribute("error", "Failed to update the password. Please try again.");
                break;

            case "success":
                model.addAttribute("msg", "Password reset successful. A confirmation email has been sent.");
                return "SubAdminChangePassword"; // Render the password change page with a success message

            default:
                redirectAttributes.addFlashAttribute("error", "An unexpected error occurred. Please try again.");
                break;
        }

        // Step 4: Redirect back to the password change page with error messages
        return "redirect:/subAdminChangePassword";
    }
//        if(password) {
//            model.addAttribute("passwordResetMessage", "Password reset successful");
//            return "SubAdminChangePassword";
//        }
//        else {
//            model.addAttribute("passwordResetError", "Failed to reset password.Please check your password");
//            return "SubAdminChangePassword";
//        }
//
//
//    }
//@PostMapping("subAdminChangePassword")
//public String changePasswordSubAdmin(@RequestParam String email,
//                                     @RequestParam String oldPassword,
//                                     @RequestParam String newPassword,
//                                     @RequestParam String confirmPassword,
//                                     RedirectAttributes redirectAttributes,
//                                     Model model) {
//    // Fetch the list of departments for departmentName
//    List<DepartmentDTO> departments = adminService.findByDepartmentName();
//    if (!departments.isEmpty()) {
//        log.info("departments name:{}", departments);
//        model.addAttribute("departments", departments);
//    }
//
//    boolean passwordChanged = changePasswordService.subAdminChangePassword(email, oldPassword, newPassword, confirmPassword);
//    if(passwordChanged)
//    {
//        model.addAttribute("msg", "Password reset successful. A confirmation email has been sent.");
//        return "SubAdminChangePassword";
//    } else {
//        redirectAttributes.addFlashAttribute("passwordResetError", "Failed to reset password. Please check your old password.");
//        return "redirect:/subAdminChangePassword";
//    }
//}

@GetMapping("subAdminChangePassword")
public String SubAdminChangePassword()
{
    return "SubAdminChangePassword";
}

    @GetMapping("department-admin-complaintViewPage")
    public String deptAdminViewComplaint( Model model,HttpServletRequest httpServletRequest,HttpSession session) {
        log.info("departments name in RegDeptController:");

       HttpSession httpSession1= httpServletRequest.getSession();
       String departmentName1= (String) httpSession1.getAttribute("departmentName");

       RegDeptAdminDTO departmentAdminName= (RegDeptAdminDTO) session.getAttribute("SubAdminName");
       model.addAttribute("departmentName",departmentAdminName.getAdminName());

       List<EmployeeDTO> employeeNames = regDeptAdminService.getAllEmployeeNames(departmentName1);

       // Check if employeeNames is not null before calling isEmpty()
        if (employeeNames != null && !employeeNames.isEmpty()) {
            log.info("employee names: {}", employeeNames);
            model.addAttribute("employeeNames", employeeNames); // Fetch the list of departments for departmentNames
        } else {
            // Optionally, log or handle the case where employeeNames is null or empty
            log.info("No active employees found for department: {}", departmentName1);
            // Optionally, add an empty list or a different message to the model if needed
            model.addAttribute("empNames","No active employees found for department  "+ departmentName1 +"  Register New Employee for This Department");
        }


        List<ComplaintRaiseDTO> complaintRaiseDetails = regDeptAdminService.deptAdminView(departmentName1);
        log.info("dept name: {}", departmentName1);

        if (complaintRaiseDetails != null && !complaintRaiseDetails.isEmpty()) {
            log.info("departments name in RegDeptController is successful:");
            model.addAttribute("viewRaiseComplaintForSubAdmin", complaintRaiseDetails);
            return "DepartmentAdminComplaintViewPage";
        } else {
            log.info("No complaints found for the department or an error occurred.");
            // Optionally, you can add a message or an empty list to the model to handle this case on the UI side
            model.addAttribute("viewRaiseComplaintForSubAdmin", Collections.emptyList());
            model.addAttribute("message", "No complaints found for the department.");
            return "DepartmentAdminComplaintViewPage";
        }
    }

@PostMapping("updateEmployee")
public String updateEmployee(@RequestParam("complaintId") int complaintId,@RequestParam(value = "employee_id",required = false) Integer employeeId,ComplaintRaiseDTO complaintRaiseDTO,HttpSession httpSession,RedirectAttributes redirectAttributes,Model model)
{
    if (employeeId == null || complaintId ==0) {
        // Handle the case where no employee is selected
        redirectAttributes.addFlashAttribute("error", "Please select an employee.");
        return "redirect:/department-admin-complaintViewPage";
    }
    //boolean data= regDeptAdminService.updateStatusAndEmployeeId(complaintId,employeeId,complaintRaiseDTO.getStatus());
    boolean data= regDeptAdminService.updateStatusAndEmployeeId(complaintId,employeeId);

    if(data)
    {
        log.info("update:"+data);
        //to store compliantDetails of employee in session to get that complaintsDetails in employeeViewComplaints
      List<ComplaintRaiseDTO> complaintRaiseDTOList= regDeptAdminRepo.findById(employeeId);
      httpSession.setAttribute("ParticularEmployeeComplaintsDetails",complaintRaiseDTOList);
      log.info("ParticularEmployeeComplaintsDetails store in session to get it in employee View ComplaintsDetails");

    }
    else
    {
        log.info("No update:" + data);
    }
    redirectAttributes.addFlashAttribute("msg","Employee Updated Successfully");
    return "redirect:/department-admin-complaintViewPage";
}

//    @GetMapping("UpdateDepartment")
//    public String updateDepartment()
//    {
//        return "AdminViewComplaintRaiseDetails";
//    }

//    @PostMapping("deactivateEmployeeStatus/{employee_id}")
//    public String deactivateEmployeeStatus(@PathVariable("employee_id") Integer employee_id, RedirectAttributes redirectAttributes) {
//
//        log.info("Update deactivate status of employee in RegDeptAdminController");
//
//        try {
//            // Check if employee_id is valid (not null and greater than 0)
//            if (employee_id == null || employee_id <= 0) {
//                redirectAttributes.addFlashAttribute("message", "Please select a valid employee to deactivate.");
//                return "redirect:/department-admin-complaintViewPage";
//            }
//
//
//
//            // Check if the employee is allocated
//            boolean isAllocated = regDeptAdminService.isEmployeeAllocated(employee_id);
//            if (isAllocated) {
//                // Deactivate the employee
//                regDeptAdminService.deactivateStatus(employee_id, Status.INACTIVE);
//                redirectAttributes.addFlashAttribute("message", "Employee deactivated/deleted successfully.");
//                log.info("Employee deactivated successfully");
//            } else {
//                redirectAttributes.addFlashAttribute("message", "Employee must be allocated before deactivation.");
//            }
//        } catch (Exception e) {
//            // Handle any unexpected exceptions
//            redirectAttributes.addFlashAttribute("message", "Failed to deactivate employee status. Please try again.");
//            log.error("Error deactivating employee status", e);
//        }
//
//        return "redirect:/department-admin-complaintViewPage";
//    }


//    @PostMapping("deactivateEmployeeStatus/{employee_id}")
//    public String deactivateEmployeeStatus(@PathVariable("employee_id") Integer employee_id, RedirectAttributes redirectAttributes) {
//
//        log.info("Update deactivate status of employee in RegDeptAdminController");
//        if (employee_id == null || employee_id <= 0) {
//            // Handle the case where no employee is selected
//            redirectAttributes.addFlashAttribute("error", "Please select an employee.");
//            return "redirect:/department-admin-complaintViewPage";
//        }
//        try {
//            // Check if the employee is allocated
//            boolean isAllocated = regDeptAdminService.isEmployeeAllocated(employee_id);
//
//            if (isAllocated) {
//                regDeptAdminService.deactivateStatus(employee_id, Status.INACTIVE);
////                ComplaintRaiseDTO complaintRaiseDTO=new ComplaintRaiseDTO();
////                complaintRaiseDTO.setEmployeeDTO(null);
////                regDeptAdminService.updateComplaintForDeactivatedEmployee(complaintRaiseDTO);
//                redirectAttributes.addFlashAttribute("message", "Employee deactivated/Deleted successfully.");
//            } else {
//                redirectAttributes.addFlashAttribute("message", "Employee must be allocated before deactivation.");
//            }
//        } catch (Exception e) {
//            redirectAttributes.addFlashAttribute("message", "Failed to deactivate employee status.");
//            log.error("Error deactivating employee status", e);
//        }
//
//        return "redirect:/department-admin-complaintViewPage";
//    }


    @PostMapping("deactivateEmployeeStatus")
    public String deactivateEmployeeStatus(@RequestParam("employee_id") String employee_id,@RequestParam("complaintId") Integer complaintId, RedirectAttributes redirectAttributes) {


        log.info("Update deactivate status of employee in RegDeptAdminController");

        if ( employee_id == null || employee_id.trim().isEmpty() ) {
            // Handle the case where no employee is selected
            redirectAttributes.addFlashAttribute("error", "Employee must be allocated before deletion.");
            log.info( "Employee must be allocated before deletion.");
            return "redirect:/department-admin-complaintViewPage";
        } else if (complaintId==null) {

            redirectAttributes.addFlashAttribute("error", " jjjEmployee must be allocated before deletion.");
            log.info( "Employee must be allocated before deletion.jjjj");
            return "redirect:/department-admin-complaintViewPage";
        }
        int empId;
        try
        {// Convert to Integer
             empId = Integer.parseInt(employee_id); // Throws NumberFormatException if not a valid integer
        }
        catch (NumberFormatException e)
        {
            redirectAttributes.addFlashAttribute("error", "Invalid employee ID.");
            return "redirect:/department-admin-complaintViewPage";
        }

        boolean isAllocated = regDeptAdminService.isEmployeeAllocated(empId);

        if (isAllocated) {
            regDeptAdminService.deactivateStatus(empId, Status.INACTIVE);

            ComplaintRaiseDTO makingEmployee_fkNull = complaintRaiseService.findByComplaintId(complaintId);
            makingEmployee_fkNull.setEmployeeDTO(null);//making null in ComplaintDTO so we can get else part
            complaintRaiseService.saveComplaintRaiseDetails(makingEmployee_fkNull);

            //below three to make update noOfEmployee while inActive in DepartmentDTO
            // Obtain the department ID from the employeeDTO
            //int departmentId = employeeDTO.getDepartmentDTO().getId();
            EmployeeDTO employeeDTO = employeeRepo.findById(empId);
            int departmentId = employeeDTO.getDepartmentDTO().getId();


            // Fetch the updated count of active employees
            Long noOfActiveEmployees = employeeRepo.countNoOfActiveEmployeeByDeptId(departmentId);

            // Fetch the department entity
            DepartmentDTO departmentDTO = employeeRepo.findByDepartmentId(departmentId);

            if (departmentDTO != null) {
                // Update the number of active employees in the department
                departmentDTO.setNoOfEmployees(noOfActiveEmployees);
                employeeRepo.updateDepartmentDTO(departmentDTO); // Assuming this method updates the department in the repository

                log.info("Employee details saved and department employee count updated successfully.");

                redirectAttributes.addFlashAttribute("message", "Employee Deactivate/Deleted successfully.");
                log.info("Employee Deactivate/Deleted successfully.");
            }
        }

       else
       {

            // If employee is allocated, show the message to allocate the employee first
            redirectAttributes.addFlashAttribute("error", " Employee is not  allocated for any complaint for Deletion");
            log.info( " Employee is not  allocated for any complaint for Deletion");

            return "redirect:/department-admin-complaintViewPage";

        }

        return "redirect:/department-admin-complaintViewPage";
    }


}


