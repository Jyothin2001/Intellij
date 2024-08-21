package com.xworkz.issuemanagement.controller;

import com.xworkz.issuemanagement.dto.DepartmentDTO;
import com.xworkz.issuemanagement.dto.RegDeptAdminDTO;
import com.xworkz.issuemanagement.model.service.AdminService;
import com.xworkz.issuemanagement.model.service.RegDeptAdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
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

    public RegDeptAdminController()
    {
        log.info("RegDeptAdminController constructor:");
    }

    @PostMapping("DepartmentSignUp")
    public String saveRegDeptAdmin(@Valid RegDeptAdminDTO regDeptAdminDTO, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes,DepartmentDTO departmentDTO)
    {
        if(bindingResult.hasErrors())
        {
            log.info("RegisterDepartmentAdminDTO has invalid data");
            bindingResult.getAllErrors().forEach(objectError-> System.out.println(objectError.getDefaultMessage()));
            model.addAttribute("errors",bindingResult.getAllErrors());
            //redirectAttributes.addFlashAttribute("errors",bindingResult.getAllErrors());
            model.addAttribute("regDeptAdminDTO",regDeptAdminDTO);//this retaining values  form page

            return"DepartmentSignUpPage";
        }
        else
        {
            DepartmentDTO departmentDTO1 = regDeptAdminService.findByDepartmentType(regDeptAdminDTO.getDepartmentName());
            //if (departmentDTO1 != null)
                regDeptAdminDTO.setDepartmentDTO(departmentDTO1);
                log.info("RegDeptAdminDTO data:{}", regDeptAdminDTO);


                boolean saveRegDeptAdmin = regDeptAdminService.saveRegDeptAdmin(regDeptAdminDTO);
                if (saveRegDeptAdmin)
                {
                    httpSession.setAttribute("subAdminEmail", regDeptAdminDTO.getEmail());
                    log.info("regDeptAdminService() in RegDeptAdminController successful:{}", saveRegDeptAdmin);
                    redirectAttributes.addFlashAttribute("saveDeptAdmin", "Sign Up successful: " + regDeptAdminDTO.getAdminName() + "\n\n, Please check your email for your password.");
                    return "redirect:/getDepartmentName";
                }
                else
                {
                     log.info("regDeptAdminService() in RegDeptAdminController not successful:");
                     redirectAttributes.addFlashAttribute("saveDeptAdmin", "Sign Up is not successful: " + regDeptAdminDTO.getAdminName());
                    return"redirect:/getDepartmentName";
                }

    }
        }

@GetMapping("getDepartmentName")
public String getDepartmentNames(Model model)
{
    // Fetch the list of departments for departmentName
    List<DepartmentDTO> departments = adminService.findByDepartmentName();
    if(!departments.isEmpty()) {
        log.info("departments name:{}", departments);
        model.addAttribute("departments", departments);// Fetch the list of departments for departmentNames
    }
    return "DepartmentSignUpPage";
}


@PostMapping("DepartmentLogInPage")
    public String departmentAdminLoginIn(@RequestParam("email") String email,@RequestParam String password,@RequestParam String departmentName, RedirectAttributes redirectAttributes,Model model)
{
    log.info("departmentAdminLoginIn method running in RegDeptAdminController..");
   // httpSession.setAttribute("subAdminEmail",email);


    RegDeptAdminDTO logIn=regDeptAdminService.getEmailAndPassword(email,password,departmentName);
    if (logIn!=null)
    {
        log.info("departmentAdminLoginIn successful AdminController..");
        regDeptAdminService.resetFailedAttempts(email);
        model.addAttribute("msg","Login Successful " + logIn.getAdminName());
        model.addAttribute("AdminName",logIn.getAdminName());
        return "SubAdminProfilePage";
    }
    else
    {
        log.info("departmentAdminLoginIn not successful in AdminController..");
        regDeptAdminService.incrementFailedAttempts(email);

        int failedAttempts=regDeptAdminService.getFailedAttempts(email);
        //redirectAttributes.addFlashAttribute("Msg", "Failed to login please check your email and password");
        if(failedAttempts>3)
        {
            regDeptAdminService.lockAccount(email);
            redirectAttributes.addFlashAttribute("accountError", "Your account is locked due to too many failed attempts.");
        }
        else
        {
            redirectAttributes.addFlashAttribute("errorMsg", "Invalid email id and password. Attempts:" + failedAttempts);
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













}
