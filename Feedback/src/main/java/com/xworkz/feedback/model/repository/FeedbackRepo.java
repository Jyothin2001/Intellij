package com.xworkz.feedback.model.repository;

import com.xworkz.feedback.dto.FeedbackDTO;

public interface FeedbackRepo
{
    public boolean saveAndValidate(FeedbackDTO feedbackDTO);

    //delete based on id
    public boolean delete(int id);
}
