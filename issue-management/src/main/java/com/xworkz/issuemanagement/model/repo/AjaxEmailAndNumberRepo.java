package com.xworkz.issuemanagement.model.repo;

import com.xworkz.issuemanagement.dto.SignUpDTO;

public interface AjaxEmailAndNumberRepo
{
    //Ajax for Email
    public SignUpDTO existsByEmail(String email);

    public SignUpDTO existsByNumber(Long contactNumber);

}
