package fr.pizzeria.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.dao.DAOFactory;
import fr.pizzeria.dao.DaoJPA;

public class EditerPizza extends HttpServlet {
	
	
	private static final String PIZZA			= "pizza";
	private static final String PIZZA_CODE 		= "code";
	
	DAOFactory dao = new DaoJPA();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				
		String code = req.getParameter(PIZZA_CODE);
		
		req.setAttribute(PIZZA, dao.getPizzaDao().recupererPizza(code));
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/editerPizza.jsp").forward(req, resp);
	}

}
