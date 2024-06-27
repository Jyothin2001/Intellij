package com.xworkz.feedback.model.repository;

import com.xworkz.feedback.dto.FeedbackDTO;
import com.xworkz.feedback.dto.SearchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Repository
public class SearchRepoImpli implements SearchRepo
{
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public List<FeedbackDTO> search(SearchDTO searchDTO)
    {
        EntityManager entityManager=this.entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction= entityManager.getTransaction();
        entityTransaction.begin();
        try
        {

          String data="SELECT dto from FeedbackDTO dto where dto.rating=:rating1";
          Query query= entityManager.createQuery(data);
          query.setParameter("rating1",searchDTO.getRating());
          List<FeedbackDTO> list= query.getResultList();
          System.out.println("jhhhgggghhjjj"+list);


          entityTransaction.commit();
          return list;
        }
        catch (PersistenceException persistenceException)
        {
            persistenceException.printStackTrace();
            entityTransaction.rollback();
        }
        finally
        {
            entityManager.close();


        }

        return Collections.emptyList();
    }

    //edit
    @Override
    public FeedbackDTO findById(int id)
    {
        System.out.println("findById() in repo");

//        @Autowired
//        private EntityManagerFactory entityManagerFactory;

      EntityManager entityManager= entityManagerFactory.createEntityManager();
     EntityTransaction entityTransaction= entityManager.getTransaction();
     entityTransaction.begin();
     try
     {
      Query query = entityManager.createQuery("SELECT dto from FeedbackDTO dto where dto.id=:editId");
      query.setParameter("editId", id);
      FeedbackDTO feedbackDTO= (FeedbackDTO) query.getSingleResult();
      System.out.println("yyy"+feedbackDTO);

      entityTransaction.commit();

      return feedbackDTO;


     }
catch(PersistenceException e)
{
  e.printStackTrace();
  entityTransaction.rollback();
}
     finally
     {
         entityManager.close();
     }
        return SearchRepo.super.findById(id);
    }
}
