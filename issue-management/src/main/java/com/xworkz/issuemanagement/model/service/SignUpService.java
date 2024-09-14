package com.xworkz.issuemanagement.model.service;


import com.xworkz.issuemanagement.dto.SignUpDTO;
import org.springframework.ui.Model;

import java.time.LocalDateTime;

public interface SignUpService {

    public boolean saveAndValidate(SignUpDTO signUpDTO);

    public void  setAudit(SignUpDTO signUpDTO, String createdBy, LocalDateTime createdOn,String updatedBy,LocalDateTime updatedOn,boolean isActive);


//    //to call generate password in service ..so we have to write 1 method
//    SignUpDTO findByEmailAndPassword(String email, String password);
//
//
//    public String generateRandomPassword();








}


