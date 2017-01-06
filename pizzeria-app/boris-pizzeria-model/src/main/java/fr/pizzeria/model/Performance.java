package fr.pizzeria.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="perf")
public class Performance {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="service")
	private String service;
	@Column(name="date")
	private Date date;
	@Column(name="tExec")
	private Long tExec;
	
	/**
	 * Constructeur vide pour JPA
	 */
	public Performance() {
		//Vide
	}
	
	public Performance(String service, Date date, Long tExec) {
		this.service = service;
		this.date = date;
		this.tExec = tExec;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long gettExec() {
		return tExec;
	}

	public void settExec(Long tExec) {
		this.tExec = tExec;
	}
	
	
}
