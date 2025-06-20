package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.SignUpDTO;

public interface AjaxEmailAndNumberService {

    //user
    boolean existsByEmail(String email);

    boolean existsByNumber(Long contactNumber);

    //sub admin
    boolean existsBySubAdminEmail(String email);

    boolean existsBySubAdminNumber(Long contactNumber);


    //employee
     boolean isEmailExists(String email);

     boolean isContactNumberExists(Long contactNumber);


}
