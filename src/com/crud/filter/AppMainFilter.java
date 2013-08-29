package com.crud.filter;

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

/**
 * Servlet Filter implementation class AppMainFilter
 */
@WebFilter("/AppMainFilter")
public class AppMainFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AppMainFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		
		HttpSession session = ((HttpServletRequest)request).getSession(true);
		String usrLogin = "";
		
		if (session!=null && session.getAttribute("user")!=null)
			usrLogin = session.getAttribute("user").toString();
		
		if (usrLogin==null || usrLogin.equals("")){
			((HttpServletResponse)response).sendRedirect("../view/login.html");
		}else{
			long tempoInicial = System.currentTimeMillis();
			
			// pass the request along the filter chain
			chain.doFilter(request, response);
			
			long tempoFinal = System.currentTimeMillis();
		    String uri = ((HttpServletRequest)request).getRequestURI();  
		    response.getWriter().println("<br/><br/>Tempo da requisição de " +
		            uri + " demourou (ms): " + (tempoFinal - tempoInicial));
			
		    response.getWriter().println("<br/><br/><a href='../view/MainServlet?action=logoutUser'>logout</a>");
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
