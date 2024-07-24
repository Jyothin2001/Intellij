package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.SignUpDTO;
import com.xworkz.issuemanagement.model.repo.EditUserDetailsRepo;
import com.xworkz.issuemanagement.model.repo.SignInRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Service
@Slf4j
public class EditUserDetailsServiceImpli implements EditUserDetailsService
{


    @Autowired
    private EditUserDetailsRepo editUserDetailsRepo;


    @Autowired
    private HttpSession httpSession;


    @Override
    public SignUpDTO getUserDetails(String email)
    {
        SignUpDTO signUpDTO=editUserDetailsRepo.findByEmail(email);

        return signUpDTO;
    }

    @Override
    public SignUpDTO updateUserDetails(SignUpDTO signUpDTO) {
        log.info("updateUserDetails method running in EditUserProfileServiceImpl..");

        //set audit fields

       String updatedBy= signUpDTO.getFirstName();
      LocalDateTime updatedOn= LocalDateTime.now();

      setAudit(signUpDTO,updatedBy,updatedOn);

      editUserDetailsRepo.updateUserDetails(signUpDTO);

        return signUpDTO;
    }

    @Override
    public String getSignedInUserEmail() {

       return (String) httpSession.getAttribute("signedInUserEmail");


    }

    @Override
    public void setAudit(SignUpDTO signUpDTO, String updatedBy, LocalDateTime updatedOn) {

        log.info("setAudit() in service:");
        signUpDTO.setUpdatedBy(updatedBy);
        signUpDTO.setUpdatedOn(updatedOn);

    }
}
