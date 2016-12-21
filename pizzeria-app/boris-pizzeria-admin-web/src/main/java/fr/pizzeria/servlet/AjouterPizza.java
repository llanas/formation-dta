package fr.pizzeria.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.dao.DAOFactory;
import fr.pizzeria.dao.DaoJPA;
import fr.pizzeria.form.PizzaForm;
import fr.pizzeria.model.Pizza;

@WebServlet("/ajouterPizza")
public class AjouterPizza extends HttpServlet {
	
	private static final String VUE_AFFICHER_PIZZA 		= "/WEB-INF/admin/afficerPizza.jsp";
	private static final String VUE_ERREUR				= "/WEB-INF/erreurs.jsp";

	private DAOFactory dao = new DaoJPA();
	
	@Override
	protected void doPost( HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PizzaForm form = new PizzaForm( dao );
		
		Pizza pizza = form.ajouterPizzaForm(req);
		
		req.setAttribute("pizza", pizza);
		req.setAttribute("form", form);
		
		if(form.getErreurs().isEmpty()) {
			this.getServletContext().getRequestDispatcher(VUE_AFFICHER_PIZZA).forward(req, resp);
		} else {
			this.getServletContext().getRequestDispatcher(VUE_ERREUR).forward(req, resp);
		}
	}
	
}
