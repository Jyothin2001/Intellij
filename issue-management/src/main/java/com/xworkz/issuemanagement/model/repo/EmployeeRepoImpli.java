package com.xworkz.issuemanagement.model.repo;

import com.xworkz.issuemanagement.constants.Status;
import com.xworkz.issuemanagement.dto.DepartmentDTO;
import com.xworkz.issuemanagement.dto.EmployeeDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


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
            Query query1 = entityManager.createQuery("SELECT d FROM DepartmentDTO d WHERE d.departmentName = :employeeDeptName");
            query1.setParameter("employeeDeptName", employeeDeptName);

            // Using getSingleResult() to handle the scenario where no result is found
            DepartmentDTO data = (DepartmentDTO) query1.getSingleResult();
            log.info("DepartmentName from employeeDeptName: " + data);

            return data;

        } catch (NoResultException noResultException) {
            log.info("No result found for department name: " + employeeDeptName);
            // Handle the case where no result is found (e.g., return null or an empty result)
            return null;

        } catch (PersistenceException persistenceException) {
            log.error("Persistence exception occurred: ", persistenceException);
            // Handle other persistence exceptions if needed
            return null;

        } finally {
            entityManager.close();
            log.info("Connection closed");
        }
    }

    @Override
    public EmployeeDTO findByEmail(String emailId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            //System.out.println("Existing email:" +emailId);
            log.info("Existing email : {}", emailId);

            String query = "SELECT e FROM EmployeeDTO e WHERE e.email =:emailId and e.status = 'ACTIVE'";

            Query query1 = entityManager.createQuery(query);
            query1.setParameter("emailId", emailId);
            EmployeeDTO employeeDTO = (EmployeeDTO) query1.getSingleResult();

            log.info("EmployeeDTO data :{}", employeeDTO);


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

    @Override
    public void updateEmployeeDetails(EmployeeDTO employeeDTO) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();
            // Save or update
            employeeDTO.setStatus(Status.ACTIVE);
            entityManager.merge(employeeDTO);
            log.info("Updating employee details in Repo: {}", employeeDTO);
            entityTransaction.commit();

        } catch (RollbackException e) {
            log.error("Transaction rollback error while updating employee details: {}", e.getMessage());
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
        } catch (PersistenceException e) {
            log.error("Persistence error while updating employee details: {}", e.getMessage());
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
        } catch (Exception e) {
            log.error("Unexpected error while updating employee details: {}", e.getMessage());
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Long countNoOfActiveEmployeeByDeptId(int departmentId) {
        log.info("countByDeptIDAndActiveEmployee in EmployeeRepoImply");
        EntityManager entityManager = null;
        Long count = 0L;

        try {
            entityManager = entityManagerFactory.createEntityManager();
            Query query = entityManager.createQuery("SELECT COUNT(e) FROM EmployeeDTO e WHERE e.departmentDTO.id = :departmentId AND e.status = 'ACTIVE'");
            query.setParameter("departmentId", departmentId);

            // Execute the query and retrieve the count
            count = (Long) query.getSingleResult();

        } catch (Exception e) {
            // Log the exception and handle it appropriately
            log.error("Error occurred while counting active employees for departmentId {}: {}", departmentId, e.getMessage());
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close(); // Ensure the EntityManager is closed to prevent resource leaks
            }
        }
        return count;
    }

    @Override
    public DepartmentDTO findByDepartmentId(int departmentId) {
        EntityManager entityManager = null;
        DepartmentDTO departmentDTO = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            Query query = entityManager.createQuery("SELECT d FROM DepartmentDTO d WHERE d.id = :departmentId");
            query.setParameter("departmentId", departmentId);
            departmentDTO = (DepartmentDTO) query.getSingleResult();
        } catch (NoResultException e) {
            // Handle case where no result is found
            log.error("No department found with ID: {}", departmentId);
        } catch (Exception e) {
            // Handle other exceptions
            log.error("Error occurred while fetching department with ID: {}", departmentId, e);
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return departmentDTO;
    }

    // Other methods as needed

    @Override
    public DepartmentDTO updateDepartmentDTO(DepartmentDTO departmentDTO) {
        log.info("updateDepartmentDTO in EmployeeRepoImpl");

        EntityManager entityManager = null;
        EntityTransaction entityTransaction = null;
        DepartmentDTO updatedDepartment = null;

        try {
            // Initialize EntityManager and Transaction
            entityManager = entityManagerFactory.createEntityManager();
            entityTransaction = entityManager.getTransaction();

            // Begin transaction
            entityTransaction.begin();

            // Merge the departmentDTO entity
            updatedDepartment = entityManager.merge(departmentDTO);

            // Commit the transaction
            entityTransaction.commit();

        } catch (IllegalArgumentException e) {
            // Handle case where the entity is not a valid entity or is detached
            if (entityTransaction != null && entityTransaction.isActive()) {
                entityTransaction.rollback(); // Rollback the transaction
            }
            log.error("IllegalArgumentException occurred while updating DepartmentDTO: {}", e.getMessage());

        } catch (PersistenceException e) {
            // Handle general persistence exceptions
            if (entityTransaction != null && entityTransaction.isActive()) {
                entityTransaction.rollback(); // Rollback the transaction
            }
            log.error("PersistenceException occurred while updating DepartmentDTO: {}", e.getMessage());

        } catch (Exception e) {
            // Handle any other unexpected exceptions
            if (entityTransaction != null && entityTransaction.isActive()) {
                entityTransaction.rollback(); // Rollback the transaction
            }
            log.error("Exception occurred while updating DepartmentDTO: {}", e.getMessage());

        } finally {
            // Ensure EntityManager is closed
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }

        return updatedDepartment;
    }


}





