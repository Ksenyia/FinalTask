package by.tr.web.controller.command;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.tr.web.controller.ActionCommand;
import by.tr.web.controller.ConfigurationManager;
import by.tr.web.controller.MessageManager;
import by.tr.web.dao.AuthorizationDAO;
import by.tr.web.dao.Catalog;
import by.tr.web.entity.Movie;
import by.tr.web.entity.User;

public class LoginCommand implements ActionCommand {
	
	private static final String PARAM_NAME_LOGIN = "login";
	private static final String PARAM_NAME_PASSWORD = "password";

	public String execute(HttpServletRequest request) {
		String page = null;
        String submitName = "button";
		String button = request.getParameter(submitName);
		System.out.println(request.getRequestURI());
		System.out.println(button);
		HttpSession session = request.getSession(true); 
		List<Movie> movies = null;
		if((button!=null)&&button.equals("login")){
			try {
				String name = request.getParameter(PARAM_NAME_LOGIN);
				String password =request.getParameter(PARAM_NAME_PASSWORD);
				User user = AuthorizationDAO.login(name,password);
				if(user!=null){
					try {
						String language = (String) session.getAttribute("local");
						movies = Catalog.getMovies(language);
						session.setAttribute("user", user);
						
					} catch (SQLException e) {
						e.printStackTrace();
					}
					Object obj = movies;
					page = ConfigurationManager.getProperty("path.page.main");
					String sessionAttribute = "movies"; 
					session.setAttribute(sessionAttribute , obj);
					System.out.println("locale"+session.getAttribute("local"));
				}
				else{
					System.out.println("locale"+session.getAttribute("local"));
					request.setAttribute("errorLoginPassMessage",
							MessageManager.getProperty("message.loginerror"));
					page = ConfigurationManager.getProperty("path.page.login");
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		if((button!=null)&&button.equals("Register")){
			System.out.println("locale"+session.getAttribute("local"));
			page = ConfigurationManager.getProperty("path.page.register");
		}
		return page;
	}

}
