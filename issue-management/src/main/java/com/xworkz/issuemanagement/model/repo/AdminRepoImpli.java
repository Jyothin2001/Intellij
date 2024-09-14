package com.xworkz.issuemanagement.model.repo;

import com.xworkz.issuemanagement.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
public class AdminRepoImpli implements AdminRepo
{
     @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public AdminDTO findByEmailAndPassword(String email, String password)
    {
        System.out.println("findByEmailAndPassword  method in AdminRepo");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {

            Query query = entityManager.createQuery("SELECT a FROM AdminDTO a where a.email=:emailId AND a.password=:adminPassword");
            query.setParameter("emailId", email);
            query.setParameter("adminPassword", password);

            AdminDTO data = (AdminDTO) query.getSingleResult();
            System.out.println("AdminDTO Data: " + data);
            return data;

        }
        catch (NoResultException e) {
            System.out.println("No result found");
        }
            catch (PersistenceException e)
            {
            log.error("while Fetching complaintType error:", e);
        } finally {
            entityManager.close();
            log.info("Admin connection closed");
        }

        return null;
    }

    @Override
    public List<SignUpDTO> findByUserId(SignUpDTO signUpDTO) {
          log.info("findById method in AdminRepoImpl...");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {

            Query query = entityManager.createQuery("SELECT d FROM  SignUpDTO d");
            List<SignUpDTO> data = query.getResultList();
            log.info("User Data:{}" , data);
            return data;

        } catch (PersistenceException e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
            System.out.println("Connection closed");
        }

        return Collections.emptyList();

    }

    @Override
    public List<ComplaintRaiseDTO> findByComplaintRaiseId(ComplaintRaiseDTO complaintRaiseDTO) {

        log.info("findById method in AdminRepoImpl");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            Query query = entityManager.createQuery("SELECT c FROM ComplaintRaiseDTO c ORDER BY c.complaintId DESC");
            List<ComplaintRaiseDTO> data = query.getResultList();
            System.out.println("Complaint Raise Data:" + data);

            return data;
        } catch (PersistenceException e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
            System.out.println("connection closed");
        }

