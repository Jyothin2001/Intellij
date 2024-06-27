package com.xworkz.feedback.model.repository;

import com.xworkz.feedback.dto.FeedbackDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Repository
public class FeedbackRepoImpli implements FeedbackRepo
{
   @Autowired
    private EntityManagerFactory entityManagerFactory;

    public FeedbackRepoImpli()
    {
        System.out.println("FeedbackRepoImpli constructor:");
    }




    @Override
    public boolean saveAndValidate(FeedbackDTO feedbackDTO)
    {
        System.out.println("saveAndValidate method:");

       EntityManager managerFactory =entityManagerFactory.createEntityManager();
       EntityTransaction entityTransaction=managerFactory.getTransaction();

        entityTransaction.begin();
       try
       {
           //to save user data into database

           //merge does both insert and update
           managerFactory.merge(feedbackDTO);

          entityTransaction.commit();
       }
      catch (PersistenceException e)
        {
            e.printStackTrace();
            entityTransaction.rollback();

         }
       finally
       {
           //we do not close "EntityManagerFactory" because it should be open once in application
           managerFactory.close();

       }

        return true;
    }

    @Override
    public boolean delete(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        try {
            Query query = entityManager.createQuery("DELETE FROM  FeedbackDTO dto where dto.id=:deleteId");
            query.setParameter("deleteId", id);
            int data = query.executeUpdate();
            System.out.println("deleted: " + data);
          entityTransaction.commit();

        } catch (PersistenceException e) {
            e.printStackTrace();
            entityTransaction.rollback();
        } finally {
            entityManager.close();
            System.out.println("connection closed!");

        }
        return true;

    }

}
