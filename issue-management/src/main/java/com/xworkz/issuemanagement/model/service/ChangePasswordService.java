package com.xworkz.issuemanagement.model.service;

public interface ChangePasswordService {

    public boolean changePassword(String email, String oldPassword, String newPassword, String confirmPassword);

    //send Reset password to email write in MailService




}
