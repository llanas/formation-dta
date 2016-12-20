package fr.pizzeria.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.dao.DAOFactory;
import fr.pizzeria.dao.DaoJPA;

public class ListerPizzas extends HttpServlet {
	
	DAOFactory dao = new DaoJPA();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setAttribute("pizzas", dao.getPizzaDao().getListePizza());
		this.getServletContext().getRequestDispatcher("/WEB-INF/listerPizzas.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("pizzas", dao.getPizzaDao().getListePizza());
		this.getServletContext().getRequestDispatcher("/WEB-INF/listerPizzas.jsp").forward(req, resp);
	}
	
	
}
