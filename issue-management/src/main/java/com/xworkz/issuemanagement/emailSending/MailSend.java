package com.xworkz.issuemanagement.emailSending;

import com.xworkz.issuemanagement.dto.RegDeptAdminDTO;
import com.xworkz.issuemanagement.dto.SignUpDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MailSend {

    @Autowired
    private JavaMailSender javaMailSender;

    public MailSend()
    {
        log.info("No parameters in MailSend Constructor:..");
    }

    public void sendPassword(SignUpDTO signUpDTO)
    {
     SimpleMailMessage message  = new SimpleMailMessage();
     message.setTo(signUpDTO.getEmail());
     message.setSubject("LogIn Password");
     message.setText("Dear "+ signUpDTO.getFirstName()+" "+signUpDTO.getLastName()+ "," + "\n\n" + "You have been successfully Signed Up.\n"
     +"Please Log In through this password: "+signUpDTO.getPassword()+"\n\n"+
        "Thanks and Regards,\n" + " " + "X-Workz Project Team");

        javaMailSender.send(message);
    }
    public void forgotPassword(SignUpDTO signUpDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(signUpDTO.getEmail());
        message.setSubject("Forgot password");
        message.setText("Dear " + signUpDTO.getFirstName() + " " + signUpDTO.getLastName() + ","+  "\n\n" + "A new password has been sent to your email. \n" +
                "Please Sign in through new password: " + signUpDTO.getPassword() + "\n\n" +
                "Thanks and Regards,\n" + " " +
                "X-workz Project Team");
        javaMailSender.send(message);
    }

    public void sendChangePassword(SignUpDTO signUpDTO,String newPassword) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(signUpDTO.getEmail());
        message.setSubject("Change password");
        message.setText("Dear " + signUpDTO.getFirstName() + " " + signUpDTO.getLastName() +"," + "\n\n"   + "A Change Password has been sent to your email. \n" +
                "Please Sign in through Change password: " + newPassword+ "\n\n" +
                "Thanks and Regards,\n" + " " +
                "X-workz Project Team");
        javaMailSender.send(message);
    }

//****************SubAdmin*****************
    public void sendDeptAdminPassword(RegDeptAdminDTO regDeptAdminDTO)
    {
        SimpleMailMessage message  = new SimpleMailMessage();
        message.setTo(regDeptAdminDTO.getEmail());
        message.setSubject("LogIn Password");
        message.setText("Dear "+ regDeptAdminDTO.getAdminName()+ "," + "\n\n" + "You have been successfully Signed Up.\n"
                +"Please Log In through this password: "+regDeptAdminDTO.getPassword()+"\n\n"+
                "Thanks and Regards,\n" + " " + "X-Workz Project Team");

        javaMailSender.send(message);
    }

    public void subAdminForgotPassword(RegDeptAdminDTO regDeptAdminDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(regDeptAdminDTO.getEmail());
        message.setSubject("SignIn password");
        message.setText("Dear " + regDeptAdminDTO.getAdminName() + " " + ",  A new password has been sent to your email. ,\n\n" +
                "Please Login in through new password: " + regDeptAdminDTO.getPassword() + "\n" +
                "Thanks and Regards,\n" + " " +
                "X-Workz Project Team");
        javaMailSender.send(message);
    }
    public void sendChangePasswordSubAdmin(RegDeptAdminDTO regDeptAdminDTO,String newPassword) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(regDeptAdminDTO.getEmail());
        message.setSubject("Change password");
        message.setText("Dear " + regDeptAdminDTO.getAdminName() +"," + "\n\n"   + "A Change Password has been sent to your email. \n" +
                "Please Sign in through Change password: " + newPassword+ "\n\n" +
                "Thanks and Regards,\n" + " " +
                "X-workz Project Team");
        javaMailSender.send(message);
    }

}
