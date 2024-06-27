package com.xworkz.feedback.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLOutput;

@Controller
@RequestMapping("/")
@EnableWebMvc
public class ImageController
{
    public ImageController()
    {
        System.out.println("ImageController constructor:");
    }

    @PostMapping("/upload")
    public String uploadImage(@RequestParam("filename") MultipartFile file, Model model)
    {
        System.out.println("Image upload:");


        try {
            System.out.println("file bytes: " + file.getBytes());

            String fileName = file.getName();
            System.out.println("file name:  " + fileName);
            System.out.println("file getContentType:  " + file.getContentType());
            System.out.println("file resource: " + file.getResource());
            System.out.println("file size:  " + file.getSize());
            System.out.println("file resource:  " + file.getOriginalFilename());


            byte[] fileBytes = file.getBytes();  //IoException
            Path path = Paths.get("C:\\Users\\Jyothi\\Desktop\\project_Library\\Images", file.getOriginalFilename());
            Files.write(path, fileBytes);

            System.out.println("fileName:" + file.getOriginalFilename() + "  ContentType:  " + file.getContentType());
            model.addAttribute("image", "you Successfully Uploaded " + file.getOriginalFilename());
        }
      catch (IOException e)
      {
        throw new RuntimeException(e);
    }
        return "FeedbackForm";
    }

    @GetMapping("/download")
    public String download(HttpServletResponse response,@RequestParam("fileName") String fileName1,Model model)  {
        System.out.println("running dowload method...");
        response.setContentType("image/jpeg");
        File file=new File("C:\\Users\\Jyothi\\Desktop\\project_Library\\Images",fileName1);

        try
        {
            InputStream buffer=new BufferedInputStream(new FileInputStream(file));
            ServletOutputStream outputStream=response.getOutputStream();
            IOUtils.copy(buffer,outputStream);
            response.flushBuffer();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        model.addAttribute("imageDownloaded",fileName1);

        return "FileUpload";
    }

}
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.bind.annotation.RequestMapping;
//import javax.servlet.http.HttpServletResponse;
//import java.io.*;
//
//        import org.apache.commons.io.IOUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//@RestController
//public class FileDownloadController {
//
//    private static final Logger logger = LoggerFactory.getLogger(FileDownloadController.class);
//
//    @GetMapping("/download")
//    public void download(HttpServletResponse response, @RequestParam("filename") String fileName) {
//        logger.info("Running download method for file: {}", fileName);
//
//        File file = new File("C:\\Users\\Jyothi\\Desktop\\project_Library\\Images", fileName);
//
//        if (!file.exists() || !file.isFile()) {
//            logger.error("File not found: {}", fileName);
//            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
//            return;
//        }
//
//        response.setContentType("image/jpeg");
//        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
//
//        try (InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
//             ServletOutputStream outputStream = response.getOutputStream()) {
//
//            IOUtils.copy(inputStream, outputStream);
//            response.flushBuffer();
//            logger.info("File download successful for file: {}", fileName);
//        } catch (IOException e) {
//            logger.error("Error occurred while downloading file: {}", fileName, e);
//            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//        }
//    }
//}


