package com.xworkz.issuemanagement.model.repo;

public interface ResetPasswordRepo
{
    boolean emailExists(String email);

    boolean verifyOldPassword(String email, String oldPassword);

    void updatePassword(String email, String newPassword);


}
