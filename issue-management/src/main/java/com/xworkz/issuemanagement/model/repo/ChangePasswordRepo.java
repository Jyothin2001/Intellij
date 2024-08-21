package com.xworkz.issuemanagement.model.repo;

public interface ChangePasswordRepo
{
    boolean emailExists(String email);

    boolean verifyOldPassword(String email, String oldPassword);

    boolean updatePassword(String email, String newPassword);

    //*****subAdmin*************
    //boolean emailExists(String email);// i reuse it from  RegDeptAdminRepo.getEmail()

    boolean verifyOldPasswordSubAdmin(String email, String oldPassword);

    boolean updatePasswordSubAdmin(String email, String newPassword);


}
