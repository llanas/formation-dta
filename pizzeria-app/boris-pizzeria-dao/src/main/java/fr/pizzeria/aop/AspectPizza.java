package fr.pizzeria.aop;

import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import fr.pizzeria.dao.perf.PerfDao;
import fr.pizzeria.model.Performance;
import fr.pizzeria.model.Pizza;

@Component
@Aspect
@Configurable
public class AspectPizza {
	
	@Autowired
	private PerfDao perfDao;
	
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
	 
	@Around("execution(* fr.pizzeria.dao.pizza.PizzaDao.*(..))")
	public Object setPerf(ProceedingJoinPoint pjp) throws Throwable {
		long debut = System.currentTimeMillis();
		Object result = pjp.proceed();			 
		Long tExec = System.currentTimeMillis()-debut;
		String service = pjp.getSignature().toShortString();
		Performance perf = new Performance(service, new Date(), tExec);
		System.out.println(tExec.toString() + " millisecondes");
		perfDao.setPerf(perf);
		return result;
	}
	
}
