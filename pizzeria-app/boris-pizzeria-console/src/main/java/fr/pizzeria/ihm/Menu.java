package fr.pizzeria.ihm;

import java.util.HashMap;
import java.util.Map;

import fr.pizzeria.ihm.choix.AfficherCarte;
import fr.pizzeria.ihm.choix.AfficherPizzaPlusCher;
import fr.pizzeria.ihm.choix.Ajouter;
import fr.pizzeria.ihm.choix.Choix;
import fr.pizzeria.ihm.choix.ListeCategorie;
import fr.pizzeria.ihm.choix.Modifier;
import fr.pizzeria.ihm.choix.Supprimer;

public class Menu {
	
	private Map<Integer, Choix> navigateur = new HashMap<Integer, Choix>();
	private IhmUtil ihm;
	
	public Menu( IhmUtil ihm ){
		
		Integer i = 0;

		this.ihm = ihm;
		navigateur.put( ++i, new AfficherCarte(ihm, i));
		navigateur.put( ++i, new ListeCategorie(ihm, i));
		navigateur.put( ++i, new AfficherPizzaPlusCher(ihm, i));
		navigateur.put( ++i, new Ajouter(ihm, i));
		navigateur.put( ++i, new Modifier(ihm, i));
		navigateur.put( ++i, new Supprimer(ihm, i));
	}
	
	public void afficherMenu() {
		
		navigateur.forEach((i, c) -> ihm.systemOut(c.afficher()));
		
	}

	public boolean getChoix() {
		
		int choix = ihm.getIndex(navigateur.size()+1, "Que souhaitez vous faire ?");
		if(choix == 99){
			return false;
		} else {
			navigateur.get(choix).executer();
			return true;
		}
	}
}
