package fr.pizzeria.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-01-06T17:53:01.400+0100")
@StaticMetamodel(Performance.class)
public class Performance_ {
	public static volatile SingularAttribute<Performance, Integer> id;
	public static volatile SingularAttribute<Performance, String> service;
	public static volatile SingularAttribute<Performance, Date> date;
	public static volatile SingularAttribute<Performance, Long> tExec;
}
