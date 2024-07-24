package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.ProfileImageDTO;

import java.util.Optional;

public interface ImageUploadService {

    boolean saveProfileImage(ProfileImageDTO profileImageDTO);

    Optional<ProfileImageDTO> findImageDetailsByUserId(int id); //id from SignUpDTO

    /// New method for updating image details
    void updateImageDetails(ProfileImageDTO editProfileImageDTO); // New method


    //  void setAudit(EditProfileImageDTO editProfileImageDTO , String updatedBy, LocalDateTime updatedOn);
    void setAllImagesInactiveForUser(int id);  // New method declaration


}
