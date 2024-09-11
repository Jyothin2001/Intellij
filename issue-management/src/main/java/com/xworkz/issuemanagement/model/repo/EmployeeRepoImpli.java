package com.xworkz.issuemanagement.model.repo;

import com.xworkz.issuemanagement.dto.DepartmentDTO;
import com.xworkz.issuemanagement.dto.EmployeeDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.*;

@Repository
@Slf4j
public class EmployeeRepoImpli implements EmployeeRepo{

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    //@Transactional
    @Override
    public boolean saveEmployeeDetails(EmployeeDTO employeeDTO)
    {
       EntityManager entityManager= entityManagerFactory.createEntityManager();
       EntityTransaction entityTransaction=entityManager.getTransaction();

       try
       {
           entityTransaction.begin();
           entityManager.merge(employeeDTO);
           entityTransaction.commit();
           return true;
       }
       catch (PersistenceException e)
       {
           log.error("error while saving Employee Details",e);
           if(entityTransaction.isActive()) {
               entityTransaction.rollback();
           }
       }
       finally
       {
           entityManager.close();

       }

        return false;
    }

    @Override
    public DepartmentDTO findByDepartmentType(String employeeDeptName) {
        log.info("findByDepartmentType method running AdminRepoImpl..");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {

            Query query1 = entityManager.createQuery("SELECT d FROM DepartmentDTO d where departmentName=:employeeDeptName");
            query1.setParameter("employeeDeptName", employeeDeptName);
            DepartmentDTO data = (DepartmentDTO) query1.getSingleResult();
            log.info("DepartmentName from employeeDeptName : " + data);

            return data;

        } catch (PersistenceException persistenceException) {
            persistenceException.printStackTrace();
        } finally {
            entityManager.close();
            log.info("Connection closed");
        }
        return null;
    }

    @Override
    public EmployeeDTO findByEmail(String emailId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            //System.out.println("Existing email:" +emailId);
            log.info("Existing email : {}", emailId);
            entityTransaction.begin();
            String query = "SELECT e FROM EmployeeDTO e WHERE e.email =:emailId";

            Query query1 = entityManager.createQuery(query);
            query1.setParameter("emailId", emailId);
            EmployeeDTO employeeDTO = (EmployeeDTO) query1.getSingleResult();

            log.info("EmployeeDTO data :{}", employeeDTO);
            entityTransaction.commit();

            return employeeDTO;


        } catch (NoResultException exception) {
            log.warn("No entity found for email: {}", emailId); // Log a warning instead of printing the stack trace

        } catch (PersistenceException persistenceException) {
            log.error("PersistenceException occurred while finding employee by email: {}", emailId, persistenceException);
            entityTransaction.rollback(); // Rollback transaction in case of persistence exception

        } finally {
            log.info("findByEmail method closed");
            if (entityTransaction.isActive()) {
                entityTransaction.rollback(); // Ensure transaction is rolled back if still active
            }
            entityManager.close();
        }
        return null;
    }


}
