package com.xworkz.issuemanagement.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.xworkz.issuemanagement.dto.DepartmentDTO;
import com.xworkz.issuemanagement.dto.EmployeeDTO;
import com.xworkz.issuemanagement.model.service.AdminService;
import com.xworkz.issuemanagement.model.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/")
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private AdminService adminService;


    public EmployeeController()
    {
        log.info("EmployeeController no args constructor:");
    }

    @PostMapping("employeeRegistration")
    private String saveEmployeeDetails(@Valid @ModelAttribute("employeeDTO")EmployeeDTO employeeDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors())
        {
            log.info("EmployeeDTO has invalid data");
            bindingResult.getAllErrors().forEach(objectError -> log.info(objectError.getDefaultMessage()));
            model.addAttribute("errors", bindingResult.getAllErrors());
            //redirectAttributes.addFlashAttribute("errors",bindingResult.getAllErrors());
            model.addAttribute("employeeDTO", employeeDTO);//this retaining values  form page

            return "DepartmentSignUpPage";
        }
        else {
            //to set departmentId in EmployeeTable
            DepartmentDTO departmentDTO=employeeService.findByDepartmentType(employeeDTO.getDepartmentName());
            employeeDTO.setDepartmentDTO(departmentDTO);//@Column name=department_id so in employeeTable the departmentId id store

            boolean employeeDetails = employeeService.saveEmployeeDetails(employeeDTO);
            if (employeeDetails)
            {
                log.info("saveEmployeeDetails() is successful in EmployeeController:{}", employeeDetails);
                model.addAttribute("saveEmployee", "Sign Up successful: " + employeeDTO.getEmployeeName() + "\n\n, Please check your email for your password.");
                return "EmployeeRegistrationForm";
            }
            else
            {
                log.info("saveEmployeeDetails() is not successful in EmployeeController");
                model.addAttribute("saveDeptAdmin", "Sign Up is not successful: " + employeeDTO.getEmployeeName());
            }


            return "EmployeeRegistrationForm";
        }
    }


    @GetMapping("get-Department-Names")
    public String getDepartmentNames(Model model)
    {
        // Fetch the list of departments for departmentName
        List<DepartmentDTO> departments = adminService.findByDepartmentName();
        if(!departments.isEmpty())
        {
            log.info("departmentsName:{}", departments);
            model.addAttribute("departments", departments);// Fetch the list of departments for departmentNames
        }
        return "EmployeeRegistrationForm";
    }

}
