package com.xworkz.issuemanagement.model.repo;


import com.xworkz.issuemanagement.dto.SignUpDTO;

public interface SignUpRepo
{

public boolean saveAndValidate(SignUpDTO signUpDTO);




}
