package fr.pizzeria.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.dao.DAOFactory;
import fr.pizzeria.dao.DaoJPA;

@WebServlet("/listerPizza")
public class ListerPizzas extends HttpServlet {
	
	private static final String VUE_LISTER_PIZZA 		= "/WEB-INF/admin/listerPizzas.jsp";
	
	DAOFactory dao = new DaoJPA();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setAttribute("pizzas", dao.getPizzaDao().getListePizza());
		this.getServletContext().getRequestDispatcher(VUE_LISTER_PIZZA).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("pizzas", dao.getPizzaDao().getListePizza());
		this.getServletContext().getRequestDispatcher(VUE_LISTER_PIZZA).forward(req, resp);
	}
	
	
}
