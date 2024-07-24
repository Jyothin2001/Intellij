package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.ProfileImageDTO;
import com.xworkz.issuemanagement.model.repo.ImageUploadRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class ImageUploadserviceImpli implements ImageUploadService{

    @Autowired
   private ImageUploadRepo imageUploadRepo;



    @Override
    public boolean saveProfileImage(ProfileImageDTO profileImageDTO) {
        log.info("saveProfileImage method running in ImageUploadServiceImpl..");

       boolean imageData= imageUploadRepo.saveProfileImage(profileImageDTO);

       if(imageData)
           log.info("saveImageDetails method running in ImageUploadServiceImpl..");
       else
           log.info("saveImageDetails method not  running in ImageUploadServiceImpl..");

        return true;
    }

    @Override
    public Optional<ProfileImageDTO> findImageDetailsByUserId(int id) {

        return imageUploadRepo.findImageDetailsByUserId(id);

    }

    @Override
    public void updateImageDetails(ProfileImageDTO editProfileImageDTO) {
        log.info("updateImageDetails method running in ImageUploadServiceImpl..");
        imageUploadRepo.imageUpdateDetails(editProfileImageDTO);
    }

    @Override
    public void setAllImagesInactiveForUser(int id)
    {
        log.info("setAllImagesInactiveForUser() method running in ImageUploadserviceImpli..");
        imageUploadRepo.setAllImagesInactiveForUser(id);

    }
}
