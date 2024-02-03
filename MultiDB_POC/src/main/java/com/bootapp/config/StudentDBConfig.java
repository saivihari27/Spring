package com.bootapp.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "studentEntityManagerFactory", 
		basePackages = {"com.bootapp.student.repo"},transactionManagerRef = "studentTransactionManager")
public class StudentDBConfig {
	
	@Primary
	@Bean(name="studentDatasource")
	//@ConfigurationProperties(prefix = "spring.datasource")
	DataSource dataSource() {
	 DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
	 dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
	 dataSourceBuilder.url("jdbc:mysql://localhost:3306/studentpoc?createDatabaseIfNotExist=true");
	 dataSourceBuilder.username("root");
	 dataSourceBuilder.password("root");
	 return dataSourceBuilder.build();
	}
	
	@Primary
	@Bean(name = "studentEntityManagerFactory")
	LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder,
			@Qualifier("studentDatasource") DataSource dataSource) {
		return builder.dataSource(dataSource)
				.packages("com.bootapp.student.entity").persistenceUnit("Student").build();
	}
	
	
	@Primary
	@Bean(name = "studentTransactionManager")
	PlatformTransactionManager transactionManager(
			@Qualifier("studentEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}
