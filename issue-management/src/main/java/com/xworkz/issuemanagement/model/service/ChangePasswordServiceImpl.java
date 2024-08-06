package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.SignUpDTO;
import com.xworkz.issuemanagement.emailSending.MailSend;
import com.xworkz.issuemanagement.model.repo.ChangePasswordRepo;
import com.xworkz.issuemanagement.model.repo.SignInRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ChangePasswordServiceImpl implements ChangePasswordService {

    @Autowired
    private MailService mailService;//i have written Simple mail sending logic (to,subject,body) in MailService make use of it.

    @Autowired
    private ChangePasswordRepo resetPasswordRepo;

    @Autowired
    private SignInRepo signInRepo;

    @Autowired
   private JavaMailSender javaMailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MailSend mailSend;

    @Override
    public boolean changePassword(String email, String oldPassword, String newPassword, String confirmPassword) {
       log.info("Trying to change password for email : " + email);

        // Step 1: Check if newPassword matches confirmPassword
        if(!newPassword.equals(confirmPassword)) {
            log.info("New password and confirm password do not match.");
            return false;
        }
        // Step 2: Retrieve SignUpDto based on email
        SignUpDTO signUpDTO=this.signInRepo.findByEmail(email);
        if(signUpDTO==null)
        {
            log.info("User with email {} not found.", email);
            return false; // User not found
        }
       String storedPassword= signUpDTO.getPassword();
        log.info("Stored password:{}", storedPassword);

        // Step 3: Verify oldPassword matches the stored password
        if(!passwordEncoder.matches(oldPassword,storedPassword))
        {
            log.info("Old password verification failed for email:{} " , email);
            return false; // Old password doesn't match
        }

        // Step 4: Encode and update the new password in SignUpDTO
        signUpDTO.setPassword(passwordEncoder.encode(newPassword));

        // Step 5: Save the updated password in the repository (database)
        boolean saveEmail=resetPasswordRepo.updatePassword(email,signUpDTO.getPassword());
        if(saveEmail)
        {
            log.info("Password updated successfully for email:{} " , email);
               //after update the encoded password in db then send to user email
            try {
                mailSend.sendChangePassword(signUpDTO, newPassword);
            }
            catch (MailException e)
            {
                // Handle exception if email sending fails (log it or take appropriate action)
                e.printStackTrace();
                return false;
            }
            return true;// Password successfully updated

        }

        return false; // Password update failed
    }


  }
//        if(!resetPasswordRepo.emailExists(email))
//        {
//            return false;
//        }
//
//        if(!resetPasswordRepo.verifyOldPassword(email,oldPassword))
//        {
//            return false;
//        }
//        if(!newPassword.equals(confirmPassword))
//        {
//            return false;
//        }
//        resetPasswordRepo.updatePassword(email,newPassword);
//
//        mailService.sendSimpleEmail(email,"Password Reset Successful","Your password has been successfully reset..Your new password is : "+newPassword);
//
//
//        return true;
