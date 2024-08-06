package com.xworkz.issuemanagement.model.repo;

import com.xworkz.issuemanagement.dto.SignUpDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Repository
@Slf4j
public class SignUpRepoImpli implements SignUpRepo {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public SignUpRepoImpli()
    {
        log.info("IssuemanagmentRepoImpli constructor:");
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

          // Persist the entity object
          entityManager.persist(signUpDTO);

          // Commit the transaction
          entityTransaction.commit();
      } catch (PersistenceException e) {
          e.printStackTrace();

          // Rollback in case of an exception
          if (entityTransaction.isActive()) {
              entityTransaction.rollback();
          }
          return false;
      } finally {
          // Always close the EntityManager
          entityManager.close();
      }
        return true;
    }
}
