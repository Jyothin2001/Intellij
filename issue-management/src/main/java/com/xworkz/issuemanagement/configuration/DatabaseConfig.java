package com.xworkz.issuemanagement.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class DatabaseConfig
{
    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.password}")
    private String password;

    @Value("${jdbc.driver}")
    private String driver;

    public DatabaseConfig()
    {
        System.out.println("DatabaseConfig constructor:");
    }

    @Bean
    public DataSource dataSource()
    {
        DriverManagerDataSource driverManagerDataSource=new DriverManagerDataSource();
        driverManagerDataSource.setUsername(username);
        driverManagerDataSource.setUrl(url);
        driverManagerDataSource.setPassword(password);
        driverManagerDataSource.setDriverClassName(driver);

        return driverManagerDataSource;
    }


    @Bean
    public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean(DataSource dataSource)
    {
        System.out.println("LocalContainerEntityManagerFactoryBean:");

        LocalContainerEntityManagerFactoryBean localContainerBean=new LocalContainerEntityManagerFactoryBean();
        localContainerBean.setDataSource(dataSource);

        JpaVendorAdapter jpaVendorAdapter=new HibernateJpaVendorAdapter();

        localContainerBean.setJpaVendorAdapter(jpaVendorAdapter);
        localContainerBean.setPackagesToScan("com.xworkz.issuemanagement");

        //for showing queries in console
        Properties properties=new Properties();
        properties.put("hibernate.show_sql","true");
        localContainerBean.setJpaProperties(properties);

        return localContainerBean;
    }
}
