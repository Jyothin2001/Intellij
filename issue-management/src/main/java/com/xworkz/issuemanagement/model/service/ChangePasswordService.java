package com.xworkz.issuemanagement.model.service;

public interface ChangePasswordService {

    public boolean changePassword(String email, String oldPassword, String newPassword, String confirmPassword);

    public boolean subAdminChangePassword(String email, String oldPassword, String newPassword, String confirmPassword);





}
