package com.xworkz.issuemanagement.model.service;

public interface ForgotPasswordService {

    String forgotPassword(String email);

    String forgotPasswordBySubAdmin(String email);
}
