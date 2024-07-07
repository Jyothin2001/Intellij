package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.SignUpDTO;

public interface MailService
{

    //to send password to mail
    void sendSimpleEmail(String toEmail,String subject,String body);


}
