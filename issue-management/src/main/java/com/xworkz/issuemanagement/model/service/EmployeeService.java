package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.DepartmentDTO;
import com.xworkz.issuemanagement.dto.EmployeeDTO;
import com.xworkz.issuemanagement.dto.SignUpDTO;

import javax.servlet.http.HttpSession;

public interface EmployeeService {
    boolean saveEmployeeDetails(EmployeeDTO employeeDTO);

    //fetch departmentName for storing departmentId
    DepartmentDTO findByDepartmentType(String employeeDeptName);

    //to check whether emailId exists or not in database
    EmployeeDTO findByEmail(String emailId);

    EmployeeDTO updateEmployeeDetails(EmployeeDTO employeeDTO);



}
