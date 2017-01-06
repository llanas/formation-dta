package fr.pizzeria.dao.perf;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fr.pizzeria.model.Performance;

@Component
public class PerfDaoJpaSpring implements PerfDao {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public void setPerf(Performance perf) {
		em.persist(perf);
	}
}
