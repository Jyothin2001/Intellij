package com.xworkz.issuemanagement.model.repo;

import com.xworkz.issuemanagement.dto.SignUpDTO;

public interface SignInRepo {

    //Sending password to email and Sign-in
    SignUpDTO findByEmailAndPassword(String email, String password);

    //count the failed attempts in db
    //Lock account when give 3 times wrong Password
    //Add this method to find a user by email
    SignUpDTO findByEmail(String email);

    //fetch adminName to display in navBar
    String getUserName(String email,String password);

    boolean updateFailedAttempts(SignUpDTO signUpDTO);

}
