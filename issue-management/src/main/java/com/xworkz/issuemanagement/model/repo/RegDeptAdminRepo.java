package com.xworkz.issuemanagement.model.repo;

import com.xworkz.issuemanagement.constants.Status;
import com.xworkz.issuemanagement.dto.ComplaintRaiseDTO;
import com.xworkz.issuemanagement.dto.DepartmentDTO;
import com.xworkz.issuemanagement.dto.EmployeeDTO;
import com.xworkz.issuemanagement.dto.RegDeptAdminDTO;

import java.util.List;

public interface RegDeptAdminRepo {

    boolean saveRegDeptAdmin(RegDeptAdminDTO regDeptAdminDTO);

    RegDeptAdminDTO getEmailAndPassword(String email,String password,String departmentName);

    RegDeptAdminDTO getEmail(String email);

    //update account locked and attempt failed
    boolean update(RegDeptAdminDTO  regDeptAdminDTO);

    //fetch departmentName for storing departmentId
    DepartmentDTO findByDepartmentType(String regDepartmentName);

    //deptViewComplaints details
    List<ComplaintRaiseDTO> deptAdminView(String departmentName);

    //de-active employee status
    void deactivateStatus(int employee_id, Status status);

    List<EmployeeDTO> getAllEmployeeNames(String regDepartmentName);

    //update status and employee id
    boolean updateStatusAndEmployeeId(int complaintId, int employeeId);

    boolean isEmployeeAllocated(int employeeId);



    boolean updateComplaintForDeactivatedEmployee(ComplaintRaiseDTO complaintRaiseDTO);

}
