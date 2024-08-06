package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.SignUpDTO;
import com.xworkz.issuemanagement.model.repo.ForgotPasswordRepo;
import com.xworkz.issuemanagement.model.repo.SignInRepo;
import com.xworkz.issuemanagement.model.repo.SignUpRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SignInServiceImpli implements SignInService
{
     @Autowired
    private SignInRepo signInRepo;

     @Autowired
     private ForgotPasswordRepo forgotPasswordRepo;

     @Autowired
     private PasswordEncoder passwordEncoder;



//    //To store password in db with respective mail
//    @Override
//    public SignUpDTO findByEmailAndPassword(String email, String password)
//    {
//
//        SignUpDTO signUpDTO= signInRepo.findByEmailAndPassword(email, password);
//        System.out.println("repo password mathod in service:"+signUpDTO);
//        if (signUpDTO != null && !signUpDTO.isAccountLocked() && signUpDTO.getPassword().equals(password)) {
//            return signUpDTO;
//        }
//        return null;
//    }


    @Override
    public SignUpDTO findByEmailAndPassword(String email, String password) {
      SignUpDTO user=  signInRepo.findByEmail(email);

      if(user!=null && !user.isAccountLocked() && passwordEncoder.matches(password,user.getPassword()))
      {
          log.info("Data is present:{} " ,user);
          return user;
      }
        log.info("Data is not present:{} " , email);
      return null;
    }

    //If number of attempts increases(Wrong password), count that in db and >3 account is locked
    @Override
    public void incrementFailedAttempts(String email)
    {
        SignUpDTO user =signInRepo.findByEmail(email);
        if (user != null)
        {
            int attempts = user.getFailedAttempt() + 1;
            user.setFailedAttempt(attempts);
            if (attempts >= 3)
            {
                user.setAccountLocked(true);
            }
            signInRepo.updateFailedAttempts(user);
        }
    }

    @Override
    public int getFailedAttempts(String email)
    {
        SignUpDTO user = signInRepo.findByEmail(email);
        return (user != null) ? user.getFailedAttempt():0;

    }

    @Override
    public void resetFailedAttempts(String email)
    {
        SignUpDTO user = signInRepo.findByEmail(email);
        if (user != null)
        {
            user.setFailedAttempt(0); //false
            signInRepo.updateFailedAttempts(user);
        }

    }

    @Override
    public void lockAccount(String email)
    {
        SignUpDTO user = signInRepo.findByEmail(email);
        if (user != null)
        {
            user.setAccountLocked(true);
            signInRepo.updateFailedAttempts(user);
        }
    }

    @Override
    public void unLockAccount(String email)
    {
       SignUpDTO signUpDTO= signInRepo.findByEmail(email);
        if(signUpDTO!=null)
        {
            signUpDTO.setAccountLocked(false);
            signInRepo.updateFailedAttempts(signUpDTO);
        }
    }
}
