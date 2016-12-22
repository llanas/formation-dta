package fr.pizzeria.servlet.client;

import java.io.IOException;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.pizzeria.dao.DaoJPA;
import fr.pizzeria.metier.form.ClientForm;
import fr.pizzeria.model.Client;


@WebServlet("/authentification")
public class Authentification extends HttpServlet {
	
	private static final String VUE_ACCUEIL			= "/WEB-INF/accueil.jsp";
	
	@Produces private DaoJPA dao = new DaoJPA();
	
	@Inject @Named("clientForm") ClientForm form;
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Client client = form.ConnexionClient(req);
		
		HttpSession session = req.getSession();
		
		req.setAttribute("client", client);
		req.setAttribute("form", form);
		
		if(form.getErreurs().isEmpty()){
			session.setAttribute("sessionClient", client);
			this.getServletContext().getRequestDispatcher(VUE_ACCUEIL).forward(req, resp);
		} else {
			session.setAttribute("sessionClient", null);
			this.getServletContext().getRequestDispatcher(VUE_ACCUEIL).forward(req, resp);
		}
	}
	
	
	

}
