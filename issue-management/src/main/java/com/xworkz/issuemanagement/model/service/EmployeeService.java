package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.DepartmentDTO;
import com.xworkz.issuemanagement.dto.EmployeeDTO;

public interface EmployeeService {
    boolean saveEmployeeDetails(EmployeeDTO employeeDTO);

    //fetch departmentName for storing departmentId
    DepartmentDTO findByDepartmentType(String employeeDeptName);

}
