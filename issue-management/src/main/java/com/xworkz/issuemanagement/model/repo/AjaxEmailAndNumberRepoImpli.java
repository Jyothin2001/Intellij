package com.xworkz.issuemanagement.model.repo;

import com.xworkz.issuemanagement.dto.RegDeptAdminDTO;
import com.xworkz.issuemanagement.dto.SignUpDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Repository
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
}
