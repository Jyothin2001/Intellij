package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.RegDeptAdminDTO;
import com.xworkz.issuemanagement.dto.SignUpDTO;
import com.xworkz.issuemanagement.emailSending.MailSend;
import com.xworkz.issuemanagement.model.repo.ForgotPasswordRepo;
import com.xworkz.issuemanagement.model.repo.RegDeptAdminRepo;
import com.xworkz.issuemanagement.util.PasswordGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ForgotPasswordServiceImpli implements ForgotPasswordService{

    @Autowired
    private ForgotPasswordRepo forgotPasswordRepo;

    @Autowired
    private RegDeptAdminRepo regDeptAdminRepo;


    @Autowired
    private SignInService signInService;

    @Autowired
    private RegDeptAdminServiceImpli regDeptAdminServiceImpli;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private MailSend mailSend;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @Override
    public boolean forgotPassword(String email)
    {
        log.info("forgotPassword method running in forgotPasswordServiceImpli");

        SignUpDTO user=forgotPasswordRepo.findByEmail(email);

        if(user!=null)
        {
            //new password generate and update in db
           String newPassword= PasswordGenerator.generatePassword();

           forgotPasswordRepo.updatePassword(email,passwordEncoder.encode(newPassword));
           user.setPassword(newPassword);//user generated Password
           mailSend.forgotPassword(user);//mail

           //Reset failed attempts
             signInService.resetFailedAttempts(email);
             signInService.unLockAccount(email);

           return true;

        }
        return false;

    }

    @Override
    public boolean forgotPasswordBySubAdmin(String email) {
        //just reusing regDeptAdminRepo.getEmail()
        RegDeptAdminDTO user= regDeptAdminRepo.getEmail(email);
        if(user!=null)
        {
            log.info("regDeptAdminRepo email data in ForgotPasswordServiceImpli:{}  ",user);

            String newPassword=PasswordGenerator.generatePassword();

            forgotPasswordRepo.updateSubAdminPassword(email,passwordEncoder.encode(newPassword));//db

             user.setPassword(newPassword);   //user generated Password
            mailSend.subAdminForgotPassword(user);//mail

            //Reset failed attempts
            regDeptAdminServiceImpli.resetFailedAttempts(email);
            regDeptAdminServiceImpli.unlockAccount(email);
            return true;
        }


        return false;
    }
}
