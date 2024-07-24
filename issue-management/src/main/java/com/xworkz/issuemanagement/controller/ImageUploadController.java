package com.xworkz.issuemanagement.controller;

import com.xworkz.issuemanagement.dto.SignUpDTO;
import com.xworkz.issuemanagement.model.service.EditUserDetailsService;
import com.xworkz.issuemanagement.model.service.ImageUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/")
@Slf4j
@SessionAttributes("/signupDTO")
public class ImageUploadController {

    private static final String UPLOAD_DIR="C:\\Users\\Jyothi\\Desktop\\project_Library\\User_images";

    @Autowired
    private ImageUploadService imageUploadService;

    @Autowired
    private EditUserDetailsService editUserDetailsService;

    @Autowired
    private HttpSession httpSession;

    public String userProfile(SignUpDTO signUpDTO, Model model, @RequestParam("file")MultipartFile file,HttpSession httpSession)
    {

        if(file!=null && !file.isEmpty())
        {
           String originalFileName= file.getOriginalFilename();

           String newFileName= signUpDTO.getEmail() + "_" + originalFileName;
           log.info("Email and Original file Name:{}",newFileName);

           Path path= Paths.get(UPLOAD_DIR,newFileName);
           log.info(" adding file Name in desire location :{}",path);

            try
            {
                file.getBytes();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }














return"";
    }










    public ImageUploadController()
    {
        log.info("ImageUploadController constructor:");
    }


}
