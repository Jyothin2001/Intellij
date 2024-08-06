package com.xworkz.issuemanagement.model.repo;

import com.xworkz.issuemanagement.dto.RegDeptAdminDTO;

public interface RegDeptAdminRepo {

    boolean saveRegDeptAdmin(RegDeptAdminDTO regDeptAdminDTO);
    RegDeptAdminDTO getEmailAndPassword(String email,String password);
}
