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
import java.time.LocalDateTime;
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


//    @Override
//    public boolean saveEmployeeDetails(EmployeeDTO employeeDTO)
//    {
//
//        boolean employeeDetails=employeeRepo.saveEmployeeDetails(employeeDTO);
//        if(employeeDetails)
//        {
//            Long noOfEmployee= employeeRepo.countNoOfActiveEmployeeByDeptId(employeeDTO.getDepartmentDTO().getId());
//
//          DepartmentDTO departmentDTO=employeeRepo.findByDepartmentId(employeeDTO.getDepartmentDTO().getId());
//
//          if(departmentDTO!=null)
//          {
//              departmentDTO.setNoOfEmployees(noOfEmployee);
//              employeeRepo.updateDepartmentDTO(departmentDTO);
//          }
//
//            log.info("saveEmployeeDetails() is successful in EmployeeServiceImply:{}",employeeDetails);
//             return true;
//        }
//        else
//        {
//            log.info("saveEmployeeDetails() is not successful in EmployeeServiceImply");
//        }
//        return false;
//    }

    @Override
    public boolean saveEmployeeDetails(EmployeeDTO employeeDTO) {
        boolean employeeSaved = false;

        // Save the employee details
        employeeSaved = employeeRepo.saveEmployeeDetails(employeeDTO);

        if (employeeSaved) {
            // If saving employee details was successful, update the department's employee count
            try {
                // Obtain the department ID from the employeeDTO
                int departmentId = employeeDTO.getDepartmentDTO().getId();

                // Fetch the updated count of active employees
                Long noOfActiveEmployees = employeeRepo.countNoOfActiveEmployeeByDeptId(departmentId);

                // Fetch the department entity
                DepartmentDTO departmentDTO = employeeRepo.findByDepartmentId(departmentId);

                if (departmentDTO != null) {
                    // Update the number of active employees in the department
                    departmentDTO.setNoOfEmployees(noOfActiveEmployees);
                    employeeRepo.updateDepartmentDTO(departmentDTO); // Assuming this method updates the department in the repository

                    log.info("Employee details saved and department employee count updated successfully.");
                    return true;
                } else {
                    log.error("DepartmentDTO not found for ID: {}", departmentId);
                }

            } catch (Exception e) {
                log.error("Error occurred while updating department employee count: {}", e.getMessage());
                // Handle exception if needed
            }
        } else {
            log.info("saveEmployeeDetails() is not successful in EmployeeServiceImpl");
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

    @Override
    public EmployeeDTO updateEmployeeDetails(EmployeeDTO employeeDTO) {

        log.info("updateEmployeeDetails method running in employeeServiceImply..");

         employeeRepo.updateEmployeeDetails(employeeDTO);

        return employeeDTO;



    }


}



