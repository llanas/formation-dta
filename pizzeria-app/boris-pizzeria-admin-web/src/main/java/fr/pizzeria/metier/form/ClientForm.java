package fr.pizzeria.metier.form;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import fr.pizzeria.exception.DAOException;
import fr.pizzeria.metier.ejb.ClientService;
import fr.pizzeria.model.Client;

@Named
public class ClientForm {

	private static final String CHAMP_LOGIN  = "mailClient";
    private static final String CHAMP_PASSWORD   = "passwordClient";
    private String resultat;
	private static Map<String, String> erreurs = new HashMap<>();
	
	@EJB private ClientService service;

	public ClientForm( ) {
		//CDI
	}

    public String getResultat() {
    	return resultat;
    }
    
    public Map<String, String> getErreurs() {
    	return erreurs;
    }

	public Client connexionClient( HttpServletRequest request ) {
    	
    	erreurs.clear();
    	
    	String login = getValeurChamp(request, CHAMP_LOGIN);
    	String password = getValeurChamp(request, CHAMP_PASSWORD);
    	
    	Client client = null;
    	
    	traiterLogin(login, client);
    	traiterPassword(password, client);
    
    	try{
    		if(erreurs.isEmpty()){
    			client = service.connexion(login, password);
    			resultat = "Succés de la connexion!";
    		} else {
    			resultat = "Erreur lors de la connexion.";
    		}
    	} catch (DAOException e){
    		erreurs.put("Imprevu", "Erreur imprevu lors de la connexion d'un Client");
    		resultat = "Identifiant ou mot de passe invalide.";
    		e.printStackTrace();
    	}
    	
    	return client;
    }

	private void traiterPassword(String password, Client client) {
		// TODO Auto-generated method stub
		
	}

	private void traiterLogin(String login, Client client) {
		// TODO Auto-generated method stub
		
	}
	
	private static String getValeurChamp( HttpServletRequest request, String champ ){
		if (champ == null) {
			champ = "";
		}
		String valeur = request.getParameter( champ );
		if( valeur == null || valeur.trim().length() == 0){
			return null;
		} else {
			return valeur.trim();
		}
	}
	
}
