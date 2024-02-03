package com.bootapp.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "departmentEntityManagerFactory", 
		basePackages = {"com.bootapp.department.repo"}, transactionManagerRef = "departmentTransactionManager")
public class DepartmentDBConfig {
	
	
	@Bean(name="departmentDatasource")
	//@ConfigurationProperties(prefix = "spring.datasource2")
	DataSource dataSource() {
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
		 dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
		 dataSourceBuilder.url("jdbc:mysql://localhost:3306/departmentpoc?createDatabaseIfNotExist=true");
		 dataSourceBuilder.username("root");
		 dataSourceBuilder.password("root");
		 return dataSourceBuilder.build();
	}
	
	
	@Bean(name = "departmentEntityManagerFactory")
	LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder,
			@Qualifier("departmentDatasource") DataSource dataSource) {
//		Map<String, Object> properties = new HashMap<>();
//		properties.put("hibernate.hbm2ddl.auto", "update");
//		properties.put("hibernate.dialect", "org.hibernate.dialect.MYSQL5Dialect");
		return builder.dataSource(dataSource)
				.packages("com.bootapp.department.entity").persistenceUnit("Department").build();
	}
	
	
	
	@Bean(name = "departmentTransactionManager")
	PlatformTransactionManager transactionManager(
			@Qualifier("departmentEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}
