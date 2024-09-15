package com.xworkz.issuemanagement.model.repo;

import com.xworkz.issuemanagement.dto.EmployeeDTO;
import com.xworkz.issuemanagement.dto.RegDeptAdminDTO;
import com.xworkz.issuemanagement.dto.SignUpDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.Optional;

@Repository
@Slf4j
public class AjaxEmailAndNumberRepoImpli implements AjaxEmailAndNumberRepo
{
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public SignUpDTO existsByEmail(String email)
    {
        EntityManager entityManager= entityManagerFactory.createEntityManager();

        try
        {
            Query query=entityManager.createQuery("Select dto from SignUpDTO dto where dto.email=:email");
            query.setParameter("email",email);
            return (SignUpDTO) query.getSingleResult();

        }
        catch (NoResultException noResultException)
        {
            return null;
        }
        catch(PersistenceException e)
        {
            e.printStackTrace();
        }
        finally
        {
            entityManager.close();
        }
        return null;
    }

    @Override
    public SignUpDTO existsByNumber(Long contactNumber)
    {
       EntityManager entityManager= entityManagerFactory.createEntityManager();

      try {
          Query query=entityManager.createQuery("Select dto from SignUpDTO dto where dto.contactNumber=:number");
         query.setParameter("number", contactNumber);
          return (SignUpDTO) query.getSingleResult();

      }
      catch (NoResultException noResultException)
      {
          return null;
      }
      catch (PersistenceException e)
      {
          e.printStackTrace();
      }
      finally {
          entityManager.close();
      }
        return null;
    }

    @Override
    public RegDeptAdminDTO existsBySubAdminEmail(String email) {
        EntityManager entityManager= entityManagerFactory.createEntityManager();

        try
        {
            Query query=entityManager.createQuery("Select dto from RegDeptAdminDTO dto where dto.email=:email");
            query.setParameter("email",email);
            return (RegDeptAdminDTO) query.getSingleResult();

        }
        catch (NoResultException noResultException)
        {
            return null;
        }
        catch(PersistenceException e)
        {
            e.printStackTrace();
        }
        finally
        {
            entityManager.close();
        }
        return null;
    }

    @Override
    public RegDeptAdminDTO existsBySubAdminNumber(Long contactNumber) {
        EntityManager entityManager= entityManagerFactory.createEntityManager();

        try {
            Query query=entityManager.createQuery("Select dto from RegDeptAdminDTO dto where dto.contactNumber=:number");
            query.setParameter("number", contactNumber);
            return (RegDeptAdminDTO) query.getSingleResult();

        }
        catch (NoResultException noResultException)
        {
            return null;
        }
        catch (PersistenceException e)
        {
            e.printStackTrace();
        }
        finally {
            entityManager.close();
        }
        return null;
    }


    //ajax for employee
    @Override
    public Optional<EmployeeDTO> findByEmail(String email) {
        EntityManager entityManager= entityManagerFactory.createEntityManager();
        try {
            Query query = entityManager.createQuery("SELECT e FROM EmployeeDTO e WHERE e.email = :email");
            query.setParameter("email", email);
            EmployeeDTO result = (EmployeeDTO) query.getSingleResult();
            log.info("checking email from EmployeeDTO in AjaxEmailAndNumberRepo");
            return Optional.of(result);
        } catch (NoResultException e) {
            return Optional.empty();
        } catch (PersistenceException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Optional<EmployeeDTO> findByContactNumber(Long contactNumber) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Query query = entityManager.createQuery("SELECT e FROM EmployeeDTO e WHERE e.contactNumber = :contactNumber");
            query.setParameter("contactNumber", contactNumber);
            EmployeeDTO result = (EmployeeDTO) query.getSingleResult();
            log.info("Checking number from EmployeeDTO in AjaxEmailAndNumberRepo");
            return Optional.of(result);
        } catch (NoResultException e) {
            log.error("No result found for contact number: " + contactNumber);
            return Optional.empty();
        } catch (NonUniqueResultException e) {
            log.error("Multiple results found for contact number: " + contactNumber);
            // Handle this case according to your business logic.
            // You may decide to return an Optional.empty(), return the first result, or throw an exception.
            return Optional.empty();  // Or handle it differently
        } catch (PersistenceException e) {
            log.error("Persistence error while fetching by contact number", e);
            return Optional.empty();
        } finally {
            entityManager.close();
        }
    }



}
