package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.SignUpDTO;
import com.xworkz.issuemanagement.emailSending.MailSend;
import com.xworkz.issuemanagement.model.repo.ForgotPasswordRepo;
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
    private SignInService signInService;

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
           user.setPassword(newPassword);//????
           mailSend.forgotPassword(user);

           //Reset failed attempts
             signInService.resetFailedAttempts(email);
             signInService.unLockAccount(email);

//             //send reset password to mail
//            SimpleMailMessage message=new SimpleMailMessage();
//            message.setTo(email);
//            message.setSubject("Password Reset");
//            message.setText("Your new password is:"+newPassword);
//           javaMailSender.send(message);

           return true;

        }
        return false;

    }
}
