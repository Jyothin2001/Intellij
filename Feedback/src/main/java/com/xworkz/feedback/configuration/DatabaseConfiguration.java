package com.xworkz.feedback.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import javax.validation.Valid;
import java.sql.DriverManager;

@Configuration
public class DatabaseConfiguration
{
    //expression
    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    @Value("${jdbc.driver}")
    private String driver;

//DataSource for efficiency rather than .xml
//in .xml plug-in's etc. not support
   @Bean
   public DataSource dataSource()
    {
        System.out.println("data Source register:");

        DriverManagerDataSource dataSource=new DriverManagerDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driver);

        return dataSource;
    }

    //tell spring to exclude .xml and include Datasource and register to spring
    //create EntityManagerFactory  object
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(DataSource dataSource)
    {
        System.out.println("LocalContainerEntityManagerFactoryBean:");

        LocalContainerEntityManagerFactoryBean bean=new LocalContainerEntityManagerFactoryBean();
        bean.setDataSource(dataSource);

        JpaVendorAdapter jpaVendorAdapter= new HibernateJpaVendorAdapter();
        bean.setJpaVendorAdapter(jpaVendorAdapter);


        bean.setPackagesToScan("com.xworkz.feedback.dto"); //if you don't specify then it(spring) will scan entire packages

        return bean;
    }

}
