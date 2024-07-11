package com.xworkz.issuemanagement.model.service;

public interface ResetPasswordService {

    public boolean resetPassword(String email, String oldPassword, String newPassword, String confirmPassword);

    //send Reset password to email write in MailService




}
