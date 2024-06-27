package com.xworkz.feedback.model.service;

import com.xworkz.feedback.dto.FeedbackDTO;
import com.xworkz.feedback.dto.SearchDTO;

import java.util.List;

public interface SearchService
{
    public List<FeedbackDTO> search(SearchDTO searchDTO);

    default FeedbackDTO findById(int id)
    {
        return null;
    }
}
