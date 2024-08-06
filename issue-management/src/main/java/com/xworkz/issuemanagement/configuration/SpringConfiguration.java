package com.xworkz.issuemanagement.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan("com.xworkz.issuemanagement")
@PropertySource("classpath:application.properties")
public class SpringConfiguration implements WebMvcConfigurer
{

    public SpringConfiguration()
    {
        System.out.println("IssuemanagementConfig constructor:");
    }

    @Bean
    public ViewResolver viewResolver()
    {
        System.out.println("View Resolver:");

       InternalResourceViewResolver resolver=new InternalResourceViewResolver();
        resolver.setPrefix("/");
        resolver.setSuffix(".jsp");

        return resolver;
    }


    //to link js

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        WebMvcConfigurer.super.addResourceHandlers(registry);
        registry.addResourceHandler("/js/**").addResourceLocations("/javascript/");
        registry.addResourceHandler("/css/**").addResourceLocations("/css/");

        //set images in configuration
        registry.addResourceHandler("/images/**").addResourceLocations("file:C:/Users/Jyothi/Desktop/project_Library/User_images/");
    }

//file upload but CommonsMultipartResolver is better than this
    @Bean
    public StandardServletMultipartResolver standardServletMultipartResolver() {
        return new StandardServletMultipartResolver();
    }



    @Bean
    public CommonsMultipartResolver multipartResolver()
    {
        //another way to create new like object creation like below
       CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(10485760); // 10MB
        return multipartResolver;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }


}



