package com.xworkz.issuemanagement.model.repo;

import com.xworkz.issuemanagement.dto.ComplaintRaiseDTO;

import java.util.List;
import java.util.Optional;

public interface ComplaintRaiseRepo {

    //to save
    boolean saveComplaintRaiseDetails(ComplaintRaiseDTO complaintRaiseDTO);

    //view for ComplaintRaise
     List<ComplaintRaiseDTO> findByComplaintsByUserId(int complaintId);

     //to set fk
     //for session like particular userid details
    Optional<ComplaintRaiseDTO> findByUserId(int userId);


    //to edit
    Optional<ComplaintRaiseDTO> findByComplaintId(int complaintId);


    //to update
    ComplaintRaiseDTO updateEditedComplaints(ComplaintRaiseDTO complaintRaiseDTO);
}
