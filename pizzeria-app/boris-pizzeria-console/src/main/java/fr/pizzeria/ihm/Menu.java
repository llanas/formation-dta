package fr.pizzeria.ihm;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.pizzeria.ihm.choix.AfficherCarte;
import fr.pizzeria.ihm.choix.Ajouter;
import fr.pizzeria.ihm.choix.Choix;
import fr.pizzeria.ihm.choix.Modifier;
import fr.pizzeria.ihm.choix.Supprimer;

@Component
public class Menu {
	
	private List<Choix> navigateur = new ArrayList<>();
	
	@Autowired
	private AfficherCarte afficherCarte;
	@Autowired
	private Ajouter ajouter;
	@Autowired
	private Modifier modifier;
	@Autowired
	private Supprimer supprimer;
	
	@Autowired
	private IhmUtil ihm;
	
	public IhmUtil getIhm() {
		return ihm;
	}

	public void setIhm(IhmUtil ihm) {
		this.ihm = ihm;
	}
	
	public void afficherMenu() {
		
		navigateur.forEach(p -> ihm.systemOut(p.afficher()));
	}

	public boolean getChoix() {
		
		int choix = ihm.getInt(navigateur.size(), "Que souhaitez vous faire ?");
		if(choix == 99){
			return false;
		} else {
			navigateur.get(choix).executer();
			return true;
		}
	}
	
	@PostConstruct
	public void init() {
		
		navigateur.clear();
		navigateur.add(afficherCarte);
		navigateur.add(ajouter);
		navigateur.add(modifier);
		navigateur.add(supprimer);
	}
}