        return Collections.emptyList();

    }

    @Override
    public List<ComplaintRaiseDTO> searchByComplaintTypeAndCity(String complaintType, String city) {
        log.info("searchByComplaintType(AND) method running in AdminRepoImpl..");

        EntityManager entityManager=entityManagerFactory.createEntityManager();

        try
        {
            Query query=entityManager.createQuery("select c from ComplaintRaiseDTO c where c.city=:city AND c.complaintType=:complaintType");
            query.setParameter("city",city);
            query.setParameter("complaintType",complaintType);
           List<ComplaintRaiseDTO> list= query.getResultList();
           log.info("CompalintDTO data by type and city:{}",list);
           return  list;

        }
        catch (PersistenceException e)
        {
            log.info("error while search by type and city:{}",e);
        }
        finally {
            log.info("close connection");
            entityManager.close();
        }
        return Collections.emptyList();
    }

    @Override
    public List<ComplaintRaiseDTO> searchByComplaintTypeOrCity(String complaintType, String city) {
        log.info("searchByComplaintType (OR) method running in AdminRepoImpl..");

        EntityManager entityManager=entityManagerFactory.createEntityManager();

        try
        {
            Query query=entityManager.createQuery("select c from ComplaintRaiseDTO c where c.city=:city OR c.complaintType=:complaintType");
            query.setParameter("city",city);
            query.setParameter("complaintType",complaintType);
            List<ComplaintRaiseDTO> list= query.getResultList();
            log.info("CompalintDTO data by type and city:{}",list);
            return  list;

        }
        catch (PersistenceException e)
        {
            log.info("error while search by type OR city:{}",e);
        }
        finally {
            log.info("close connection");
            entityManager.close();
        }
        return Collections.emptyList();
    }

    @Override
    public List<ComplaintRaiseDTO> findAllCities() {

        EntityManager entityManager=entityManagerFactory.createEntityManager();

        try
        {
            Query query=entityManager.createQuery("select c.city from ComplaintRaiseDTO c ");
            List<ComplaintRaiseDTO> list= query.getResultList();
            log.info("CompalintDTO city data by type and city:{}",list);
            return  list;

        }
        catch (PersistenceException e)
        {
            log.info("error while search by type OR city:{}",e);
        }
        finally {
            log.info("close connection");
            entityManager.close();
        }



        return Collections.emptyList();
    }

    @Override
    public String getAdminName(String email, String password) {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        try
        {
            Query query=entityManager.createQuery("SELECT a.username FROM AdminDTO a WHERE a.email = :email AND a.password = :password");
            query.setParameter("email",email);
            query.setParameter("password",password);
            String list= (String) query.getSingleResult();
            log.info("AdminDTO username:{}",list);
            return  list;

        }
        catch (PersistenceException e)
        {
            log.info("error while fetching admin username : " ,e);

        }
        finally {
            log.info(" connection close");
            entityManager.close();
        }

        return null;
    }

    //String cannot be cast to com.xworkz.issuemanagement.dto.AdminDTO if you give AdminDTO only

    @Override
    public DepartmentDTO saveDepartment(DepartmentDTO departmentDTO) {
        log.info("saveDepartment method running in AdminRepoImpl..");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();
            entityManager.persist(departmentDTO);
            entityTransaction.commit();

        } catch (PersistenceException persistenceException) {
            persistenceException.printStackTrace();
        } finally {
            entityManager.close();
            log.info("Connection closed");
        }
        return departmentDTO;

    }

    @Override
    public Optional<DepartmentDTO> existsByDepartmentName(String departmentName) {
        EntityManager entityManager= entityManagerFactory.createEntityManager();
        try {
            Query query = entityManager.createQuery("SELECT e FROM DepartmentDTO e WHERE e.departmentName = :departmentName");
            query.setParameter("departmentName", departmentName);
            DepartmentDTO result = (DepartmentDTO) query.getSingleResult();
            log.info("checking departmentName from DepartmentDTO in AjaxEmailAndNumberRepo");
            return Optional.of(result);
        } catch (NoResultException e) {
            return Optional.empty();
        } catch (PersistenceException e) {
            e.printStackTrace();
            return Optional.empty();
        }

    }


    @Override
    public List<DepartmentDTO> findByDepartmentName() {

        log.info("findAll method running AdminRepoImpl..");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {

            Query query1 = entityManager.createQuery("SELECT d FROM DepartmentDTO d");
            //query1.setParameter("departmentName", departmentName);
            List<DepartmentDTO> data = query1.getResultList();
            log.info("DepartmentName : " + data);

            return data;

        } catch (PersistenceException persistenceException) {
            persistenceException.printStackTrace();
        } finally {
            entityManager.close();
            System.out.println("Connection closed");
        }
        return null;
        //return Collections.emptyList();
    }

    @Override
    public boolean updateStatusAndDepartmentId(int complaintId, int departmentId, String status)
    {
        log.info("updateStatusAndDepartmentId method running in RaiseComplaintRepoImpl..");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            String updateQuery = "UPDATE ComplaintRaiseDTO r SET r.departmentDTO.id = :departmentId, r.status = :status WHERE r.complaintId = :complaintId";
            Query query = entityManager.createQuery(updateQuery);
            query.setParameter("departmentId", departmentId);
            query.setParameter("status", status);
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
    public List<RegDeptAdminDTO> getAllSubAdminDetails() {
        log.info("getAllSubAdminDetails method running AdminRepoImpl..");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {

            Query query1 = entityManager.createQuery("SELECT d FROM RegDeptAdminDTO d");
            //query1.setParameter("departmentName", departmentName);
            List<RegDeptAdminDTO> data = query1.getResultList();
            log.info("All SubAdminDetails : " + data);

            return data;

        } catch (PersistenceException persistenceException) {
            persistenceException.printStackTrace();
        } finally {
            entityManager.close();
            System.out.println("Connection closed");
        }

        return Collections.emptyList();
    }


}
