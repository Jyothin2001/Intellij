package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.RegDeptAdminDTO;
import com.xworkz.issuemanagement.dto.SignUpDTO;
import com.xworkz.issuemanagement.model.repo.AjaxEmailAndNumberRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AjaxEmailAndNumberServiceImpli implements AjaxEmailAndNumberService
{
    @Autowired
    private AjaxEmailAndNumberRepo ajaxEmailRepo;


    @Override
    public boolean existsByEmail(String email)
    {
        log.info("Exists by email: {}", email);
       SignUpDTO signUpDTO= ajaxEmailRepo.existsByEmail(email);
       if(signUpDTO!=null)
       {return true;}
       else
       {return false;}
    }

    //two kind of return it reduce line if()
    @Override
    public boolean existsByNumber(Long contactNumber)
    {
       SignUpDTO signUpDTO= ajaxEmailRepo.existsByNumber(contactNumber);
        return signUpDTO != null;
    }

    @Override
    public boolean existsBySubAdminEmail(String email) {
        log.info("Exists by email: {}", email);
        RegDeptAdminDTO regDeptAdminDTO= ajaxEmailRepo.existsBySubAdminEmail(email);
        if(regDeptAdminDTO!=null)
        {return true;}
        else
        {return false;}

    }

    @Override
    public boolean existsBySubAdminNumber(Long contactNumber) {
        RegDeptAdminDTO regDeptAdminDTO= ajaxEmailRepo.existsBySubAdminNumber(contactNumber);
        return regDeptAdminDTO != null;

    }
}
