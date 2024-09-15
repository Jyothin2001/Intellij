package com.xworkz.issuemanagement.controller;

import com.mysql.cj.xdevapi.ModifyStatement;
import com.xworkz.issuemanagement.constants.Status;
import com.xworkz.issuemanagement.dto.DepartmentDTO;
import com.xworkz.issuemanagement.dto.EmployeeDTO;
import com.xworkz.issuemanagement.emailSending.MailSend;
import com.xworkz.issuemanagement.model.service.AdminService;
import com.xworkz.issuemanagement.model.service.EmployeeService;
import com.xworkz.issuemanagement.util.EmailOTPGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Controller
@RequestMapping("/")
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private EmailOTPGenerator emailOTPGenerator;  // Inject the OTP generator

    @Autowired
    private MailSend mailSend;



    public EmployeeController()
    {
        log.info("EmployeeController no args constructor:");
    }

    @PostMapping("employeeRegistration")
    private String saveEmployeeDetails(@Valid @ModelAttribute("employeeDTO")EmployeeDTO employeeDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model, HttpSession session) {
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
            employeeDTO.setStatus(Status.ACTIVE);
            boolean employeeDetails = employeeService.saveEmployeeDetails(employeeDTO);
            if (employeeDetails)
            {

                log.info("saveEmployeeDetails() is successful in EmployeeController:{}", employeeDetails);
                redirectAttributes.addFlashAttribute("saveEmployee",  employeeDTO.getEmployeeName()+ ", Saved                                                                                                                                            successful: " );
                return "redirect:/get-Department-Names";

            }
            else
            {
                log.info("saveEmployeeDetails() is not successful in EmployeeController");
                redirectAttributes.addFlashAttribute("saveDeptAdmin", "Sign Up is not successful: " + employeeDTO.getEmployeeName());
                return "redirect:/get-Department-Names";
            }

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


    //*******************************************************

    // Employee Login with CAPTCHA and OTP generation
    @PostMapping("generateOtp")
    public String generateOtp(@RequestParam("email") String emailId,
                              @RequestParam("captcha") String captchaInput,
                              HttpServletRequest httpServletRequest,
                              HttpSession session,
                              Model model,
                              RedirectAttributes redirectAttributes) {
        log.info("generateOtp method running in EmployeeController..");

        session.setAttribute("email",emailId);

        // Check if the email exists in the database
        EmployeeDTO employeeDTO = employeeService.findByEmail(emailId);
        if (employeeDTO == null) {
            // If email does not exist, return an error message and redirect to login page
            redirectAttributes.addFlashAttribute("emailNotFound", "Email does not exist.");
            log.error("Email not found in the database: {}", emailId);
            return "redirect:/employeeLogin";
        }

        // Validate CAPTCHA
        String sessionCaptcha = (String) session.getAttribute("captcha");
        if (sessionCaptcha == null || !sessionCaptcha.equals(captchaInput)) {
            // CAPTCHA is invalid, return an error message
            redirectAttributes.addFlashAttribute("captchaError", "Invalid CAPTCHA.");
            log.error("Invalid CAPTCHA for email: {}", emailId);
            return "redirect:/employeeLogin";
        }
        //to display employee name in session for jsp
        session.setAttribute("employeeName", employeeDTO.getEmployeeName());

        // CAPTCHA is valid, proceed with OTP generation
        String otp = emailOTPGenerator.generateOtp();
        log.info("OTP generated: {}", otp);

        // Save OTP in employeeDTO and update in the database
        employeeDTO.setOtp(Long.parseLong(otp));
        boolean isSaved = employeeService.saveEmployeeDetails(employeeDTO);

        if (isSaved) {
            //to display employeeName in jsp
           model.addAttribute("employeeName",employeeDTO.getEmployeeName());

           model.addAttribute("welcomeMsg","Welcome To Your Profile");

            // Send OTP to the user's email
            mailSend.sendOtpToEmail(employeeDTO.getEmail(), otp);
            redirectAttributes.addFlashAttribute("generatedOTP", "OTP generated and sent to email.");
            log.info("OTP generated and sent to email: {}", emailId);
            return "redirect:/employee-OTP-page";
        } else {
            redirectAttributes.addFlashAttribute("failedToGenerateOTPError", "Failed to generate OTP, please try again.");
            log.error("Failed to save OTP for email: {}", emailId);
        }

        return "redirect:/employeeLogin"; // Redirect back to login page if there is an error
    }

    @GetMapping("employee-OTP-page")
    public String employeeOtpPage() {
        return "EmployeeOTPPage"; // Display OTP entry page
    }

    @GetMapping("employeeLogin")
    public String employeeLoginPage() {
        return "EmployeeLoginPage"; // Display login page
    }


    @PostMapping("otpVerification")
    public String otpVerification(@RequestParam("otp") String enteredOtp,
                                  @RequestParam("email") String email,
                                  HttpSession session,

                                  Model model) {

        log.info("otpVerification method running in EmployeeController..");

        // Retrieve email from session
        String emailId = (String) session.getAttribute("email");

        // Find employee by email
        EmployeeDTO employeeDTO = employeeService.findByEmail(emailId);

        if (employeeDTO == null) {
            // If the employee doesn't exist, return an error
            model.addAttribute("emailNotFound", "Email does not exist.");
            return "EmployeeOTPPage";
        }
        String employeeName= (String) session.getAttribute("employeeName");
        model.addAttribute("employeeName",employeeName);
        model.addAttribute("welcomeMsg","Welcome To Your Profile");



        try {
            // Trim the OTP string before parsing and compare with stored OTP
            long enteredOtpValue = Long.parseLong(enteredOtp.trim());

            if (employeeDTO.getOtp().equals(enteredOtpValue)) {
                //to display employee name in jsp

                // OTPs match, handle success
                return "EmployeeProfilePage";
            } else {
                // OTPs don't match, handle failure
                model.addAttribute("invalidOTP", "OTP is not matching");
                return "EmployeeOTPPage";
            }
        } catch (NumberFormatException e) {
            // Handle the exception if OTP is not a valid number
            model.addAttribute("invalidOTP", "Invalid OTP format");
            return "EmployeeOTPPage";
        }
    }


    @PostMapping("resendOtp")
    @ResponseBody
    public ResponseEntity<String> resendOtp(@RequestParam("email") String emailId, Model model,HttpSession session) {
        log.info("resendOtp method running in EmployeeController..");

        // Check if the email exists in the database
        EmployeeDTO employeeDTO = employeeService.findByEmail(emailId);
        if (employeeDTO == null) {
            // If email does not exist, return an error message
            log.error("Email not found in the database: {}", emailId);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email does not exist.");
        }

        // Generate a new OTP
        String otp = emailOTPGenerator.generateOtp();
        log.info("New OTP generated: {}", otp);

        // Save the new OTP in employeeDTO and update the database
        employeeDTO.setOtp(Long.parseLong(otp));
        boolean isSaved = employeeService.saveEmployeeDetails(employeeDTO);

        if (isSaved) {

            //to display employee name in jsp
            String employeeName= (String) session.getAttribute("employeeName");
            model.addAttribute("employeeName",employeeName);
            model.addAttribute("welcomeMsg","Welcome To Your Profile");


            // Send the new OTP to the user's email
            mailSend.resendOtpToEmail(employeeDTO.getEmail(), otp);
            log.info("New OTP sent to email: {}", emailId);
            //model.addAttribute("newOTP","New OTP sent to your email.");

            return ResponseEntity.ok("New OTP sent to your email.");
        } else {
            log.error("Failed to save the new OTP for email: {}", emailId);
            // model.addAttribute("newOTP","Failed to generate OTP, please try again");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(".Failed to generate OTP, please try again");
        }
    }


    @GetMapping("editEmployeeDetails")
    public String updateEmployeeDetails(HttpSession session)
    {
          String emailId= (String) session.getAttribute("email");



        return"";
    }



}