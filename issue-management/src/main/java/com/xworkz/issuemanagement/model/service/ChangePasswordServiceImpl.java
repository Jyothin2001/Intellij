package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.RegDeptAdminDTO;
import com.xworkz.issuemanagement.dto.SignUpDTO;
import com.xworkz.issuemanagement.emailSending.MailSend;
import com.xworkz.issuemanagement.model.repo.ChangePasswordRepo;
import com.xworkz.issuemanagement.model.repo.RegDeptAdminRepo;
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



    @Autowired
    private RegDeptAdminRepo regDeptAdminRepo;


    @Override
    public String changePassword(String email, String oldPassword, String newPassword, String confirmPassword) {
        log.info("Trying to change password for email: {}", email);

        // Step 1: Check if newPassword matches confirmPassword
        if (!newPassword.equals(confirmPassword)) {
            log.info("New password and confirm password do not match.");
            return "password_mismatch"; // Indicate password mismatch
        }

        // Step 2: Retrieve SignUpDTO based on email
        SignUpDTO signUpDTO = this.signInRepo.findByEmail(email);
        if (signUpDTO == null) {
            log.info("User with email {} not found.", email);
            return "user_not_found"; // User not found
        }

        String storedPassword = signUpDTO.getPassword();
        log.info("Stored password: {}", storedPassword);

        // Step 3: Verify oldPassword matches the stored password
        if (!passwordEncoder.matches(oldPassword, storedPassword)) {
            log.info("Old password verification failed for email: {}", email);
            return "old_password_incorrect"; // Old password doesn't match
        }

        // Step 4: Try sending the email with the new password first
        try {
            String emailStatus = mailSend.sendChangePassword(signUpDTO, newPassword);

            // Handle different email statuses
            if ("network_error".equals(emailStatus)) {
                log.info("Network issue occurred while sending the change password email.");
                return "network_error"; // Return network error status
            } else if ("send_error".equals(emailStatus)) {
                log.info("Error occurred while sending the change password email.");
                return "send_error"; // Return email send failure status
            }
        } catch (Exception e) {
            log.error("Unexpected exception occurred while sending change password email: ", e);
            return "send_error"; // Generic email error
        }

        // Step 5: If email sent successfully, encode and update the new password
        signUpDTO.setPassword(passwordEncoder.encode(newPassword));

        // Step 6: Save the updated password in the repository (database)
        boolean saveEmail = resetPasswordRepo.updatePassword(email, signUpDTO.getPassword());
        if (saveEmail) {
            log.info("Password updated successfully for email: {}", email);
            return "success"; // Password successfully updated
        }

        return "update_failed"; // Password update failed in DB
    }

//    @Override
//    public String changePassword(String email, String oldPassword, String newPassword, String confirmPassword) {
//        log.info("Trying to change password for email : " + email);
//
//        // Step 1: Check if newPassword matches confirmPassword
//        if (!newPassword.equals(confirmPassword)) {
//            log.info("New password and confirm password do not match.");
//            return "password_mismatch"; // Indicate password mismatch
//        }
//
//        // Step 2: Retrieve SignUpDTO based on email
//        SignUpDTO signUpDTO = this.signInRepo.findByEmail(email);
//        if (signUpDTO == null) {
//            log.info("User with email {} not found.", email);
//            return "user_not_found"; // User not found
//        }
//
//        String storedPassword = signUpDTO.getPassword();
//        log.info("Stored password:{}", storedPassword);
//
//        // Step 3: Verify oldPassword matches the stored password
//        if (!passwordEncoder.matches(oldPassword, storedPassword)) {
//            log.info("Old password verification failed for email: {}", email);
//            return "old_password_incorrect"; // Old password doesn't match
//        }
//
//        // Step 4: Encode and update the new password in SignUpDTO
//        signUpDTO.setPassword(passwordEncoder.encode(newPassword));
//
//        // Step 5: Save the updated password in the repository (database)
//        boolean saveEmail = resetPasswordRepo.updatePassword(email, signUpDTO.getPassword());
//        if (saveEmail) {
//            log.info("Password updated successfully for email: {}", email);
//
//            // After update, send email notification to the user
//            try {
//              String emailStatus=  mailSend.sendChangePassword(signUpDTO, newPassword);
//              if( "network_error".equals(emailStatus))
//              {
//                  return "network_error";
//              }
//                  else if ("send_error".equals(emailStatus))
//                  {
//                  return "send_error"; // return email send failure status
//
//              }
//            } catch (Exception e) {
//                log.error("Unexpected exception occurred while sending change password email: ", e);
//                return "send_error"; // Generic email error
//            }
//            return "success"; // Password successfully updated
//        }
//
//        return "update_failed"; // Password update failed in DB
//    }

