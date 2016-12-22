package fr.pizzeria.servlet.pizza;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.dao.DaoJPA;
import fr.pizzeria.metier.ejb.PizzaService;
import fr.pizzeria.metier.form.PizzaForm;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

@WebServlet("/admin/ajouterPizza")
public class AjouterPizza extends HttpServlet {
	
	private static final String VUE_AFFICHER_PIZZA 		= "/WEB-INF/afficherPizza.jsp";
	private static final String VUE_ERREUR				= "/WEB-INF/admin/pizza/ajouterPizza.jsp";

	private static final String TYPE					= "type";

	@Inject @Named("pizzaForm") PizzaForm form;
	
	@EJB private PizzaService service;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		List<String> type = new ArrayList<>();
		Arrays.asList(CategoriePizza.values()).forEach(p ->{ 
			type.add(p.toString().toUpperCase());
		});
		req.setAttribute(TYPE, type);
		this.getServletContext().getRequestDispatcher(VUE_ERREUR).forward(req, resp);

	}



	@Override
	protected void doPost( HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
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
