package fr.pizzeria.servlet.pizza;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.metier.ejb.PizzaService;

@WebServlet("/afficherPizza")
public class AfficherPizza extends HttpServlet{
	
	@EJB private PizzaService sPizza;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String code = req.getParameter("code");
		
		req.setAttribute("pizza", sPizza.recupererPizza(code));
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/afficherPizza.jsp").forward(req, resp);
	}

	
}
