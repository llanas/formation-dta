package fr.pizzeria.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/admin/*")
public class AuthFilter implements Filter {
	

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//Obligatoire implementation
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		HttpSession session = request.getSession();
		
		if( session.getAttribute("sessionClient") != null ) {
			chain.doFilter(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/accueil");
			
		}
	}

	@Override
	public void destroy() {
		
	}

}