//    @Override
//    public boolean changePassword(String email, String oldPassword, String newPassword, String confirmPassword) {
//       log.info("Trying to change password for email : " + email);
//
//        // Step 1: Check if newPassword matches confirmPassword
//        if(!newPassword.equals(confirmPassword)) {
//            log.info("New password and confirm password do not match.");
//            return false;
//        }
//        // Step 2: Retrieve SignUpDto based on email
//        SignUpDTO signUpDTO=this.signInRepo.findByEmail(email);
//        if(signUpDTO==null)
//        {
//            log.info("User with email {} not found.", email);
//            return false; // User not found
//        }
//       String storedPassword= signUpDTO.getPassword();
//        log.info("Stored password:{}", storedPassword);
//
//        // Step 3: Verify oldPassword matches the stored password
//        if(!passwordEncoder.matches(oldPassword,storedPassword))
//        {
//            log.info("Old password verification failed for email:{} " , email);
//            return false; // Old password doesn't match
//        }
//
//        // Step 4: Encode and update the new password in SignUpDTO
//        signUpDTO.setPassword(passwordEncoder.encode(newPassword));
//
//        // Step 5: Save the updated password in the repository (database)
//        boolean saveEmail=resetPasswordRepo.updatePassword(email,signUpDTO.getPassword());
//        if(saveEmail)
//        {
//            log.info("Password updated successfully for email:{} " , email);
//               //after update the encoded password in db then send to user email
//            try {
//                mailSend.sendChangePassword(signUpDTO, newPassword);
//            }
//            catch (MailException e)
//            {
//                // Handle MailException (e.g., network issue, invalid email address)
//                log.error("MailException occurred while sending change password email: ", e);
//                return false;
//            }
//            catch (Exception e) {
//                // Handle any other unexpected exception
//                log.error("Unexpected exception occurred while sending change password email: ", e);
//                return false; // Return a generic error status for other exceptions
//            }
//
//            return true;// Password successfully updated
//
//        }
//
//        return false; // Password update failed
//    }

    @Override
    public String subAdminChangePassword(String email, String oldPassword, String newPassword, String confirmPassword) {
        log.info("Trying to change password for email: {}", email);

        // Step 1: Check if newPassword matches confirmPassword
        if (!newPassword.equals(confirmPassword)) {
            log.info("New password and confirm password do not match.");
            return "password_mismatch"; // Indicate password mismatch
        }

        // Step 2: Retrieve RegDeptAdminDTO based on email
        RegDeptAdminDTO regDeptAdminDTO = this.regDeptAdminRepo.getEmail(email);
        if (regDeptAdminDTO == null) {
            log.info("User with email {} not found.", email);
            return "user_not_found"; // User not found
        }

        String storedPassword = regDeptAdminDTO.getPassword();
        log.info("Stored password: {}", storedPassword);

        // Step 3: Verify oldPassword matches the stored password
        if (!passwordEncoder.matches(oldPassword, storedPassword)) {
            log.info("Old password verification failed for email: {}", email);
            return "old_password_incorrect"; // Old password doesn't match
        }

        // Step 4: Try sending the email with the new password first
        try {
            String emailStatus = mailSend.sendChangePasswordSubAdmin(regDeptAdminDTO, newPassword);

            // Handle different email statuses
            if ("network_error".equals(emailStatus)) {
                log.info("Network issue occurred while sending the change password email.");
                return "network_error"; // Return network error status
            } else if ("send_error".equals(emailStatus)) {
                log.info("Error occurred while sending the change password email.");
                return "send_error"; // Return email send failure status
            }
        } catch (Exception e) {
            log.error("Unexpected exception occurred while sending change password email: ", e);
            return "send_error"; // Generic email error
        }

        // Step 5: If email sent successfully, encode and update the new password
        regDeptAdminDTO.setPassword(passwordEncoder.encode(newPassword));

        // Step 6: Save the updated password in the repository (database)
        boolean saveEmail = resetPasswordRepo.updatePasswordSubAdmin(email, regDeptAdminDTO.getPassword());
        if (saveEmail) {
            log.info("Password updated successfully for email: {}", email);
            return "success"; // Password successfully updated
        }

        return "update_failed"; // Password update failed in DB
    }


//    @Override
//    public boolean subAdminChangePassword(String email, String oldPassword, String newPassword, String confirmPassword) {
//        log.info("Trying to change password for email : " + email);
//
//        // Step 1: Check if newPassword matches confirmPassword
//        if(!newPassword.equals(confirmPassword)) {
//            log.info("New password and confirm password do not match.");
//            return false;
//        }
//        // Step 2: Retrieve SignUpDto based on email
//        RegDeptAdminDTO regDeptAdminDTO=this.regDeptAdminRepo.getEmail(email);
//        if(regDeptAdminDTO==null)
//        {
//            log.info("User with email {} not found.", email);
//            return false; // User not found
//        }
//        String storedPassword= regDeptAdminDTO.getPassword();
//        log.info("Stored password:{}", storedPassword);
//
//        // Step 3: Verify oldPassword matches the stored password
//        if(!passwordEncoder.matches(oldPassword,storedPassword))
//        {
//            log.info("Old password verification failed for email:{} " , email);
//            return false; // Old password doesn't match
//        }
//
//        // Step 4: Encode and update the new password in RegDeptAdminDTO
//        regDeptAdminDTO.setPassword(passwordEncoder.encode(newPassword));
//
//        // Step 5: Save the updated password in the repository (database)
//        boolean saveEmail=resetPasswordRepo.updatePasswordSubAdmin(email,regDeptAdminDTO.getPassword());
//        if(saveEmail)
//        {
//            log.info("Password updated successfully for email:{} " , email);
//            //after update the encoded password in db then send to user email
//            try {
//                mailSend.sendChangePasswordSubAdmin(regDeptAdminDTO, newPassword);
//            }
//            catch (MailException e)
//            {
//                // Handle exception if email sending fails (log it or take appropriate action)
//                e.printStackTrace();
//                return false;
//            }
//            return true;// Password successfully updated
//
//        }
//
//        return false;
//    }












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
