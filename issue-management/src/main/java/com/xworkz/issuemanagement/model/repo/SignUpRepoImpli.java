package com.xworkz.issuemanagement.model.repo;

import com.xworkz.issuemanagement.dto.SignUpDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Repository
public class SignUpRepoImpli implements SignUpRepo {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public SignUpRepoImpli()
    {
        System.out.println("IssuemanagmentRepoImpli constructor:");
    }


    //data save
    @Override
    public boolean saveAndValidate(SignUpDTO signUpDTO)
    {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction=entityManager.getTransaction();

      try
      {
          entityTransaction.begin();
          entityManager.persist(signUpDTO);
          entityTransaction.commit();

      }
      catch (PersistenceException e)
      {
          e.printStackTrace();
          entityTransaction.rollback();
      }
      finally
      {
          entityManager.close();
      }
        return true;
    }
}
