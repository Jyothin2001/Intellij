package com.xworkz.issuemanagement.model.repo;

public interface ChangePasswordRepo
{
    boolean emailExists(String email);

    boolean verifyOldPassword(String email, String oldPassword);

    boolean updatePassword(String email, String newPassword);


}
