package fr.pizzeria.test;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement
@ComponentScan("fr.pizzeria")
public class SpringTestConfig {

	@Bean
	public LocalEntityManagerFactoryBean getEntityManagerFactory() {
		LocalEntityManagerFactoryBean em = new LocalEntityManagerFactoryBean();
		em.setPersistenceUnitName("boris-pizzeria-app-test");
		return em;
	}
	
	@Bean
	public JpaTransactionManager jpaManager() {
		return new JpaTransactionManager();
	}
	
	@Bean 
	public DataSource getDataSource(){
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).addScript("insertData.sql").build();
	}
}
