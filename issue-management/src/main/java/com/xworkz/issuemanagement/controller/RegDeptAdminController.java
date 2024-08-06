package com.xworkz.issuemanagement.controller;

import com.xworkz.issuemanagement.dto.RegDeptAdminDTO;
import com.xworkz.issuemanagement.model.service.RegDeptAdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@Slf4j
@RequestMapping("/")
public class RegDeptAdminController
{

    @Autowired
    private RegDeptAdminService regDeptAdminService;

    public RegDeptAdminController()
    {
        log.info("RegDeptAdminController constructor:");
    }

    @PostMapping("DepartmentSignUp")
    public String saveRegDeptAdmin(@Valid RegDeptAdminDTO regDeptAdminDTO, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes)
    {
        log.info("saveRegDeptAdmin() in RegDeptAdminController ");
        log.info("RegDeptAdminDTO data:{}",regDeptAdminDTO);
        if(bindingResult.hasErrors())
        {
            log.info("RegisterDepartmentAdminDTO has invalid data");
            bindingResult.getAllErrors().forEach(objectError-> System.out.println(objectError.getDefaultMessage()));
            //model.addAttribute("errors",bindingResult.getAllErrors());
            //redirectAttributes.addFlashAttribute("errors",bindingResult.getAllErrors());
            model.addAttribute("regDeptAdminDTO",regDeptAdminDTO);//this retaining values  form page

            return"DepartmentSignUpPage";
        }
        else {
            boolean saveRegDeptAdmin = regDeptAdminService.saveRegDeptAdmin(regDeptAdminDTO);
            if (saveRegDeptAdmin) {
                log.info("regDeptAdminService() in RegDeptAdminController successful:{}", saveRegDeptAdmin);
                redirectAttributes.addFlashAttribute("saveDeptAdmin", "Sign Up successful: " + regDeptAdminDTO.getAdminName() + ", Please check your email for your password.");
                return "redirect:DepartmentSignUpPage";
            } else {
                log.info("regDeptAdminService() in RegDeptAdminController not successful:");
                redirectAttributes.addFlashAttribute("saveDeptAdmin", "Sign Up is not successful: " + regDeptAdminDTO.getAdminName());
            }
        }


        return"redirect:DepartmentSignUpPage";
    }



@PostMapping("DepartmentLogInPage")
    public String departmentAdminLoginIn(@RequestParam("email") String email,@RequestParam String password,RedirectAttributes redirectAttributes,Model model)
{




    return"redirect:DepartmentLogInPage";
}



}
