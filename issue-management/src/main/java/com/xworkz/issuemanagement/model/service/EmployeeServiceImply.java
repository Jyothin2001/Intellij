package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.DepartmentDTO;
import com.xworkz.issuemanagement.dto.EmployeeDTO;
import com.xworkz.issuemanagement.emailSending.MailSend;
import com.xworkz.issuemanagement.model.repo.EmployeeRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class EmployeeServiceImply implements EmployeeService
{

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private MailSend mailSend;


    @Override
    public boolean saveEmployeeDetails(EmployeeDTO employeeDTO)
    {
        boolean employeeDetails=employeeRepo.saveEmployeeDetails(employeeDTO);
        if(employeeDetails)
        {
            log.info("saveEmployeeDetails() is successful in EmployeeServiceImply:{}",employeeDetails);
             return true;
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

    @Override
    public EmployeeDTO findByEmail(String emailId) {

            log.info("findByEmail method running EmployeeServiceImpl..");

            log.info("Your EmailId : {}", emailId);
            EmployeeDTO employeeDTO = employeeRepo.findByEmail(emailId);

            if (employeeDTO != null) {
                log.info("EmailId exists in database");
                return employeeDTO;
            } else {
                log.info("EmailId not exists in database");
            }


            return null;
        }


    }



