package com.xworkz.issuemanagement.model.repo;

import com.xworkz.issuemanagement.dto.DepartmentDTO;
import com.xworkz.issuemanagement.dto.RegDeptAdminDTO;

public interface RegDeptAdminRepo {

    boolean saveRegDeptAdmin(RegDeptAdminDTO regDeptAdminDTO);

    RegDeptAdminDTO getEmailAndPassword(String email,String password,String departmentName);

    RegDeptAdminDTO getEmail(String email);

    //update account locked and attempt failed
    boolean update(RegDeptAdminDTO  regDeptAdminDTO);

    //fetch departmentName for storing departmentId
    DepartmentDTO findByDepartmentType(String regDepartmentName);

}
