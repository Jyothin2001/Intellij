package com.xworkz.issuemanagement.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpli implements MailService
{
    @Autowired
    private JavaMailSender javaMailSender;

    //To send password to mail
    @Override
    public void sendSimpleEmail(String toEmail, String subject, String body)
    {
        SimpleMailMessage message=new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);
        message.setFrom("jyothin.xworkz@gmail.com");

        javaMailSender.send(message);

    }



}
