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

    public MailSend() {
        log.info("No parameters in MailSend Constructor:..");
    }

    // User Sign-up Confirmation Email
    public void sendPassword(SignUpDTO signUpDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(signUpDTO.getEmail());
        message.setSubject("Welcome to X-Workz - Your Login Details");
        message.setText("Dear " + signUpDTO.getFirstName() + " " + signUpDTO.getLastName() + ",\n\n" +
                "Congratulations! Your account has been successfully created.\n" +
                "Please use the following password to log in to your account:\n\n" +
                "Password: " + signUpDTO.getPassword() + "\n\n" +
                "For your security, we recommend changing your password after your first login.\n\n" +
                "Best Regards,\n" +
                "X-Workz Project Team");
        javaMailSender.send(message);
    }

    // User Forgot Password Email
    public void forgotPassword(SignUpDTO signUpDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(signUpDTO.getEmail());
        message.setSubject("Password Reset Request");
        message.setText("Dear " + signUpDTO.getFirstName() + " " + signUpDTO.getLastName() + ",\n\n" +
                "We received a request to reset your password. Please use the new password provided below to sign in:\n\n" +
                "New Password: " + signUpDTO.getPassword() + "\n\n" +
                "For your security, we recommend changing your password after logging in.\n\n" +
                "Best Regards,\n" +
                "X-Workz Project Team");
        javaMailSender.send(message);
    }

    // User Change Password Email
    public void sendChangePassword(SignUpDTO signUpDTO, String newPassword) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(signUpDTO.getEmail());
        message.setSubject("Your Password Has Been Updated");
        message.setText("Dear " + signUpDTO.getFirstName() + " " + signUpDTO.getLastName() + ",\n\n" +
                "Your password has been successfully changed. Please use the new password provided below to sign in:\n\n" +
                "New Password: " + newPassword + "\n\n" +
                "If you did not request this change, please contact our support team immediately.\n\n" +
                "Best Regards,\n" +
                "X-Workz Project Team");
        javaMailSender.send(message);
    }

    // Department Admin Sign-up Confirmation Email
    public void sendDeptAdminPassword(RegDeptAdminDTO regDeptAdminDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(regDeptAdminDTO.getEmail());
        message.setSubject("Welcome to X-Workz - Department Admin Login Details");
        message.setText("Dear " + regDeptAdminDTO.getAdminName() + ",\n\n" +
                "Congratulations! Your department admin account has been successfully created.\n" +
                "Please use the following password to log in to your account:\n\n" +
                "Password: " + regDeptAdminDTO.getPassword() + "\n\n" +
                "For your security, we recommend changing your password after your first login.\n\n" +
                "Best Regards,\n" +
                "X-Workz Project Team");
        javaMailSender.send(message);
    }

    // Department Admin Forgot Password Email
    public void subAdminForgotPassword(RegDeptAdminDTO regDeptAdminDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(regDeptAdminDTO.getEmail());
        message.setSubject("Password Reset Notification");
        message.setText("Dear " + regDeptAdminDTO.getAdminName() + ",\n\n" +
                "Your password has been successfully reset. Please use the new password provided below to log in to your account:\n\n" +
                "New Password: " + regDeptAdminDTO.getPassword() + "\n\n" +
                "For your security, we recommend changing your password after logging in.\n\n" +
                "Best Regards,\n" +
                "X-Workz Project Team");
        javaMailSender.send(message);
    }

    // Department Admin Change Password Email
    public void sendChangePasswordSubAdmin(RegDeptAdminDTO regDeptAdminDTO, String newPassword) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(regDeptAdminDTO.getEmail());
        message.setSubject("Your Department Admin Password Has Been Updated");
        message.setText("Dear " + regDeptAdminDTO.getAdminName() + ",\n\n" +
                "Your password has been successfully changed. Please use the new password provided below to log in:\n\n" +
                "New Password: " + newPassword + "\n\n" +
                "If you did not request this change, please contact our support team immediately.\n\n" +
                "Best Regards,\n" +
                "X-Workz Project Team");
        javaMailSender.send(message);
    }


    // Send OTP to email
    public void sendOtpToEmail(String email, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("One-Time Password (OTP) Verification");
        message.setText("Dear User,\n\n" +
                "Your One-Time Password (OTP) for verification is: " + otp + ".\n\n" +
                "Please use this OTP to complete the verification process. If you did not request this OTP, please disregard this message.\n\n" +
                "Best regards,\n" +
                "X-Workz Team");
        javaMailSender.send(message);
    }
    // Resend OTP to email
    public void resendOtpToEmail(String email, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Resend One-Time Password (OTP) Verification");
        message.setText("Dear User,\n\n" +
                "We have received your request to resend the One-Time Password (OTP) for verification. " +
                "Your OTP is: " + otp + ".\n\n" +
                "Please use this OTP to complete the verification process. If you did not request this OTP, please ignore this message.\n\n" +
                "Best regards,\n" +
                "X-Workz Team");
        javaMailSender.send(message);
    }

}
