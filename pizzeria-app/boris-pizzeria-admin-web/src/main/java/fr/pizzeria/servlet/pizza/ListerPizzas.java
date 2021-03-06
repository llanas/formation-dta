package fr.pizzeria.servlet.pizza;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.metier.ejb.PizzaService;

@WebServlet("/listerPizza")
public class ListerPizzas extends HttpServlet {
	
	private static final String VUE_LISTER_PIZZA 		= "/WEB-INF/listerPizzas.jsp";
		
	@EJB private PizzaService service;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setAttribute("pizzas", service.getListePizza());
		this.getServletContext().getRequestDispatcher(VUE_LISTER_PIZZA).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("pizzas", service.getListePizza());
		this.getServletContext().getRequestDispatcher(VUE_LISTER_PIZZA).forward(req, resp);
	}
	
	
}
