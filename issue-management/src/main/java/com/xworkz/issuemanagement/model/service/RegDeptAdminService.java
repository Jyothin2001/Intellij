package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.DepartmentDTO;
import com.xworkz.issuemanagement.dto.RegDeptAdminDTO;

public interface RegDeptAdminService {

    boolean saveRegDeptAdmin(RegDeptAdminDTO regDeptAdminDTO);

    RegDeptAdminDTO getEmailAndPassword(String email,String password,String departmentName);

    //checking wrong password and lock the account

    void incrementFailedAttempts(String email);

    int getFailedAttempts(String email);

    void resetFailedAttempts(String email);

    void lockAccount(String email);

    //to unlock when I new password generate
    void unlockAccount(String email);

    //fetch departmentName for storing departmentId
    DepartmentDTO findByDepartmentType(String regDepartmentName);

}
