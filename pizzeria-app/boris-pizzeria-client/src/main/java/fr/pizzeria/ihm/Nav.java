package fr.pizzeria.ihm;

import java.util.HashMap;
import java.util.Map;

import fr.pizzeria.ihm.choix.Choix;
import fr.pizzeria.model.Client;

public class Nav {

	protected Map<Integer, Choix> navigateur = new HashMap<>();
	protected IhmUtil ihm;
	protected Client client;
	
	public void afficherMenu() {
		navigateur.forEach((i, c) -> ihm.systemOut(c.afficher()));
	}
	
	public boolean getChoix() {
		
		int choix = ihm.getInt(navigateur.size()+1, "Que souhaitez vous faire ?");
		if(choix == 99){
			return false;
		} else {
			navigateur.get(choix).executer();
			return true;
		}
	}
}
