package fr.pizzeria.servlet.pizza;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.metier.ejb.PizzaService;

@WebServlet("/admin/supprimerPizza")
public class SupprimerPizza extends HttpServlet {
	
	@EJB private PizzaService sPizza;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String code = req.getParameter("code");
		
		sPizza.supprimerPizza(code);
		
		this.getServletContext().getRequestDispatcher("/listerPizza").forward(req, resp);
	}

	
	
}
	