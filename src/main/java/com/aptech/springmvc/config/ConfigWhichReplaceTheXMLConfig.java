package com.aptech.springmvc.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "com.aptech.springmvc")
@EnableWebMvc
@EnableTransactionManagement
@EnableAspectJAutoProxy
public class ConfigWhichReplaceTheXMLConfig implements WebMvcConfigurer {

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }


    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource pooledDataSource = new ComboPooledDataSource();

        pooledDataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
        pooledDataSource.setUser("root");
        pooledDataSource.setPassword("admin");
        pooledDataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/book_tracker?useSSL=false");
        pooledDataSource.setMaxPoolSize(20);
        pooledDataSource.setMaxIdleTime(30000);
        pooledDataSource.setMinPoolSize(5);

        return pooledDataSource;
    }


    @Bean
    public LocalSessionFactoryBean sessionFactory() throws PropertyVetoException {
        LocalSessionFactoryBean  sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[] {"com.aptech.springmvc.entity"});
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    @Bean
    public PlatformTransactionManager hibernateTransactionManager() throws PropertyVetoException {
        HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
        hibernateTransactionManager.setSessionFactory(sessionFactory().getObject());
        return hibernateTransactionManager;
    }

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver commonsMultipart() {
        CommonsMultipartResolver commonsMultipart = new CommonsMultipartResolver();
        commonsMultipart.setMaxUploadSize(500000);
        return commonsMultipart;
    }

    private Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        hibernateProperties.setProperty("hibernate.show_sql","true");
        return hibernateProperties;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/css/**").addResourceLocations("/resources/css/");
    }


}