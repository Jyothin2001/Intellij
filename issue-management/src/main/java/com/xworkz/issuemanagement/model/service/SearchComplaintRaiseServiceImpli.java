package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.ComplaintRaiseDTO;
import com.xworkz.issuemanagement.model.repo.SearchComplaintRaiseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class SearchComplaintRaiseServiceImpli implements SearchComplaintRaiseService {

    @Autowired
    private SearchComplaintRaiseRepo searchComplaintRaiseRepo;


    @Override
    public List<ComplaintRaiseDTO> getComplaintType(ComplaintRaiseDTO complaintType) {
       return searchComplaintRaiseRepo.getByComplaintType(complaintType);

    }

    @Override
    public List<ComplaintRaiseDTO> getComplaintRaiseArea(ComplaintRaiseDTO complaintRaiseArea) {

        return searchComplaintRaiseRepo.getByComplaintRaiseArea(complaintRaiseArea);
    }
}
