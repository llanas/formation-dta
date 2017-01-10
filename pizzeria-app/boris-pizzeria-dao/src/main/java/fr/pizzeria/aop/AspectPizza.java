package fr.pizzeria.aop;

import java.util.Date;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import fr.pizzeria.dao.perf.PerfDao;
import fr.pizzeria.dao.pizza.PizzaDao;
import fr.pizzeria.exception.PizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Performance;
import fr.pizzeria.model.Pizza;

@Component
@Aspect
@Configurable
public class AspectPizza {
	
	@Autowired
	private PerfDao perfDao;
	
	@Autowired
	@Qualifier("JPASpring")
	private PizzaDao pizzaDao;
	
	 @Before("execution(* fr.pizzeria.dao.pizza.PizzaDao.ajouter(..))")
	 public void setCodePizza(JoinPoint jp) {
		 
		 Object[] objects = jp.getArgs();
		 
		 for(Object listeObjets: objects){
			 Pizza p = (Pizza) listeObjets;
			 StringBuilder code = new StringBuilder();
			 code.append(p.getNom().charAt(0)).append(p.getNom().charAt(1)).append(p.getNom().charAt(2));
			 p.setCode(code.toString());
		 }
	 }
	 
//	@Around("execution(* fr.pizzeria.test.pizza.*(..))")
//	public Object setPizzaTest(ProceedingJoinPoint pjp) {
//		Object result = null;
//		try {
//			Pizza test = new Pizza("JUN","JunitTest",10.0,CategoriePizza.SANS_VIANDE);
//			pizzaDao.ajouter(test);
//			result = pjp.proceed();
//			if(pizzaDao.getListePizza().stream().filter(p -> p.getCode().equals(test.getCode())).findAny().isPresent()){
//				pizzaDao.supprimer(test);
//			}
//		} catch (Throwable e) {
//			Logger.getLogger(e.getMessage());
//		} finally {
//			return result;
//		}
//	}
	 
	@Around("execution(* fr.pizzeria.dao.pizza.PizzaDao.*(..))")
	public Object setPerf(ProceedingJoinPoint pjp) throws Throwable {
			Object result = null;
			long debut = System.currentTimeMillis();
			result = pjp.proceed();			 
			Long tExec = System.currentTimeMillis()-debut;
			String service = pjp.getSignature().toShortString();
			Performance perf = new Performance(service, new Date(), tExec);
			System.out.println("Temps d'éxecution de " + service + " : " + tExec.toString() + " millisecondes");
			perfDao.setPerf(perf);
			return result;
	}
	
}
