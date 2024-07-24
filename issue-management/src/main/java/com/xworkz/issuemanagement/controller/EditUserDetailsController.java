package com.xworkz.issuemanagement.controller;

import com.xworkz.issuemanagement.constants.Status;
import com.xworkz.issuemanagement.dto.ProfileImageDTO;
import com.xworkz.issuemanagement.dto.SignUpDTO;
import com.xworkz.issuemanagement.model.repo.SignUpRepo;
import com.xworkz.issuemanagement.model.service.EditUserDetailsService;
import com.xworkz.issuemanagement.model.service.ImageUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/")
@SessionAttributes({"signUpDTO","profileImageDTO"})
@Slf4j
public class EditUserDetailsController {


    private static final String UPLOAD_DIR = "C:\\Users\\Jyothi\\Desktop\\project_Library\\User_images";

    @Autowired
    private EditUserDetailsService editUserDetailsService;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private ImageUploadService imageUploadService;



    public EditUserDetailsController() {
        log.info("EditUserDetailsController constructor:");
    }

    @GetMapping("edit")
    public String editUserProfile(@RequestParam("email") String email, Model model) {

        //call httpSession on signInController also
        String signedInUserEmail = (String) httpSession.getAttribute("signedInUserEmail");

        log.info("user data in editUserProfile() :{}", signedInUserEmail);

        if (signedInUserEmail != null && signedInUserEmail.equals(email)) {
            SignUpDTO signUpDTO = editUserDetailsService.getUserDetails(email);

            if(signUpDTO!=null) {

                // Get profile image from session
                String profileImageUrl = (String) httpSession.getAttribute("profileImage");
                model.addAttribute("profileImage", profileImageUrl);

                model.addAttribute("signUpDTO", signUpDTO);
                return "UpdateUserProfile";
            }
        }

        model.addAttribute("errorMessage", "Error fetching user details");
        return "UpdateUserProfile"; // Handle error appropriately
    }


    @PostMapping("updateUserProfile")
    public String updateUserProfile(SignUpDTO signUpDTO, Model model,@RequestParam("file") MultipartFile file, HttpSession httpSession)
    {
      try
      {
          String newFileName = null;

          if (file != null && !file.isEmpty())
          {
              String originalFileName = file.getOriginalFilename();

              newFileName = signUpDTO.getEmail() + "_" + originalFileName;
              log.info("Email and Original file Name:{}", newFileName);

              Path path = Paths.get(UPLOAD_DIR, newFileName);
              log.info(" adding file Name in desire location :{}", path);

              Files.write(path, file.getBytes());
              signUpDTO.setImageName(newFileName);

              ProfileImageDTO profileImageDTO = new ProfileImageDTO();

              profileImageDTO.setUser(signUpDTO);// set the user
              profileImageDTO.setImagePath(newFileName);
              profileImageDTO.setImageName(originalFileName);
              profileImageDTO.setImageSize(file.getSize());
              profileImageDTO.setImageType(file.getContentType());
              profileImageDTO.setCreatedOn(LocalDateTime.now());
              profileImageDTO.setCreatedBy(signUpDTO.getEmail());
              profileImageDTO.setUpdatedBy(signUpDTO.getEmail());
              profileImageDTO.setUpdatedOn(LocalDateTime.now());
              profileImageDTO.setStatus(Status.ACTIVE);

              // starting Set all previous images inactive
              imageUploadService.setAllImagesInactiveForUser(signUpDTO.getId());


             // Check if image details already exist for the user
              Optional<ProfileImageDTO> existingImage = imageUploadService.findImageDetailsByUserId(signUpDTO.getId());

              //if data present then get the value by get()
              //save image details in database

              if (existingImage.isPresent())
              {
                 profileImageDTO.setCreatedOn(signUpDTO.getCreatedOn());
                 profileImageDTO.setCreatedBy(signUpDTO.getCreatedBy());

                  imageUploadService.updateImageDetails(profileImageDTO);

              }
              else
              {
                  //for update date
                  imageUploadService.saveProfileImage(profileImageDTO);
              }
          }

              //edit user data and update
              SignUpDTO userData = editUserDetailsService.updateUserDetails(signUpDTO);

              if (userData != null)
              {
                  log.info("updated User Data:{}", signUpDTO);
                  model.addAttribute("signUpDTO", userData);
                  model.addAttribute("msg", "Profile updated Successfully!..");
                  httpSession.setAttribute("email", userData.getEmail());
                  httpSession.setAttribute("firstName", userData.getFirstName());
                  httpSession.setAttribute("lastName", userData.getLastName());
                  httpSession.setAttribute("contactNumber", userData.getContactNumber());


                  if (newFileName != null)
                  {
                      String imageURL = "/images/" + newFileName;
                      httpSession.setAttribute("profileImage", imageURL);
                      model.addAttribute("imageURL", imageURL);
                  }

                  // Display in console
                  log.info("Image upload");
                  log.info("file getName: {}", file.getName());
                  log.info("file getContentType: {}", file.getContentType());
                  log.info("file getResource: {}", file.getResource());
                  log.info("file getOriginalFilename: {}", file.getOriginalFilename());
                  log.info("File uploaded: {}, ContentType: {}", file.getOriginalFilename(), file.getContentType());

                  return "redirect:/edit?email=" + signUpDTO.getEmail(); // Redirect to avoid form resubmission

              }
              else
              {
                  model.addAttribute("errorMessageProfile", "Error while Updating profile.");
                  log.info("Error while Updating profile.");
              }


      }


         catch (IOException io)
        {
            model.addAttribute("errorUploadMsg","Error uploading file:"+ io.getMessage());
            log.error("Error uploading file",io);
        }

        return "redirect:/edit?email=" + signUpDTO.getEmail(); // Redirect to avoid form resubmission
    }

}
