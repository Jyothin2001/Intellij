package com.xworkz.issuemanagement.model.service;

public interface ChangePasswordService {

    public String changePassword(String email, String oldPassword, String newPassword, String confirmPassword);

    public String subAdminChangePassword(String email, String oldPassword, String newPassword, String confirmPassword);





}
