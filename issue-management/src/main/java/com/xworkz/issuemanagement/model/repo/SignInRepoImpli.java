package com.xworkz.issuemanagement.model.repo;

import com.xworkz.issuemanagement.dto.SignUpDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;
@Repository
public class SignInRepoImpli implements SignInRepo
{
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    //sign-in : to generate random password and store in db
    @Override
    public SignUpDTO findByEmailAndPassword(String email, String password)
    {
        EntityManager entityManager= entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction= entityManager.getTransaction();

        try
        {
            entityTransaction.begin();
            Query query= entityManager.createQuery("SELECT  dto FROM SignUpDTO dto WHERE dto.email=:email AND dto.password=:password");
            query.setParameter("email",email);
            query.setParameter("password",password);
            SignUpDTO signUpDTO = (SignUpDTO) query.getSingleResult();
            System.out.println("Email and password saved in db:"+signUpDTO);
            entityTransaction.commit();
            return signUpDTO;
        }
        catch (NonUniqueResultException e)
        {
            return null;
        }
        catch (PersistenceException e)
        {
            System.out.println("Transaction is closed:");
            e.printStackTrace();
            entityTransaction.rollback();
        }
        finally {
            entityManager.close();
        }
        return null;
    }

    @Override
    public SignUpDTO findByEmail(String email)
    {
        EntityManager entityManager=entityManagerFactory.createEntityManager();

        try
        {
            Query query = entityManager.createQuery("SELECT dto from SignUpDTO dto where dto.email=:email");
            query.setParameter("email", email);


            List<SignUpDTO> resultList = query.getResultList();

            if (resultList.isEmpty())
            {
                return null;
            }
            else if (resultList.size() == 1)
                return resultList.get(0);
            else
            {
                throw new NonUniqueResultException("Multiple results found for email: " + email);
            }
        }
        catch (NonUniqueResultException nonUniqueResultException)
        {
            return null;
        }

        catch (NoResultException resultException)
        {
            return null;
        }
        finally
        {
            entityManager.close();
        }


    }

    //to save and update failed attempts in db
    @Override
    public boolean updateFailedAttempts(SignUpDTO signUpDTO)
    {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction=entityManager.getTransaction();
        try
        {
            entityTransaction.begin();
            entityManager.merge(signUpDTO);
            entityTransaction.commit();
        }
        catch (PersistenceException e)
        {
            e.printStackTrace();
            entityTransaction.rollback();
            return false;

        }
        finally
        {
            entityManager.close();
        }

        return true;
    }

}
