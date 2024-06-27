package com.xworkz.feedback.model.service;

import com.xworkz.feedback.dto.FeedbackDTO;

public interface FeedbackService
{
    public boolean save(FeedbackDTO feedbackDTO);

    //we can use default also
    default boolean delete(int id)
    {
        return true;
    }



}
