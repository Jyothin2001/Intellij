package com.xworkz.issuemanagement.model.repo;

import com.xworkz.issuemanagement.dto.SignUpDTO;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

public interface ForgotPasswordRepo
{
    public SignUpDTO findByEmail(String email);

    public void updatePassword(String email,String newPassword);

    //public SignUpDTO findBySubAdminEmail(String email);

    public void updateSubAdminPassword(String email,String newPassword);
}
