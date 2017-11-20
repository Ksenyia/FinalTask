package by.tr.web.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import by.tr.web.dao.AuthorizationDAO;


/**
 * Servlet implementation class Controller
 */
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Controller() {
    	super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String encoding = "utf-8";
		String contentType = "text/html";
		String requestAttribute = "naming";
		String requestTagName = "command";
		String requestTagValue = "naming";
		String jspWelcomFile = "Welcom.jsp";
		String jspRegiserFile = "register.jsp";
		
		String name = request.getParameter("login");
		String password =request.getParameter("password");
		
		request.setCharacterEncoding(encoding);
		response.setContentType(contentType);
		try {
			if(AuthorizationDAO.login(name,password)){
				request.getRequestDispatcher(jspWelcomFile).forward(request, response);
				doGet(request, response);
			}
			else{
				request.getRequestDispatcher(jspRegiserFile).forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//doGet(request, response);
	}

}
