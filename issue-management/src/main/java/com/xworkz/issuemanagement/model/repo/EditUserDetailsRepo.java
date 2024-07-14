package com.xworkz.issuemanagement.model.repo;

import com.xworkz.issuemanagement.dto.SignUpDTO;

public interface EditUserDetailsRepo {

    //we want findByEmail() so i reuse that from signIn
    SignUpDTO findByEmail(String email);

    void updateUserDetails(SignUpDTO signUpDTO);

    
}
