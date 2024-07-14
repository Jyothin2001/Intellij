package com.xworkz.issuemanagement.model.repo;

import com.xworkz.issuemanagement.dto.SignUpDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Repository
@Slf4j
public class ForgotPasswordRepoImpli implements ForgotPasswordRepo{

    @Autowired
    private EntityManagerFactory entityManagerFactory;


    @Override
    public SignUpDTO findByEmail(String email) {
       EntityManager entityManager= entityManagerFactory.createEntityManager();

       try {
           log.info("findByEmail method running  in ForgotPasswordRepoImpl .");
           Query query = entityManager.createQuery("SELECT dto from SignUpDTO dto where dto.email=:email");
           query.setParameter("email", email);
           log.info("email in repo:{}",query);
           SignUpDTO signUpDTO = (SignUpDTO) query.getSingleResult();
           return signUpDTO;
       }
       catch (PersistenceException e)
       {
           e.printStackTrace();
           return null;
       }
       finally {
           if(entityManager!=null)
           {
               entityManager.close();
           }
       }

    }

    @Override
    public void updatePassword(String email, String newPassword)
    {
       EntityManager entityManager= entityManagerFactory.createEntityManager();
      EntityTransaction entityTransaction= entityManager.getTransaction();

      try {
          entityTransaction.begin();
          Query query = entityManager.createQuery("UPDATE SignUpDTO dto SET dto.password=:newPassword WHERE dto.email=:email");
          query.setParameter("newPassword", newPassword);
          query.setParameter("email", email);
          query.executeUpdate();
          entityTransaction.commit();
      }
      catch (PersistenceException e)
      {
          e.printStackTrace();
          if(entityTransaction.isActive())
          {
              entityTransaction.rollback();
          }
      }
      finally {
          entityManager.close();
      }

    }
}
