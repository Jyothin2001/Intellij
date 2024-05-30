package com.xworkz.eventregister.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan("com.xworkz.eventregister")
@EnableWebMvc
public class EventRegisterConfig {

    public EventRegisterConfig() {
        System.out.println("no args EventRegisterConfig:");
    }

    @Bean
    public ViewResolver viewResolver() {
        System.out.println("Register in viewresolver");
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/");
        resolver.setSuffix(".jsp");
        return resolver;

    }
}
