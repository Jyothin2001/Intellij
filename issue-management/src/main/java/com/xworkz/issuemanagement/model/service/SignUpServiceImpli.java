package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.SignUpDTO;
import com.xworkz.issuemanagement.model.repo.SignUpRepo;
import com.xworkz.issuemanagement.util.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class SignUpServiceImpli implements SignUpService
{
    @Autowired
    private SignUpRepo signUpRepo;

    public SignUpServiceImpli()
    {
        System.out.println("SignUpServiceImpli constructor");
    }

    //To store Basic/form user details in db
    @Override
    public boolean saveAndValidate(SignUpDTO signUpDTO)
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
        signUpDTO.setPassword(generatedPassword);

        boolean data=this.signUpRepo.saveAndValidate(signUpDTO);
        if(data)
        {
            System.out.println("repo save() in service successfully: " + data);
            return data;
            }
        else{
                System.out.println("repo save() in service not successfully: " + data);
            }
        return true;
    }



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



















