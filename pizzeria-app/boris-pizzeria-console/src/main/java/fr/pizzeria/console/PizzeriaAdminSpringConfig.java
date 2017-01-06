package fr.pizzeria.console;

import java.util.Scanner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@ComponentScan("fr.pizzeria")
@EnableTransactionManagement
@EnableAspectJAutoProxy
public class PizzeriaAdminSpringConfig {
	
	@Bean
	public LocalEntityManagerFactoryBean getEntityManagerFactory() {
		LocalEntityManagerFactoryBean em = new LocalEntityManagerFactoryBean();
		em.setPersistenceUnitName("boris-pizzeria-app");
		return em;
	}
	
	@Bean
	public JpaTransactionManager jpaManager() {
		return new JpaTransactionManager();
	}
	
	@Bean
	public Scanner getScanner() {
		return new Scanner(System.in);
	}
}
