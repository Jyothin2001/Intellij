package com.xworkz.issuemanagement.model.repo;

import com.xworkz.issuemanagement.dto.RegDeptAdminDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

@Repository
@Slf4j
public class RegDeptAdminRepoImpli implements RegDeptAdminRepo{
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public boolean saveRegDeptAdmin(RegDeptAdminDTO regDeptAdminDTO) {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction=entityManager.getTransaction();
        try
        {
        entityTransaction.begin();
        entityManager.persist(regDeptAdminDTO);
        entityTransaction.commit();
            log.info("saveRegDeptAdmin() in RegDeptAdminRepoImpli successful:");
        return true;

        }
        catch (PersistenceException e)
        {
            log.error("error while saving RegDeptAdminDTO:",e);
            if(entityTransaction.isActive())
            {
                entityTransaction.rollback();
            }
        }
        finally {
            entityManager.close();
        }

        return false;
    }

    @Override
    public RegDeptAdminDTO getEmailAndPassword(String email, String password) {

        return null;
    }
}
