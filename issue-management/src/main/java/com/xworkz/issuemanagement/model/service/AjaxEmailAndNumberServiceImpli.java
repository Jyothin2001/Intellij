package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.SignUpDTO;
import com.xworkz.issuemanagement.model.repo.AjaxEmailAndNumberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AjaxEmailAndNumberServiceImpli implements AjaxEmailAndNumberService
{
    @Autowired
    private AjaxEmailAndNumberRepo ajaxEmailRepo;


    @Override
    public boolean existsByEmail(String email)
    {
        System.out.println("Exists by email: "+ email);
       SignUpDTO signUpDTO= ajaxEmailRepo.existsByEmail(email);
       if(signUpDTO!=null)
        return true;
       else
       return false;
    }

    //two kind of return it reduce line if()
    @Override
    public boolean existsByNumber(Long contactNumber)
    {
       SignUpDTO signUpDTO= ajaxEmailRepo.existsByNumber(contactNumber);
        return signUpDTO != null;
    }
}
