package fr.pizzeria.dao;

import java.io.File;
import java.io.FileWriter;

import fr.pizzeria.model.Pizza;

public class FichierMetier {

	private static final String CHEMIN 		= "src/fr/pizzeria/dao/data";
	
	/**
	 * Permet de créer un fichier texte nommé <CODE_PIZZA>.txt contenant les informations d'un objet {@link Pizza} tel que :
	 * "<NOM_PIZZA>;<PRIX_PIZZA>;<TYPE_PIZZA>
	 * 
	 * @param pizza
	 * 		Correspond à l'objet {@link Pizza} à enregistrer
	 */
	public void SauvegarderDansFichier( Pizza pizza ) {
		
		try {
			File fichier = new File(CHEMIN,  pizza.getCode() + ".txt");
			FileWriter ecrireDansFichier = new FileWriter(fichier);
			ecrireDansFichier.write(pizza.getNom() + ";" + pizza.getPrix() + ";" + pizza.getType().name());
			ecrireDansFichier.flush();
			ecrireDansFichier.close();
			} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Permet de supprimer le fichier texte d'un objet {@link Pizza} passé en paramêtre
	 * 
	 * @param pizza
	 * 		Correspond à l'objet {@link Pizza} à supprimer
	 */
	public void SupprimerDansFichier( Pizza pizza ) {
		
		File fichier = new File(CHEMIN,  pizza.getCode() + ".txt");
		fichier.delete();
	}
	
	/**
	 * Permet de supprimer tous les fichiers et dossier contenu dans le chemin passé en paramêtre.
	 * Dans le cas présent, cette fonction est appelé à la fermeture de l'application.
	 * 
	 * 
	 * @param chemin
	 * 		Correspond au chemin du dossier dont le contenu est à supprimer
	 */
	public void SupprimerDossier( String chemin )
	{
	  File path = new File( CHEMIN );
	  if( path.exists() )
	  {
	    File[] files = path.listFiles();
	    for( int i = 0 ; i < files.length ; i++ )
	    {
	      if( files[ i ].isDirectory() )
	      {
	        SupprimerDossier( path+"\\"+files[ i ] );
	      }
	      files[ i ].delete();
	    }
	  }
	}
}
