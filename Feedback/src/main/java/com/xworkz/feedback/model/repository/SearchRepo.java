package com.xworkz.feedback.model.repository;

import com.xworkz.feedback.dto.FeedbackDTO;
import com.xworkz.feedback.dto.SearchDTO;

import java.util.List;

public interface SearchRepo
{
    public List<FeedbackDTO> search(SearchDTO searchDTO);

    default FeedbackDTO findById(int id)
    {
        return null;
    }

}
