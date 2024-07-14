package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.SignUpDTO;
import com.xworkz.issuemanagement.model.repo.ForgotPasswordRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@Slf4j
public class ViewPageServiceImpli implements ViewPageService
{
    /*1.To display user details write repository get it from db and call in service then controller.
    *2.I already wrote  SignUPDTO FindByEmail(String email) method so make use of it in service  */

    @Autowired
    private ForgotPasswordRepo forgotPasswordRepo;

    @Autowired
    private HttpSession httpSession;

    @Override
    public SignUpDTO getUserByEmail(String email)
    {
        //this method return SignUpDTO
        log.info("calling findByEmail from ForgotPasswordRepo(it contains) in userPageServiceImpli");

        SignUpDTO signUpDTO=forgotPasswordRepo.findByEmail(email);
        log.info("email in service:{}",signUpDTO);
         return signUpDTO;

    }

    @Override
    public String getSignedInUserEmail()
    {
        //??
        log.info("httpsession in userPageServiceImpli:");
        httpSession.getAttribute("SignedInUserEmail");
        return "String";
    }
}
