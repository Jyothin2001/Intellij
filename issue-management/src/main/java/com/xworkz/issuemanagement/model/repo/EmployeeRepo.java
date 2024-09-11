package com.xworkz.issuemanagement.model.repo;

import com.xworkz.issuemanagement.dto.DepartmentDTO;
import com.xworkz.issuemanagement.dto.EmployeeDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepo  {

    boolean saveEmployeeDetails(EmployeeDTO employeeDTO);

    //fetch departmentName for storing departmentId
    DepartmentDTO findByDepartmentType(String employeeDeptName);

    //to check whether email exists or not in database

    EmployeeDTO findByEmail(String  emailId);



}
