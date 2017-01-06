package fr.pizzeria.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTransactionManager;

import fr.pizzeria.exception.DAOException;

public class MotherDaoJPA {

	protected EntityManagerFactory emf;
	
	@Autowired
	JpaTransactionManager jtm;
	
	@FunctionalInterface
	protected interface IEntityManager<T> {
		T exec(EntityManager em, EntityTransaction et);
	}
	
	protected <T> T execute(IEntityManager<T> run ) throws DAOException {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		try{
			et.begin();
			return run.exec(em, et);
		} catch( PersistenceException e ) {
			Logger.getLogger(e.getMessage());
			throw new DAOException(e);
		} finally {
			et.commit();
			if (em.isOpen()) {
				em.close();
			}
		}
	}
}
