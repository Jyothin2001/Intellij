package com.xworkz.issuemanagement.model.repo;

import com.xworkz.issuemanagement.dto.DepartmentDTO;
import com.xworkz.issuemanagement.dto.EmployeeDTO;

public interface EmployeeRepo  {

    boolean saveEmployeeDetails(EmployeeDTO employeeDTO);

    //fetch departmentName for storing departmentId
    DepartmentDTO findByDepartmentType(String employeeDeptName);

    //to check whether email exists or not in database

    EmployeeDTO findByEmail(String  emailId);



    public EmployeeDTO updateEmployeeDetails(EmployeeDTO employeeDTO);



}
