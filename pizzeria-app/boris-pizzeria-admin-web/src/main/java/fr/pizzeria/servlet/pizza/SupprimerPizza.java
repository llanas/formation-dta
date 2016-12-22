package fr.pizzeria.servlet.pizza;

import java.io.IOException;

import javax.enterprise.inject.Produces;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.dao.*;

@WebServlet("/admin/supprimerPizza")
public class SupprimerPizza extends HttpServlet {
	
	@Produces private DaoJPA dao = new DaoJPA();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String code = req.getParameter("code");
		
		dao.getPizzaDao().supprimer(dao.getPizzaDao().recupererPizza(code));
		
		this.getServletContext().getRequestDispatcher("/listerPizza").forward(req, resp);
	}

	
	
}
