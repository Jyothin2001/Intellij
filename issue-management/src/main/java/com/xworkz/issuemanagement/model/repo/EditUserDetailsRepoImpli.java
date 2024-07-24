package com.xworkz.issuemanagement.model.repo;

import com.xworkz.issuemanagement.dto.ProfileImageDTO;
import com.xworkz.issuemanagement.dto.SignUpDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
@Slf4j
public class EditUserDetailsRepoImpli implements EditUserDetailsRepo {

    @Autowired
    private EntityManagerFactory entityManagerFactory;



    public SignUpDTO findByEmail(String email)
    {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            String query = "SELECT e From SignUpDTO e WHERE e.email=:email";
            Query query1 = entityManager.createQuery(query);
            query1.setParameter("email", email);

            // Use getResultList() instead of getSingleResult()
            List<SignUpDTO> results = query1.getResultList();

            if (!results.isEmpty()) {
                return results.get(0);// Return the first result if found
            }

        } catch (NoResultException e) {
            // Handle case where no results are found
            e.printStackTrace();
        } finally {
            entityManager.close();
        }

        return null;// Return null if no results found


    }
    @Override
    public void updateUserDetails(SignUpDTO signUpDTO)
    {
            EntityManager entityManager= entityManagerFactory.createEntityManager();

            EntityTransaction entityTransaction=entityManager.getTransaction();

            try
            {
                entityTransaction.begin();
                //save and update
                entityManager.merge(signUpDTO);
                log.info("In Repo SignUpDTO:{}",signUpDTO);
                entityTransaction.commit();
            }
            catch (PersistenceException e)
            {
                e.printStackTrace();
                if (entityTransaction.isActive()) {
                    entityTransaction.rollback();
                }

            }
            finally {
                entityManager.close();
            }
    }


}
