package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.SignUpDTO;
import com.xworkz.issuemanagement.emailSending.MailSend;
import com.xworkz.issuemanagement.model.repo.SignUpRepo;
import com.xworkz.issuemanagement.util.PasswordGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class SignUpServiceImpli implements SignUpService
{
    @Autowired
    private SignUpRepo signUpRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MailSend mailSend;

    public SignUpServiceImpli()
    {
        log.info("SignUpServiceImply constructor");
    }

    //To store Basic/form user details in db
    @Override
    public String saveAndValidate(SignUpDTO signUpDTO)
    {
        //Set name,time,active data to variables
        String createdBy = signUpDTO.getFirstName(); // or get the current user
        LocalDateTime createdOn = LocalDateTime.now();
        String updatedBy = signUpDTO.getFirstName(); // or get the current user
        LocalDateTime updatedOn = LocalDateTime.now();
        boolean isActive = true;

       //calling audit() in saveAndValidate()
        setAudit(signUpDTO, createdBy, createdOn, updatedBy, updatedOn,isActive);

        //calling Random password generator method
        String generatedPassword= PasswordGenerator.generatePassword();

        signUpDTO.setPassword(generatedPassword);//for user
        String emailStatus = mailSend.sendPassword(signUpDTO);

        if ("network_error".equals(emailStatus)) {
            return "network_error"; // return network error status
        }

        //do encode for random generator
        signUpDTO.setPassword(passwordEncoder.encode(generatedPassword));//for db

        //image set default profile
        signUpDTO.setImageName("ProfileIcon.png");

        boolean data=this.signUpRepo.saveAndValidate(signUpDTO);
        if(data)
        {
            log.info("repo save() in service successfully: " + data);
            return "success";
        }
        else{
                log.info("repo save() in service not successfully: " + data);
            }
        return "success";
    }

//    @Override
//    public boolean saveAndValidate(SignUpDTO signUpDTO) {
//        // Set audit-related data
//        String createdBy = signUpDTO.getFirstName(); // Get current user or use first name
//        LocalDateTime createdOn = LocalDateTime.now();
//        String updatedBy = signUpDTO.getFirstName(); // Get current user or use first name
//        LocalDateTime updatedOn = LocalDateTime.now();
//        boolean isActive = true;
//
//        // Call audit method
//        setAudit(signUpDTO, createdBy, createdOn, updatedBy, updatedOn, isActive);
//
//        // Generate random password and encode it
//        String generatedPassword = PasswordGenerator.generatePassword();
//        signUpDTO.setPassword(passwordEncoder.encode(generatedPassword));
//
//        // Set default profile image
//        signUpDTO.setImageName("ProfileIcon.png");
//
//        // Save the user data
//        boolean isSaved = this.signUpRepo.saveAndValidate(signUpDTO);
//        if (isSaved) {
//            log.info("User data saved successfully: {}", isSaved);
//            signUpDTO.setPassword(generatedPassword); // Keep raw password for sending email
//
//            // Send confirmation email and handle any email sending issues
//            String emailStatusMessage = mailSend.sendPassword(signUpDTO);
//
//            // Log the email status and return it accordingly
//            if (emailStatusMessage.equals("Email sent successfully.")) {
//                log.info("Confirmation email sent successfully to: {}", signUpDTO.getEmail());
//            } else {
//                log.error("Failed to send confirmation email: {}", emailStatusMessage);
//            }
//
//            return isSaved;
//        } else {
//            log.error("Failed to save user data: {}", isSaved);
//        }
//        return false;
//    }


    //To store the details of sign up user like when,who,isActive
    @Override
    public void setAudit(SignUpDTO signUpDTO, String createdBy, LocalDateTime createdOn, String updatedBy, LocalDateTime updatedOn, boolean isActive)
    {

        System.out.println("setAudit() is running in SignInServiceImpli..");
        signUpDTO.setCreatedBy(createdBy);
        signUpDTO.setCreatedOn(createdOn);
        signUpDTO.setUpdatedBy(updatedBy);
        signUpDTO.setUpdatedOn(updatedOn);
        signUpDTO.setActive(isActive);
    }

}



















