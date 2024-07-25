package com.xworkz.issuemanagement.model.repo;

import com.xworkz.issuemanagement.dto.ComplaintRaiseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;

@Repository
@Slf4j
public class SearchComplaintRaiseRepoImpli implements SearchComplaintRaiseRepo {


    @Autowired
    private EntityManagerFactory entityManagerFactory;



    @Override
    public List<ComplaintRaiseDTO> getByComplaintType(ComplaintRaiseDTO complaintType) {

        EntityManager entityManager=entityManagerFactory.createEntityManager();
        try
        {
         Query query= entityManager.createQuery("SELECT t from ComplaintRaiseDTO t WHERE t.complaintType=:complaintType");
         query.setParameter("complaintType",complaintType);
         List<ComplaintRaiseDTO> list=query.getResultList();
         if(list!=null)
         {
             log.info("ComplaintType list:{} ",list);
             return list;
         }

        }
        catch (PersistenceException e)
        {
            log.error("while Fetching complaintType error:",e);
        }
        finally {
            entityManager.close();
        }



        return null;
    }

    @Override
    public List<ComplaintRaiseDTO> getByComplaintRaiseArea(ComplaintRaiseDTO complaintRaiseArea) {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        try
        {
            Query query= entityManager.createQuery("SELECT t from ComplaintRaiseDTO t WHERE t.area=:complaintRaiseArea");
            query.setParameter("complaintRaiseArea",complaintRaiseArea);
            List<ComplaintRaiseDTO> list=query.getResultList();
            if(list!=null)
            {
                log.info("ComplaintRaise area list:{} ",list);
                return list;
            }

        }
        catch (PersistenceException e)
        {
            log.error("while Fetching complaintRaiseArea error:",e);
        }
        finally {
            entityManager.close();
        }



        return null;

    }
}
