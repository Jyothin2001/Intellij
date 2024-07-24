package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.SignUpDTO;

public interface ViewPageService
{
    //user Details:
    //calling findByEmail from ForgotPasswordRepo
    SignUpDTO getUserByEmail(String email);

    //Not calling httpSession in controller??
    String getSignedInUserEmail();
}
