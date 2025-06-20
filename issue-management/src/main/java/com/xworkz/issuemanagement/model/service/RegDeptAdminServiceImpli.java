package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.constants.Status;
import com.xworkz.issuemanagement.dto.ComplaintRaiseDTO;
import com.xworkz.issuemanagement.dto.DepartmentDTO;
import com.xworkz.issuemanagement.dto.EmployeeDTO;
import com.xworkz.issuemanagement.dto.RegDeptAdminDTO;
import com.xworkz.issuemanagement.emailSending.MailSend;
import com.xworkz.issuemanagement.model.repo.RegDeptAdminRepo;
import com.xworkz.issuemanagement.util.PasswordGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class RegDeptAdminServiceImpli implements RegDeptAdminService{

    @Autowired
    private RegDeptAdminRepo regDeptAdminRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MailSend mailSend;

    @Autowired
    private JavaMailSender mailSender;


    @Override
    public String saveRegDeptAdmin(RegDeptAdminDTO regDeptAdminDTO) {

        //Random password generate and encode and save in db
        String generatePassword=PasswordGenerator.generatePassword();



        regDeptAdminDTO.setPassword(generatePassword);//send to user
        String emailStatus= mailSend.sendDeptAdminPassword(regDeptAdminDTO);

        regDeptAdminDTO.setPassword(passwordEncoder.encode(generatePassword));//save in db


        if ("network_error".equals(emailStatus)) {
            return "network_error"; // return network error status
        }


        boolean saveRegDeptAdmin=regDeptAdminRepo.saveRegDeptAdmin(regDeptAdminDTO);
        if(saveRegDeptAdmin)
        {
            log.info("regDeptAdminRepo() in RegDeptAdminService successful:{}",saveRegDeptAdmin);
            return "success";
        }
        else {
            log.info("regDeptAdminRepo() in RegDeptAdminService not successful:");
        }
        return "error";
    }



    @Override
    public RegDeptAdminDTO getEmailAndPassword(String email, String password, String departmentName) {
        RegDeptAdminDTO emailData = regDeptAdminRepo.getEmail(email);
        log.info("email and password matching data:{}", emailData);

        if (emailData != null) {
            if (emailData.isAccountLocked()) {
                log.info("Account is locked for email: {}", email);
                return null; // Don't allow login if account is locked
            }

            // Check password and department name
            if (passwordEncoder.matches(password, emailData.getPassword()) && emailData.getDepartmentName().equals(departmentName)) {
                log.info("findEmailAndPassword successful in AdminServiceImpl..");
                return emailData;
            }
        }

        log.info("findEmailAndPassword not successful in AdminServiceImpl..");
        return null;
    }

//    @Override
//    public RegDeptAdminDTO getEmailAndPassword(String email, String password,String departmentName)
//    {
//         //RegDeptAdminDTO emailData=regDeptAdminRepo.getEmailAndPassword(email,password); //generated password is not there/match the query because in db encrypt password is there so no match get via email only (no query found exception)
//        RegDeptAdminDTO emailData=regDeptAdminRepo.getEmail(email);
//         log.info("email and password matching data:{}",emailData);
//
//         if(emailData!=null && passwordEncoder.matches(password,emailData.getPassword()) && emailData.getDepartmentName().equals(departmentName))
//         {log.info("findEmailAndPassword successful in  AdminServiceImpl..");
//             return emailData;
//         }
//         else
//         {
//             log.info("findEmailAndPassword not successful in AdminServiceImpl..");
//         }
//return null;
//}

    @Override
    public void incrementFailedAttempts(String email) {
        RegDeptAdminDTO user= regDeptAdminRepo.getEmail(email);
        if(user!=null)
        {
            int attempts=user.getFailedAttempt() + 1;  //0+1=1,1+1=2,2+1=3..
            user.setFailedAttempt(attempts);// set and get the number
            if(attempts>3)
            {
                user.setAccountLocked(true);
            }
            regDeptAdminRepo.update(user);
        }
    }

    @Override
    public int getFailedAttempts(String email) {
       RegDeptAdminDTO user= regDeptAdminRepo.getEmail(email);
       return (user!=null) ? user.getFailedAttempt():0;
    }

    @Override
    public void resetFailedAttempts(String email) {
      RegDeptAdminDTO user= regDeptAdminRepo.getEmail(email);
      if(user!=null)
      {
          user.setFailedAttempt(0);//false
          regDeptAdminRepo.update(user);
      }
    }

    @Override
    public void lockAccount(String email) {
        RegDeptAdminDTO user= regDeptAdminRepo.getEmail(email);
        if(user!=null)
        {
            user.setAccountLocked(true);
            regDeptAdminRepo.update(user);
        }

    }

    @Override
    public void unlockAccount(String email) {
        RegDeptAdminDTO user= regDeptAdminRepo.getEmail(email);
        if(user!=null)
        {
            user.setAccountLocked(false);
            regDeptAdminRepo.update(user);
        }
    }

    @Override
    public DepartmentDTO findByDepartmentType(String regDepartmentName)
    {
        DepartmentDTO departmentType= regDeptAdminRepo.findByDepartmentType(regDepartmentName);
        if(departmentType!=null)
        {
            log.info("fetching RegDepartmentDetails:{}",departmentType);
            return departmentType;
        }
        else
        {
            log.info("Not fetching RegDepartmentDetails:");
        }
        return null;
    }

    @Override
    public List<ComplaintRaiseDTO> deptAdminView(String departmentName) {
        List<ComplaintRaiseDTO> complaintDetails=regDeptAdminRepo.deptAdminView(departmentName);
        if(complaintDetails!=null)
        {
            log.info("fetching complaintRaiseDetails:{}",departmentName);
            return complaintDetails;
        }
        else
        {
            log.info(" fetching complaintRaiseDetails is not successful:");
        }

        return null;
    }

    @Override
    public void deactivateStatus(int employeeId, Status status) {
        log.info("update deactivate status of employee in RegDeptAdminServiceImply:" );
        regDeptAdminRepo.deactivateStatus(employeeId,status);

    }

    @Override
    public List<EmployeeDTO> getAllEmployeeNames(String regDepartmentName)
    {
        log.info(" employeeNames in RegDeptAdminServiceImply:" );

        List<EmployeeDTO> employeeNames=  regDeptAdminRepo.getAllEmployeeNames(regDepartmentName);
        return employeeNames!=null?employeeNames: Collections.emptyList();

    }

    @Override
    public boolean updateStatusAndEmployeeId(int complaintId, int employeeId) {
        log.info("updateStatusAndEmployeeId method running in RaiseComplaintService");
        return regDeptAdminRepo.updateStatusAndEmployeeId(complaintId, employeeId);

    }
@Override
    public boolean isEmployeeAllocated(int employeeId) {
        // Logic to check if the employee is allocated
        // This is just an example; implement according to your needs
        return regDeptAdminRepo.isEmployeeAllocated(employeeId);
    }

    @Override
    public boolean updateComplaintForDeactivatedEmployee(ComplaintRaiseDTO complaintRaiseDTO) {
        log.info("updateComplaintForDeactivatedEmployee method running in RaiseComplaintService");
       return regDeptAdminRepo.updateComplaintForDeactivatedEmployee(complaintRaiseDTO);
    }


}