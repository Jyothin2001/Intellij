package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.ComplaintRaiseDTO;
import com.xworkz.issuemanagement.dto.SignUpDTO;
import com.xworkz.issuemanagement.model.repo.ComplaintRaiseRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ComplaintRaiseServiceImpli implements ComplaintRaiseService
{
    @Autowired
    private ComplaintRaiseRepo complaintRaiseRepo;

    @Autowired
    private HttpSession httpSession;

    @Override
    public boolean saveComplaintRaiseDetails(ComplaintRaiseDTO complaintRaiseDTO)
    {
        log.info("saveComplaintRaiseDetails() running complaintRaiseRepo");

        boolean data= complaintRaiseRepo. saveComplaintRaiseDetails(complaintRaiseDTO);
        if(data)
        {
            log.info("saveComplaintRaiseDetails() is successful in complaintRaiseService");
            log.info("complaintRaiseDTO data in service:{},{}",data,complaintRaiseDTO);
            return data;

        }
        else {
            log.info("saveComplaintRaiseDetails not successful in complaintRaiseService");

        }
        return true;
    }

//view
    @Override
    public List<ComplaintRaiseDTO> findByComplaintsByUserId(int userId)
    {
        return complaintRaiseRepo.findByComplaintsByUserId(userId);
    }

//    @Override
//    public ComplaintRaiseDTO getComplaintsByUserId(int userId)
//    {
//       return  complaintRaiseRepo.findByComplaintId(userId);
//
//    }

    //to set foreign key
    @Override
    public Optional<ComplaintRaiseDTO> findByUserId(HttpServletRequest request)
    {
        HttpSession httpSession=request.getSession();

      SignUpDTO signInUserId=  (SignUpDTO) httpSession.getAttribute("signUpDTO");
      if(signInUserId!=null)
      {
          log.info("signInUserId:{}",signInUserId);
          return complaintRaiseRepo.findByUserId(signInUserId.getId());
      }
        return Optional.empty();
    }


}
