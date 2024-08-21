package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.SignUpDTO;

public interface AjaxEmailAndNumberService {

    boolean existsByEmail(String email);

    boolean existsByNumber(Long contactNumber);

    boolean existsBySubAdminEmail(String email);

    boolean existsBySubAdminNumber(Long contactNumber);

}
