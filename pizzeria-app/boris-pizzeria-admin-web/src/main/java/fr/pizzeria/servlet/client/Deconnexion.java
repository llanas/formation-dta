package fr.pizzeria.servlet.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/deconnexion")
public class Deconnexion extends HttpServlet {
	
	private static final String VUE_ACCUEIL 		= "/accueil";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		session.removeAttribute("sessionClient");
		
		resp.sendRedirect(req.getContextPath() + VUE_ACCUEIL);
	}

	
}
