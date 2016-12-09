package fr.pizzeria.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.jboss.logging.Logger;

import fr.pizzeria.exception.FichierException;
import fr.pizzeria.model.Pizza;

public class FichierMetier {

	private static final String CHEMIN 		= "src/main/resources/data";
	
	/**
	 * Permet de cr�er un fichier texte nomm� <CODE_PIZZA>.txt contenant les informations d'un objet {@link Pizza} tel que :
	 * "<NOM_PIZZA>;<PRIX_PIZZA>;<TYPE_PIZZA>
	 * 
	 * @param pizza
	 * 		Correspond � l'objet {@link Pizza} � enregistrer
	 * @throws FichierException 
	 */
	public void sauvegarderDansFichier( Pizza pizza ) throws FichierException {
		
		try (FileWriter fw = new FileWriter(new File(CHEMIN,  pizza.getCode() + ".txt"))){
			fw.write(pizza.getNom() + ";" + pizza.getPrix() + ";" + pizza.getType().name());
			fw.flush();
			fw.close();
		} catch( IOException e ) {
			Logger.getLogger(e.getMessage());
			throw new FichierException(e);
		}
	}
	
	/**
	 * Permet de supprimer le fichier texte d'un objet {@link Pizza} pass� en param�tre
	 * 
	 * @param pizza
	 * 		Correspond � l'objet {@link Pizza} � supprimer
	 */
	public boolean supprimerDansFichier( String code ) {
		
		File fichier = new File(CHEMIN,  code + ".txt");
		return fichier.delete();
	}
	
	/**
	 * Permet de supprimer tous les fichiers et dossier contenu dans le chemin pass� en param�tre.
	 * Dans le cas pr�sent, cette fonction est appel� � la fermeture de l'application.
	 * 
	 * 
	 * @param chemin
	 * 		Correspond au chemin du dossier dont le contenu est � supprimer
	 * @throws FichierException 
	 */
	public void supprimerDossier( String chemin ) throws FichierException
	{
	  File path = new File( CHEMIN );
	  if( path.exists() )
	  {
	    File[] files = path.listFiles();
	    for( int i = 0 ; i < files.length ; i++ )
	    {
	      if( files[ i ].isDirectory() )
	      {
	        supprimerDossier( path+"\\"+files[ i ] );
	      }
	      if(files[ i ].delete()){
	    	  throw new FichierException("Impossible de supprimer le fichier");
	      }
	    }
	  }
	}
	
	public String[] listerFichier() {
		File data = new File(CHEMIN);
		return data.list();
	}

	public String[] recupererPizza(String codePizza) throws IOException {
		File pizzaFile = new File(codePizza + ".txt");
		FileReader f = new FileReader(pizzaFile);
		BufferedReader reader = new BufferedReader(f);
		String linePizza = reader.readLine();
		reader.close();
		return linePizza.split(";");
	}
}
