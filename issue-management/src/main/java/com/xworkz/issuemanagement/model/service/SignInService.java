package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.SignUpDTO;

public interface SignInService
{
    SignUpDTO findByEmailAndPassword(String email, String password);

    //to lock  sign in/ page disabled button
    void incrementFailedAttempts(String email);

    int getFailedAttempts(String email);

    void resetFailedAttempts(String email);

    void lockAccount(String email);

}
