package com.crud.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;

import com.crud.dao.SolrInterface;
import com.crud.to.User;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/view/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//PrintWriter out = response.getWriter();
		
		String action = request.getParameter("action");
				
		if (action.equals("searchUser")){
			
			try {
				
				String qTerm  = request.getParameter("qTerm");
				String qField = request.getParameter("qField");
				
				if (qField!=null && !qField.equals("")){
					
					if (qTerm==null || qTerm.equals(""))
						qTerm = "*";
					
					SolrInterface solrInt = new SolrInterface();
					SolrQuery query = new SolrQuery();
					
					query.setQuery(qField+":"+qTerm);
					query.addSortField("id", SolrQuery.ORDER.asc);
					
					QueryResponse qr = solrInt.execQuery(query);
					
					String sOut = "";
					String sEditUrl = "";
					
					for (SolrDocument o : qr.getResults()) {
						
						sEditUrl = "";
						
						sOut += "<tr>";
						
						for (String s : o.getFieldNames()) {
							sOut += "<td>";
							sEditUrl += "&"+s+"=";
							if (o.get(s) instanceof List) {
								
								for (Object o1 : (List) o.get(s)) {
									sOut += o1.toString()+"<br/>";
									sEditUrl += o1.toString()+' ';
								}
								
							} else {
								sOut += o.get(s).toString();
								
								sEditUrl += o.get(s).toString();
							}
							sOut += "</td>";
						}
						
						sOut += "<td><a href='../view/cadastro.jsp?act=edit"+sEditUrl+"'>Editar</a></td>";						
						sOut += "</tr>";						
					}
					
					request.setAttribute("listagem", sOut);
					request.getRequestDispatcher("result.jsp").forward(request, response);
				}else{
					response.sendRedirect("../view/?r=no-term");	
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SolrServerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (action.equals("postUser")){
			User usr = new User();
			
			usr.id = request.getParameter("id");
			usr.nome = request.getParameter("nome");
			usr.apelido = request.getParameter("apelido");
			//usr.setInteresses("");
						
			SolrInterface solrInt = new SolrInterface();
			try {
				if (solrInt.doPostBean(usr)){
					response.sendRedirect("../view/cadastro.jsp?r=ok");					
				}else{
					response.sendRedirect("../view/cadastro.jsp?r=error");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SolrServerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (action.equals("delUser")){
			String id = request.getParameter("id");
			
			if (id!=null && !id.equals("")){
				SolrInterface solrInt = new SolrInterface();
				try {
					if (solrInt.deleteById(id)){
						response.sendRedirect("../view/?r=ok");					
					}else{
						response.sendRedirect("../view/cadastro.jsp?r=error");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SolrServerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}else{
				response.sendRedirect("../view/cadastro.jsp?r=no-id");				
			}
		}
		
		if (action.equals("loginUser")){

			HttpSession session = request.getSession(true);
							
			String user = request.getParameter("user");
			String pass = request.getParameter("pass");
				
			if (user==null) user="";
			if (pass==null) pass="";
				
			if (user.equals("luiz") && pass.equals("123")){
				session.setAttribute("user", user);
			}				
			
			response.sendRedirect("../view/");
		}
		
		if (action.equals("logoutUser")){

			HttpSession session = request.getSession(true);
			
			session.setAttribute("user", "");
			
			response.sendRedirect("../view/");
		}				
		//super(request, response);
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
