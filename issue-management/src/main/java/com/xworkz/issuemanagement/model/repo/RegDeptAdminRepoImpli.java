package com.xworkz.issuemanagement.model.repo;

import com.xworkz.issuemanagement.constants.Status;
import com.xworkz.issuemanagement.dto.ComplaintRaiseDTO;
import com.xworkz.issuemanagement.dto.DepartmentDTO;
import com.xworkz.issuemanagement.dto.EmployeeDTO;
import com.xworkz.issuemanagement.dto.RegDeptAdminDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
@Slf4j
public class RegDeptAdminRepoImpli implements RegDeptAdminRepo{
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public boolean saveRegDeptAdmin(RegDeptAdminDTO regDeptAdminDTO) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = null;

        try {
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();

            // If regDeptAdminDTO is detached, merge it before persisting
            if (!entityManager.contains(regDeptAdminDTO)) {
                regDeptAdminDTO = entityManager.merge(regDeptAdminDTO);
            }

            entityManager.persist(regDeptAdminDTO);
            entityTransaction.commit();
            log.info("saveRegDeptAdmin() in RegDeptAdminRepoImpl successful");
            return true;

        } catch (PersistenceException persistenceException) {
            log.error("Error while saving RegDeptAdminDTO: ", persistenceException);
            if (entityTransaction != null && entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }

        return false;
    }

//    @Override
//    public boolean saveRegDeptAdmin(RegDeptAdminDTO regDeptAdminDTO) {
//        EntityManager entityManager=entityManagerFactory.createEntityManager();
//        EntityTransaction entityTransaction=entityManager.getTransaction();
//        try
//        {
//        entityTransaction.begin();
//        entityManager.persist(regDeptAdminDTO);
//        entityTransaction.commit();
//        log.info("saveRegDeptAdmin() in RegDeptAdminRepoImpli successful:");
//        return true;
//
//        }
//        catch (PersistenceException persistenceException)
//        {
//            log.error("error while saving RegDeptAdminDTO:",persistenceException);
//            if(entityTransaction.isActive())
//            {
//                entityTransaction.rollback();
//            }
//        }
//        finally {
//            entityManager.close();
//        }
//
//        return false;
//    }

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
        log.info("findByDepartmentType method running regDeptRepoImpl..");
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
            log.info("Connection closed");
        }
        return null;
    }

    @Override
    public List<ComplaintRaiseDTO> deptAdminView(String departmentName)
    {
        log.info("deptAdminView method running regDeptRepoImpl..");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {

            Query query1 = entityManager.createQuery("SELECT d FROM ComplaintRaiseDTO d where d.complaintType=:regDepartmentName");
            query1.setParameter("regDepartmentName", departmentName);
            List<ComplaintRaiseDTO> data =  query1.getResultList();
            log.info("DepartmentName from regDeptAdmin {}: " , data);

            return data;

        } catch (PersistenceException persistenceException) {
            persistenceException.printStackTrace();
        } finally {
            entityManager.close();
            log.info("Connection Closed");
        }
        return null;
    }

    @Override
    public void deactivateStatus(int employeeId, Status status)
    {
       EntityManager entityManager= entityManagerFactory.createEntityManager();
       EntityTransaction entityTransaction=entityManager.getTransaction();
       try
       {
           entityTransaction.begin();
           Query query=entityManager.createQuery("update EmployeeDTO e SET e.status=:status where e.employee_id=:employeeId");
           query.setParameter("status",status);
           query.setParameter("employeeId",employeeId);
           int a=query.executeUpdate();
           log.info("update deactivate status of employee in RegDeptAdminRepoImply{}: " , a);
           entityTransaction.commit();

       }catch (Exception e)
       {
           if (entityTransaction.isActive()) {
               entityTransaction.rollback();  // Rollback in case of an error
           }
           e.printStackTrace();  // Optionally log the exception
       }
       finally
       {
           if (entityManager != null)
           {
               entityManager.close();  // Always close the EntityManager
           }
       }

    }

    @Override
    public List<EmployeeDTO> getAllEmployeeNames(String regDepartmentName)
    {
        log.info("getAllEmployeeNames method running regDeptRepoImpl..");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {

            Query query1 = entityManager.createQuery("SELECT e FROM EmployeeDTO e where e.departmentName=:regDepartmentName AND e.status = 'ACTIVE'");
            query1.setParameter("regDepartmentName", regDepartmentName);
            List<EmployeeDTO> data = query1.getResultList();
            log.info("getAllEmployeeNames in regDeptAdminRepoImply : " + data);

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
    public boolean updateStatusAndEmployeeId(int complaintId, int employeeId) {
        log.info("updateStatusAndEmployeeId method running in RaiseComplaintRepoImpl..");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            String updateQuery = "UPDATE ComplaintRaiseDTO r SET r.employeeDTO.id =:employeeId WHERE r.complaintId = :complaintId";
            Query query = entityManager.createQuery(updateQuery);
            query.setParameter("employeeId", employeeId);
            //query.setParameter("status", status);
            query.setParameter("complaintId", complaintId);

            int data = query.executeUpdate();
            log.info("data :{}",data);
            transaction.commit();

        } catch (Exception e)
        {
            if (transaction.isActive())
            {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return true;

    }




    @Override
    public boolean isEmployeeAllocated(int employeeId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            // Query to count the number of complaints associated with the employee
            Query query = entityManager.createQuery("SELECT COUNT(e) FROM ComplaintRaiseDTO e WHERE e.employeeDTO.employee_id = :employee_id");
            query.setParameter("employee_id", employeeId);
            Long count = (Long) query.getSingleResult();
           log.error("count:{}  ",count);
            // Return true if count is greater than 0 (employee is allocated)
            // Return false if count is 0 (employee is not allocated)
            return count > 0;
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public boolean updateComplaintForDeactivatedEmployee(ComplaintRaiseDTO complaintRaiseDTO) {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction=entityManager.getTransaction();
        try
        {
            entityTransaction.begin();
            entityManager.merge(complaintRaiseDTO);
            entityTransaction.commit();
            log.info(" update successful complaintDTO for InactiveEmployee in RegDeptAdminRepoImply: ");
            return true;

        }
        catch (PersistenceException e)
        {
            log.error("error while update complaintDTO for InactiveEmployee: ",e);
        }
        return false;
    }


}
