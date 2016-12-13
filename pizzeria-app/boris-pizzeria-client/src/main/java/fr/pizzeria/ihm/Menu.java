package fr.pizzeria.ihm;

import fr.pizzeria.ihm.choix.Connection;
import fr.pizzeria.ihm.choix.Inscription;

public class Menu extends Nav{
	
	public Menu( IhmUtil ihm ){
		
		int i = 0;
		super.ihm = ihm;
		navigateur.put( ++i, new Inscription(ihm, i));
		navigateur.put( ++i, new Connection(ihm, i));
	}
}
