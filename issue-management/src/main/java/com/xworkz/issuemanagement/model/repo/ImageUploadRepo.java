package com.xworkz.issuemanagement.model.repo;

import com.xworkz.issuemanagement.dto.ProfileImageDTO;

import java.util.Optional;

public interface ImageUploadRepo {

    boolean saveProfileImage(ProfileImageDTO profileImageDTO);

    Optional<ProfileImageDTO> findImageDetailsByUserId(int id); //id from SignUpDTO


    //to update image table

    void imageUpdateDetails(ProfileImageDTO profileImageDTO);

    void setAllImagesInactiveForUser(int id);
}
