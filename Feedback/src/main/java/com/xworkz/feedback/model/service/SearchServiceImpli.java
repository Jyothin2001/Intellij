package com.xworkz.feedback.model.service;

import com.xworkz.feedback.dto.FeedbackDTO;
import com.xworkz.feedback.dto.SearchDTO;
import com.xworkz.feedback.model.repository.SearchRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class SearchServiceImpli implements SearchService
{
    @Autowired
    private SearchRepo searchRepo;

    @Override
    public List<FeedbackDTO> search(SearchDTO searchDTO)
    {
      List<FeedbackDTO> list=  this.searchRepo.search(searchDTO);
      if(!list.isEmpty())
      {
          System.out.println("searched result in dto: "+ list);
          return list;
      }
      else
      {
          System.out.println("searched result not in dto: "+ list);
      }
        return Collections.emptyList();
    }

    @Override
    public FeedbackDTO findById(int id)
    {

        System.out.println("findById() repo  in service");

     FeedbackDTO feedbackDTO= this.searchRepo.findById(id);

     if(feedbackDTO!=null)
     {
         return feedbackDTO;
     }
     else
     {
         System.out.println("Feedback form is not in serviceImpli");
     }


        return SearchService.super.findById(id);
    }
}
