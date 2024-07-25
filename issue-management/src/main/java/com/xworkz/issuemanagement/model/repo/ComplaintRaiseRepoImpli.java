package com.xworkz.issuemanagement.model.repo;

import com.xworkz.issuemanagement.dto.ComplaintRaiseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
public class ComplaintRaiseRepoImpli implements ComplaintRaiseRepo
{
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public boolean saveComplaintRaiseDetails(ComplaintRaiseDTO complaintRaiseDTO) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        boolean isSuccess = false;

        try {
            entityTransaction.begin();
            entityManager.persist(complaintRaiseDTO);
            entityTransaction.commit();
            isSuccess = true;
        } catch (PersistenceException e) {
            log.error("Exception while saving complaintRaise data:", e);
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
                log.info("Transaction rolled back due to an error.");
            }
        } finally {
            entityManager.close();
            log.info("EntityManager connection closed.");
        }

        return isSuccess;
    }
    //view
    @Override
    public List<ComplaintRaiseDTO> findByComplaintsByUserId(int userId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
           Query query = entityManager.createQuery("SELECT r FROM ComplaintRaiseDTO r WHERE r.signUpDTO.id =:userId");

            query.setParameter("userId", userId);
            List<ComplaintRaiseDTO> listOfComplaintDTO=query.getResultList();
            if(listOfComplaintDTO!=null)
            {
                log.info("listOfComplaintDTO for view complaintRaise: no. of  complaints:{} and userId: {} ",listOfComplaintDTO.size(),userId);
                return listOfComplaintDTO;
            }

        }
        catch (PersistenceException e)
        {
            log.error("error while fetching complaint data:",e);
        }
        finally
        {
            entityManager.close();
        }
        return null;

    }

    //to set foreign key
    //if you don't use TypedQuery, you'd need to cast the results, which is less safe and less readable:
    @Override
    public Optional<ComplaintRaiseDTO> findByUserId(int userId)
    {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {

            TypedQuery<ComplaintRaiseDTO> query = entityManager.createQuery(
                    "SELECT i FROM ComplaintRaiseDTO i WHERE i.user_fk = :id", ComplaintRaiseDTO.class);
            query.setParameter("id", userId);
            return query.getResultList().stream().findFirst();
        } finally {
            entityManager.close();
        }

    }
/*Conversion to Stream: query.getResultList().stream() converts the list of results into a stream.
Find First Element: findFirst() retrieves the first element in the stream, if present.
Optional: If the list is empty, findFirst() returns Optional.empty().
If it contains elements, it returns the first element wrapped in an Optional.
*/
    //edit
    @Override
    public Optional<ComplaintRaiseDTO> findByComplaintId(int complaintId) {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        //warning unchecked exaception so use typedQuery ,there is no need to casting
        try {
            TypedQuery<ComplaintRaiseDTO> query = entityManager.createQuery("SELECT c FROM ComplaintRaiseDTO c WHERE c.complaintId=:complaintId",ComplaintRaiseDTO.class);
            query.setParameter("complaintId", complaintId);

           return query.getResultList().stream().findFirst();
        }
        catch (PersistenceException e)
        {
            log.error("error while fetching complaint data:",e);
        }
        finally {
              entityManager.close();
        }
        return null;
    }

    @Override
    public ComplaintRaiseDTO updateEditedComplaints(ComplaintRaiseDTO complaintRaiseDTO) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try
        {
            entityTransaction.begin();
            entityManager.merge(complaintRaiseDTO);
            entityTransaction.commit();

            return complaintRaiseDTO;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            entityTransaction.rollback();
        }
        finally {
            entityManager.close();
            log.info("updateRaiseComplaintUserDetails connection closed");
        }

        return null;
    }


}
