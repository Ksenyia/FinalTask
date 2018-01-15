package by.tr.web.controller.command;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.tr.web.controller.ActionCommand;
import by.tr.web.controller.ConfigurationManager;
import by.tr.web.dao.Catalog;
import by.tr.web.dao.RegistrationDAO;
import by.tr.web.entity.Movie;
import by.tr.web.entity.User;

public class RegisterCommand implements ActionCommand {

	public String execute(HttpServletRequest request) {
		
		//System.out.println("locale"+session.getAttribute("local"));
		String page = null;	
		HttpSession session = request.getSession(true);
		String login = request.getParameter("usrname");
		String password = request.getParameter("pwd1");
		String email = request.getParameter("email");
		User user = new User();
		List<Movie> movies = null;
		System.out.println(" 11"+login);
		System.out.println(login+"," +email+"," +password);
		user = RegistrationDAO.logon(login, email, password);
		session.setAttribute("user", user);
		try {
			String language = (String) session.getAttribute("local");
			movies = Catalog.getMovies(language);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Object obj = movies;
		System.out.println(obj.toString());
		String sessionAttribute = "movies"; 
	    session.setAttribute(sessionAttribute, obj);
		page = ConfigurationManager.getProperty("path.page.main");
		return page;
		
	}

}
