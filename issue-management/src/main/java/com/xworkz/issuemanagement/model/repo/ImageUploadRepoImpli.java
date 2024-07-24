package com.xworkz.issuemanagement.model.repo;

import com.xworkz.issuemanagement.constants.Status;
import com.xworkz.issuemanagement.dto.ProfileImageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
public class ImageUploadRepoImpli implements ImageUploadRepo {

    @Autowired
    private EntityManagerFactory entityManagerFactory;


    @Override
    public boolean saveProfileImage(ProfileImageDTO profileImageDTO) {
        log.info("saveProfileImage method running in ImageUploadRepoImpl..");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();
            entityManager.persist(profileImageDTO);
            entityTransaction.commit();
            return true;

        } catch (PersistenceException e) {
            log.error("Error saving image: {}", e.getMessage());
            if (entityTransaction != null && entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
                return false;

        } finally {
            entityManager.close();
            log.info("Connection closed");
        }


    }

    /*Avoiding NullPointerException:
    The Optional class in Java is used to represent a value that may or may not be present.
    */
    @Override
    public Optional<ProfileImageDTO> findImageDetailsByUserId(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            Query query = entityManager.createQuery("SELECT dto from ProfileImageDTO dto WHERE dto.user.id=: id");
            query.setParameter("id", id);

            return query.getResultList().stream().findFirst();

            //can we write like this or in single line
//            List<ProfileImageDTO> list =query.getResultList();
//            Optional<ProfileImageDTO> data=list.stream().findFirst();
            //           return data;
        } catch (PersistenceException e) {
            e.printStackTrace();
            entityTransaction.rollback();
            return Optional.empty();
        } finally {
            entityManager.close();
        }
        // return Optional.empty() if object is empty/no value
    }

    @Override
    public void imageUpdateDetails(ProfileImageDTO profileImageDTO) {
        log.info("updateImage method running in ImageUploadRepoImpl..");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();
            //inActive to active
            entityManager.merge(profileImageDTO);
            entityTransaction.commit();
        } catch (Exception e) {
            log.error("Error updating image: {}", e.getMessage());
            if (entityTransaction != null && entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
        } finally {
            entityManager.close();
            log.info("Connection closed");
        }


    }

    @Override
    public void setAllImagesInactiveForUser(int id)
    {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();
            Query query = entityManager.createQuery("UPDATE ProfileImageDTO dto SET dto.status=:status WHERE dto.user.id=:userId ");
            query.setParameter("status", Status.INACTIVE);
            query.setParameter("userId", id);
           int  updatedCount=query.executeUpdate();

            log.info("Number of images set inactive: {}", updatedCount);
            entityTransaction.commit();

        } catch (PersistenceException e) {
            log.error("Error setting images inactive for user with ID {}: {}", id, e.getMessage());
            if (entityTransaction != null && entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
        } finally {
            entityManager.close();
            log.info("Connection closed for SetAllImagesInactiveForUser..");
        }
    }
}
