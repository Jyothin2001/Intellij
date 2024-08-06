package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.RegDeptAdminDTO;
import com.xworkz.issuemanagement.emailSending.MailSend;
import com.xworkz.issuemanagement.model.repo.RegDeptAdminRepo;
import com.xworkz.issuemanagement.util.PasswordGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RegDeptAdminServiceImpli implements RegDeptAdminService{

    @Autowired
    private RegDeptAdminRepo regDeptAdminRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MailSend mailSend;

    @Override
    public boolean saveRegDeptAdmin(RegDeptAdminDTO regDeptAdminDTO) {

        //Random password generate and encode and save in db
        String generatePassword=PasswordGenerator.generatePassword();
        regDeptAdminDTO.setPassword(passwordEncoder.encode(generatePassword));

        boolean saveRegDeptAdmin=regDeptAdminRepo.saveRegDeptAdmin(regDeptAdminDTO);
        if(saveRegDeptAdmin)
        {
            log.info("regDeptAdminRepo() in RegDeptAdminService successful:{}",saveRegDeptAdmin);
            regDeptAdminDTO.setPassword(generatePassword);
            mailSend.sendDeptAdminPassword(regDeptAdminDTO);
            return saveRegDeptAdmin;
        }
        else {
            log.info("regDeptAdminRepo() in RegDeptAdminService not successful:");
        }
        return false;
    }
}
