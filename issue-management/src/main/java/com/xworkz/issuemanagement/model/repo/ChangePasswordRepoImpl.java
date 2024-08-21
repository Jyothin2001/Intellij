package com.xworkz.issuemanagement.model.repo;

import com.xworkz.issuemanagement.dto.RegDeptAdminDTO;
import com.xworkz.issuemanagement.dto.SignUpDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Repository
@Slf4j
public class ChangePasswordRepoImpl implements ChangePasswordRepo
{
    @Autowired
    private EntityManagerFactory entityManagerFactory;


    @Override
    public boolean emailExists(String email) {
        //check if email exists in database or not
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {

            Query query = entityManager.createQuery("SELECT dto FROM SignUpDTO dto where dto.email=:email");
            query.setParameter("email", email);

            SignUpDTO signUpDTO = (SignUpDTO) query.getSingleResult();
            return true;

        } catch (NoResultException e) {
            return false;
        }
        finally {
            entityManager.close();
        }
    }




    @Override
    public boolean verifyOldPassword(String email, String oldPassword) {
        //to verify  the old password

        EntityManager entityManager = entityManagerFactory.createEntityManager();

//        String query = "SELECT COUNT(e) FROM SignUpDTO e  WHERE email =:email AND e.password=:password";
//
//        Query query1 = entityManager.createQuery(query);
//        query1.setParameter("email", email);
//        query1.setParameter("password", oldPassword);
//        Long count = (Long) query1.getSingleResult();
//        System.out.println(count);

        try {
            Query query = entityManager.createQuery("SELECT dto FROM SignUpDTO dto WHERE dto.email=:email AND dto.password=:password ");
            query.setParameter("email", email);
            query.setParameter("password", oldPassword);
            SignUpDTO signUpDTO = (SignUpDTO) query.getSingleResult();
            log.info("Result list{}",signUpDTO);
            return true;
        }

        catch (NoResultException e)
        {
            // If no result is found, return false
            return false;
        }

        finally {
            entityManager.close();
        }


    }

    @Override
    //@Transactional
    public boolean updatePassword(String email, String newPassword) {

        // to update the reset password to password in database
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            //update table name set password=? where email=?;

            String query = "UPDATE SignUpDTO e SET e.password=:password WHERE e.email=:email ";

            Query query1 = entityManager.createQuery(query);
            query1.setParameter("password", newPassword);
            query1.setParameter("email", email);

            int executeData = query1.executeUpdate();
            log.info("executeData{}",executeData);
            entityTransaction.commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            entityTransaction.rollback();
            return false;
        } finally {

            entityManager.close();
        }
    }

    @Override
    public boolean verifyOldPasswordSubAdmin(String email, String oldPassword) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

//        String query = "SELECT COUNT(e) FROM SignUpDTO e  WHERE email =:email AND e.password=:password";
//
//        Query query1 = entityManager.createQuery(query);
//        query1.setParameter("email", email);
//        query1.setParameter("password", oldPassword);
//        Long count = (Long) query1.getSingleResult();
//        System.out.println(count);

        try {
            Query query = entityManager.createQuery("SELECT dto FROM RegDeptAdminDTO dto WHERE dto.email=:email AND dto.password=:password ");
            query.setParameter("email", email);
            query.setParameter("password", oldPassword);
            RegDeptAdminDTO regDeptAdminDTO = (RegDeptAdminDTO) query.getSingleResult();
            log.info("email and password matching record{}",regDeptAdminDTO);
            return true;
        }

        catch (NoResultException e)
        {
            // If no result is found, return false
            log.error("No result found",e);
            return false;
        }

        finally {
            entityManager.close();
        }

    }

    @Override
    public boolean updatePasswordSubAdmin(String email, String newPassword) {
        // to update the reset password to password in database
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            //update table name set password=? where email=?;

            String query = "UPDATE RegDeptAdminDTO e SET e.password=:password WHERE e.email=:email ";

            Query query1 = entityManager.createQuery(query);
            query1.setParameter("password", newPassword);
            query1.setParameter("email", email);

            int executeData = query1.executeUpdate();
            log.info("executeData{}",executeData);
            entityTransaction.commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            entityTransaction.rollback();
            return false;
        } finally {

            entityManager.close();
        }

    }


}

