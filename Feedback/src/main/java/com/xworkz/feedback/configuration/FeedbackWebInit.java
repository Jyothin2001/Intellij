package com.xworkz.feedback.configuration;

import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class FeedbackWebInit extends AbstractAnnotationConfigDispatcherServletInitializer implements WebMvcConfigurer
{
    @Override
    protected Class<?>[] getRootConfigClasses()
    {
        System.out.println("getRootConfigClasses");
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses()
    {
        System.out.println("getServletConfigClasses");
        return new Class[]{FeedbackConfiguration.class};
    }

    @Override
    protected String[] getServletMappings() {
        System.out.println("getServletMappings");
        return new String[]{"/"};
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer)
    {
        WebMvcConfigurer.super.configureDefaultServletHandling(configurer);
        configurer.enable();


    }
}
