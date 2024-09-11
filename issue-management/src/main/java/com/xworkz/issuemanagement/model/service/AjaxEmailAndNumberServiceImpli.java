package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.RegDeptAdminDTO;
import com.xworkz.issuemanagement.dto.SignUpDTO;
import com.xworkz.issuemanagement.model.repo.AjaxEmailAndNumberRepo;
import com.xworkz.issuemanagement.model.repo.AjaxForEmployee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AjaxEmailAndNumberServiceImpli implements AjaxEmailAndNumberService
{
    @Autowired
    private AjaxEmailAndNumberRepo ajaxEmailAndNumberRepo;



    @Override
    public boolean existsByEmail(String email)
    {
        log.info("Exists by email: {}", email);
       SignUpDTO signUpDTO= ajaxEmailAndNumberRepo.existsByEmail(email);
       if(signUpDTO!=null)
       {return true;}
       else
       {return false;}
    }

    //two kind of return it reduce line if()
    @Override
    public boolean existsByNumber(Long contactNumber)
    {
       SignUpDTO signUpDTO= ajaxEmailAndNumberRepo.existsByNumber(contactNumber);
        return signUpDTO != null;
    }

    @Override
    public boolean existsBySubAdminEmail(String email) {
        log.info("Exists by email: {}", email);
        RegDeptAdminDTO regDeptAdminDTO= ajaxEmailAndNumberRepo.existsBySubAdminEmail(email);
        if(regDeptAdminDTO!=null)
        {return true;}
        else
        {return false;}

    }

    @Override
    public boolean existsBySubAdminNumber(Long contactNumber) {
        RegDeptAdminDTO regDeptAdminDTO= ajaxEmailAndNumberRepo.existsBySubAdminNumber(contactNumber);
        return regDeptAdminDTO != null;

    }



    //ajax
    public boolean isEmailExists(String email) {
        log.info("checking email from EmployeeDTO in AjaxEmailAndNumberService");
        return ajaxEmailAndNumberRepo.findByEmail(email).isPresent();
    }

    public boolean isContactNumberExists(Long contactNumber) {
        log.info("checking number from EmployeeDTO in AjaxEmailAndNumberService");
        return ajaxEmailAndNumberRepo.findByContactNumber(contactNumber).isPresent();
    }

}
