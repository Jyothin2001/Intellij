package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.model.repo.ResetPasswordRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class ResetPasswordServiceImpl implements ResetPasswordService {

    @Autowired
    private MailService mailService;//i have written Simple mail sending logic (to,subject,body) in MailService make use of it.

    @Autowired
    private ResetPasswordRepo resetPasswordRepo;


    @Autowired
   private JavaMailSender javaMailSender;

    @Override
    public boolean resetPassword(String email, String oldPassword, String newPassword, String confirmPassword) {

        if(!resetPasswordRepo.emailExists(email))
        {
            return false;
        }

        if(!resetPasswordRepo.verifyOldPassword(email,oldPassword))
        {
            return false;
        }
        if(!newPassword.equals(confirmPassword))
        {
            return false;
        }
        resetPasswordRepo.updatePassword(email,newPassword);

        mailService.sendSimpleEmail(email,"Password Reset Successful","Your password has been successfully reset..Your new password is : "+newPassword);


        return true;
    }

  }
