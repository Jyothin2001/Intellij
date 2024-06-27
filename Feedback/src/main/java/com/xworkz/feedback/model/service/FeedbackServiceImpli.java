package com.xworkz.feedback.model.service;

import com.xworkz.feedback.dto.FeedbackDTO;
import com.xworkz.feedback.model.repository.FeedbackRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackServiceImpli implements FeedbackService
{
    @Autowired
    private FeedbackRepo feedbackRepo;


    public FeedbackServiceImpli()
    {
        System.out.println("FeedbackServiceImpli constructor:");
    }



    @Override
    public boolean save(FeedbackDTO feedbackDTO)
    {

        boolean data= this.feedbackRepo.saveAndValidate(feedbackDTO);

        if(data)
            System.out.println("repo is save in service successfully: "+ feedbackDTO);
        else
            System.out.println("repo is not save in service successfully: "+ feedbackDTO);

        return true;
    }

    @Override
    public boolean delete(int id)
    {
        boolean data=feedbackRepo.delete(id);

        if(data)
        {
            System.out.println("Repo delete() in service successful:" +data);
        }
        else
        {
            System.out.println("Repo delete() in service is not  successful:" +data);
        }
        return data;
    }
}
