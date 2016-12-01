package fr.pizzeria.ihm.choix;

import fr.pizzeria.ihm.IhmUtil;

public abstract class Choix {
	
	private String description;
	protected IhmUtil ihm;
	protected int indexMenu = 0;
	protected String abandonner = "99";
	
	public String getDescription() {
		
		return description;
	}
	public void setDescription(String description) {
		
		this.description = description;
	}
	
	public String afficher(){
		
		return getDescription();
	}
	
	public abstract void executer();
}
