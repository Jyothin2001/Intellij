package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.ComplaintRaiseDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public interface ComplaintRaiseService
{
    boolean saveComplaintRaiseDetails(ComplaintRaiseDTO complaintRaiseDTO);

    //used for view complaintRaise
    List<ComplaintRaiseDTO> findByComplaintsByUserId(int complaintId);


    Optional<ComplaintRaiseDTO> findByUserId(HttpServletRequest request);

}
