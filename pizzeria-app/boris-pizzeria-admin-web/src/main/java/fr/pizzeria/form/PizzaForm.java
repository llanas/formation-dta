package fr.pizzeria.form;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import fr.pizzeria.dao.DAOFactory;
import fr.pizzeria.exception.DAOException;
import fr.pizzeria.model.Pizza;

public class PizzaForm {
	
	private static final String CHAMP_CODE		= "codePizza";
	private static final String CHAMP_NOM		= "nomPizza";
	private static final String CHAMP_PRIX		= "prixPizza";
	private static final String CHAMP_TYPE		= "typePizza";
	private static final String CHAMP_OLD_CODE  = "oldCodePizza";
	private String resultat;
	private static Map<String, String> erreurs = new HashMap<>();
	private DAOFactory dao;
	
	public PizzaForm(DAOFactory dao) {
		super();
		this.dao = dao;
	}

    public String getResultat() {
    	return resultat;
    }
    
    public Map<String, String> getErreurs() {
    	return erreurs;
    }

	public Pizza modifierPizzaForm( HttpServletRequest req ) {
		
		erreurs.clear();
		String code = getValeurChamp(req, CHAMP_CODE);
		String nom = getValeurChamp(req, CHAMP_NOM);
		Double prix = Double.parseDouble(getValeurChamp(req, CHAMP_PRIX));
		String type = getValeurChamp(req, CHAMP_TYPE);
		String oldCode = getValeurChamp(req, CHAMP_OLD_CODE);
		
		Pizza pizza = null;
		
		try{
			if(erreurs.isEmpty()) {
				pizza = dao.getPizzaDao().modifier(code, nom, prix, type, oldCode);
				resultat = "La pizza à été modifié";
			} else {
				resultat = "Erreur lors de la modification";
			}
		} catch(DAOException e) {
			erreurs.put("Imprévu", "Erreurs lors de la modification de la pizza");
			resultat = "Données non valide";
			e.printStackTrace();
		}
		
		return pizza;
	}

	public Pizza ajouterPizzaForm(HttpServletRequest req) {
		
		erreurs.clear();
		String code = getValeurChamp(req, CHAMP_CODE);
		String nom = getValeurChamp(req, CHAMP_NOM);
		Double prix = Double.parseDouble(getValeurChamp(req, CHAMP_PRIX));
		String type = getValeurChamp(req, CHAMP_TYPE);
		
		Pizza pizza = null;
		
		try{
			if(erreurs.isEmpty()){
				pizza = dao.getPizzaDao().ajouter(code, nom, prix, type);
				resultat = "La pizza à été ajouter";
			} else {
				resultat = "Erreur lors de l'ajout de la pizza";
			}
		} catch( DAOException e) {
			erreurs.put("Imprevu", "Erreur lors de l'ajout de pizza");
			resultat = "Données invalides";
			e.printStackTrace();
		}
		
		return pizza;
	}
	
	private static String getValeurChamp( HttpServletRequest req, String champ){
		if (champ == null) {
			champ = "";
		}
		String valeur = req.getParameter( champ );
		if( valeur == null || valeur.trim().length() == 0){
			return null;
		} else {
			return valeur.trim();
		}
	}
    
    private static void setErreur( String champ, String message ) {
    	erreurs.put( champ, message );
    }
}
