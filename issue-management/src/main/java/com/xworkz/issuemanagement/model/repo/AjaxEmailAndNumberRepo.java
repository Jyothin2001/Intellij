package com.xworkz.issuemanagement.model.repo;

import com.xworkz.issuemanagement.dto.RegDeptAdminDTO;
import com.xworkz.issuemanagement.dto.SignUpDTO;

public interface AjaxEmailAndNumberRepo
{
    //Ajax for user Email
    public SignUpDTO existsByEmail(String email);

    //Ajax for sub admin number
    public SignUpDTO existsByNumber(Long contactNumber);

    //Ajax for sub admin Email
    public RegDeptAdminDTO existsBySubAdminEmail(String email);

    //Ajax for sub admin number
    public RegDeptAdminDTO existsBySubAdminNumber(Long contactNumber);

}
