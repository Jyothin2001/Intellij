package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.ComplaintRaiseDTO;

import java.util.List;

public interface SearchComplaintRaiseService {


    List<ComplaintRaiseDTO> getComplaintType(ComplaintRaiseDTO complaintType);

    List<ComplaintRaiseDTO> getComplaintRaiseArea(ComplaintRaiseDTO complaintRaiseArea);
}
