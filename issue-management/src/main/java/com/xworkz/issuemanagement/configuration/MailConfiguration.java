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

        /*1.Properties is a subclass of Hashtable that holds a set of key-value pairs.

        mail.transport.protocol: Specifies the protocol to be used, which is SMTP in this case.
        mail.smtp.auth: Enables SMTP authentication.
        mail.smtp.starttls.enable: Enables STARTTLS, allowing the connection to be upgraded to a secure connection using SSL/TLS.
        mail.debug: Enables debug mode to log detailed information for troubleshooting purposes.

        Summary:
        The javaMailSender bean configuration sets up the email sending capabilities by configuring the connection to the Gmail SMTP server with authentication and TLS support.
        The sendSimpleEmail method leverages this configuration to create and send an email.
        By using Spring's dependency injection, the JavaMailSender bean is made available wherever needed,
        ensuring the email sending process is smooth and well-integrated within your Spring application.

*/

        Properties props= mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");

        props.put("mail.smtp.auth", "true");

        props.put("mail.smtp.starttls.enable", "true");

        props.put("mail.debug", "true");

        return mailSender;
    }

}
