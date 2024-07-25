package com.xworkz.issuemanagement.model.repo;

import com.xworkz.issuemanagement.dto.ComplaintRaiseDTO;

import java.util.List;

public interface SearchComplaintRaiseRepo {

    List<ComplaintRaiseDTO> getByComplaintType(ComplaintRaiseDTO complaintType);

    List<ComplaintRaiseDTO> getByComplaintRaiseArea(ComplaintRaiseDTO complaintRaiseArea);
}
