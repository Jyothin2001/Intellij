package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.constants.Status;
import com.xworkz.issuemanagement.dto.ComplaintRaiseDTO;
import com.xworkz.issuemanagement.dto.DepartmentDTO;
import com.xworkz.issuemanagement.dto.EmployeeDTO;
import com.xworkz.issuemanagement.dto.RegDeptAdminDTO;

import javax.servlet.http.HttpSession;
import java.util.List;

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

   List< ComplaintRaiseDTO>  deptAdminView(String departmentName);

    //de-active employee status
    void deactivateStatus(int employee_id, Status status);


    List<EmployeeDTO> getAllEmployeeNames(String regDepartmentName);

    //update status and employee id
    boolean updateStatusAndEmployeeId(int complaintId, int employeeId, String status);





}
