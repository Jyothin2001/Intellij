package com.xworkz.issuemanagement.model.repo;

import com.xworkz.issuemanagement.dto.DepartmentDTO;
import com.xworkz.issuemanagement.dto.RegDeptAdminDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

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
        catch (PersistenceException persistenceException)
        {
            log.error("error while saving RegDeptAdminDTO:",persistenceException);
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
    public RegDeptAdminDTO getEmailAndPassword(String email, String password,String departmentName) {

       EntityManager entityManager= entityManagerFactory.createEntityManager();
       try {

           Query query = entityManager.createQuery("SELECT e FROM RegDeptAdminDTO e WHERE e.email=:email AND e.password=:password AND departmentName=:departmentName");
           query.setParameter("email", email);
           query.setParameter("password", password);
           query.setParameter("departmentName",departmentName);
          RegDeptAdminDTO emailAndPassword= (RegDeptAdminDTO) query.getSingleResult();
          log.info("emailAndPassword data from database:{}",emailAndPassword);

           return emailAndPassword;
       }
       catch (PersistenceException e)
       {
           log.error("error while fetching email and password matching record",e);
            return null;
       }
       finally {
           entityManager.close();
           log.info("Connection closed");
       }
    }

    @Override
    public RegDeptAdminDTO getEmail(String email) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            log.info(" Existing Email : {}" , email);
            String query = "SELECT e FROM RegDeptAdminDTO e WHERE e.email=:email";
            Query query1 = entityManager.createQuery(query);
            query1.setParameter("email", email);

            RegDeptAdminDTO data = (RegDeptAdminDTO) query1.getSingleResult();
            System.out.println("email :" + data);
            return data;

        } catch (NoResultException e) {
            log.info("No entity found for query");
        } catch (PersistenceException e) {
            log.error("error while fetching email record",e);
        } finally {
            entityManager.close();
            log.info("Connection closed");
        }
        return null;
    }

    @Override
    public boolean update(RegDeptAdminDTO regDeptAdminDTO) {
        log.info("update method running in AdminRepoImpl..");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            entityManager.merge(regDeptAdminDTO);
            entityTransaction.commit();
            log.error("update successful");
            return true;
        }
        catch (PersistenceException e)
        {
            log.error("error while update record",e);
     if(entityTransaction.isActive())
     {
         entityTransaction.rollback();
     }
        }
        finally {
            entityManager.close();
            log.error("connection closed:");
        }

        return false;
    }
    @Override
    public DepartmentDTO findByDepartmentType(String regDepartmentName) {
        log.info("findByDepartmentType method running AdminRepoImpl..");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {

            Query query1 = entityManager.createQuery("SELECT d FROM DepartmentDTO d where departmentName=:regDepartmentName");
            query1.setParameter("regDepartmentName", regDepartmentName);
            DepartmentDTO data = (DepartmentDTO) query1.getSingleResult();
            log.info("DepartmentName from regDeptAdmin : " + data);

            return data;

        } catch (PersistenceException persistenceException) {
            persistenceException.printStackTrace();
        } finally {
            entityManager.close();
            System.out.println("Connection closed");
        }
        return null;
    }

}
