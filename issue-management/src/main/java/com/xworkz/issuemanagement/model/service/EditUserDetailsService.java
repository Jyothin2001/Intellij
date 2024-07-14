package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.SignUpDTO;

import java.time.LocalDateTime;

public interface EditUserDetailsService
{
     SignUpDTO getUserDetails(String email);

     SignUpDTO updateUserDetails(SignUpDTO signUpDTO);

     String getSignedInUserEmail();

     void setAudit(SignUpDTO signUpDTO, String updatedBy, LocalDateTime updatedOn);
}
