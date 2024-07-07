package com.xworkz.issuemanagement.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;


@Configuration
// Sending password to email
public class MailConfiguration
{
    public MailConfiguration()
    {
        System.out.println("MailConfiguration constructor:");
    }
    @Bean
    public JavaMailSender javaMailSender()
    {
        JavaMailSenderImpl mailSender=new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com.");
        mailSender.setPort(587);
        mailSender.setUsername("jyothin.xworkz@gmail.com");
        mailSender.setPassword("xyfm bxcy dusq oupz");

        Properties props= mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

}
