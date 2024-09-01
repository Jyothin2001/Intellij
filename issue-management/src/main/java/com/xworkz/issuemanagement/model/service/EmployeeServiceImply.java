package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.DepartmentDTO;
import com.xworkz.issuemanagement.dto.EmployeeDTO;
import com.xworkz.issuemanagement.model.repo.EmployeeRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmployeeServiceImply implements EmployeeService
{

    @Autowired
    private EmployeeRepo employeeRepo;

    @Override
    public boolean saveEmployeeDetails(EmployeeDTO employeeDTO)
    {
        boolean employeeDetails=employeeRepo.saveEmployeeDetails(employeeDTO);
        if(employeeDetails)
        {
            log.info("saveEmployeeDetails() is successful in EmployeeServiceImply:{}",employeeDetails);
            return employeeDetails;
        }
        else
        {
            log.info("saveEmployeeDetails() is not successful in EmployeeServiceImply");
        }
        return false;
    }

    @Override
    public DepartmentDTO findByDepartmentType(String employeeDeptName)
    {
        DepartmentDTO departmentType= employeeRepo.findByDepartmentType(employeeDeptName);
        if(departmentType!=null)
        {
            log.info("fetching employeeDeptName:{}",departmentType);
            return departmentType;
        }
        else
        {
            log.info("Not fetching employeeDeptName:");
        }
        return null;
    }








}
