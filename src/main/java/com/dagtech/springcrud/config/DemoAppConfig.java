package com.dagtech.springcrud.config;

import java.beans.PropertyVetoException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages="com.dagtech.springcrud")
@PropertySource("classpath:persistence-mysql.properties")
public class DemoAppConfig implements WebMvcConfigurer{
	
	// set up variable to hold the properties
		@Autowired
		private Environment env;
		
		// set up a logger for diagnostics
		private Logger logger = Logger.getLogger(getClass().getName());
		
		// define a bean for ViewResolver
		@Bean
		public ViewResolver viewResolver() {
			
			InternalResourceViewResolver viewResolver = 
					                     new InternalResourceViewResolver();
			
			viewResolver.setPrefix("/WEB-INF/view/");
			viewResolver.setSuffix(".jsp");
			
			return viewResolver;
			
		}
		
		// path to css, image and js files
		public void addResourceHandlers(final ResourceHandlerRegistry registry) {
	        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	    }
		
		
		
		// define a bean for our security datasource
		@Bean
		public DataSource securityDataSource() {
			
			// create connection pool
			ComboPooledDataSource securityDataSource = new ComboPooledDataSource();
			
			// set the jdbc driver
			
			try {
				
				securityDataSource.setDriverClass("com.mysql.jdbc.Driver");
			}
			catch (PropertyVetoException exc) {
				
				throw new RuntimeException(exc);
				
			}
			
			// log user and url to know the data is reading
			logger.info("jdbc.url=" + env.getProperty("jdbc.url"));
			logger.info("jdbc.user=" + env.getProperty("jdbc.user"));
			
			// set database connection props
			securityDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
			securityDataSource.setUser(env.getProperty("jdbc.user"));
			securityDataSource.setPassword(env.getProperty("jdbc.password"));
			
			// set connection pool props
			securityDataSource.setInitialPoolSize(
					           getIntProperty("connection.pool.initialPoolSize"));
			
			securityDataSource.setMinPoolSize(
			           getIntProperty("connection.pool.minPoolSize"));
			
			securityDataSource.setMaxPoolSize(
			           getIntProperty("connection.pool.maxPoolSize"));
			
			securityDataSource.setMaxIdleTime(
			           getIntProperty("connection.pool.maxIdleTime"));
			
			return securityDataSource;
			
		}
		

		// read enviroment property and convert to int
		
		private int getIntProperty(String propName) {
			
			String propVal = env.getProperty(propName);
			
			// now convert to int
			int intPropVal = Integer.parseInt(propVal);
			
			return intPropVal;
		}
		
		private Properties getHibernateProperties() {
			
			// set hibernate properties
			Properties props = new Properties();
			
			props.setProperty("hibernate.dialect", 
					 env.getProperty("hibernate.dialect"));
			
			props.setProperty("hibernate.show_sql", 
					 env.getProperty("hibernate.show_sql"));
			
			return props;
			
		}
		
		@Bean
		public LocalSessionFactoryBean sessionFactory() {
			
			// create session factory
			LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
			
			// set the properties
			sessionFactory.setDataSource(securityDataSource());
			sessionFactory.setPackagesToScan(env.getProperty("hibernate.packagesToScan"));
			sessionFactory.setHibernateProperties(getHibernateProperties());
			
			return sessionFactory;
			
		}
		
		@Bean
		@Autowired
		public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
			
			// setup transaction manager based on session factory
			HibernateTransactionManager txManager = new HibernateTransactionManager();
			txManager.setSessionFactory(sessionFactory);
			
			return txManager;
			
		}
		
		


}
