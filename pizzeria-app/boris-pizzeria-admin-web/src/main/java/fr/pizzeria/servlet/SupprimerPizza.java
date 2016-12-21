package fr.pizzeria.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.dao.*;

@WebServlet("/supprimerPizza")
public class SupprimerPizza extends HttpServlet {
	
	private DAOFactory dao = new DaoJPA();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String code = req.getParameter("code");
		
		dao.getPizzaDao().supprimer(code);
		
		this.getServletContext().getRequestDispatcher("/listerPizza").forward(req, resp);
	}

	
	
}
