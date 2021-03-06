package fr.pizzeria.console;

import java.util.Scanner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@ComponentScan("fr.pizzeria")
@EnableTransactionManagement
@EnableAspectJAutoProxy
@EnableJpaRepositories("fr.pizzeria.repo")
public class PizzeriaAdminSpringConfig {
		
	@Bean
	public LocalEntityManagerFactoryBean entityManagerFactory() {
		LocalEntityManagerFactoryBean em = new LocalEntityManagerFactoryBean();
		em.setPersistenceUnitName("boris-pizzeria-app");
		return em;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		return  new JpaTransactionManager();
	}
	
	@Bean
	public Scanner getScanner() {
		return new Scanner(System.in);
	}
}
