package by.tr.web.controller.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.tr.web.controller.ActionCommand;
import by.tr.web.controller.ConfigurationManager;
import by.tr.web.dao.MovieCatalogDAO;
import by.tr.web.dao.RegistrationDAO;
import by.tr.web.entity.Movie;
import by.tr.web.entity.User;

public class RegisterCommand implements ActionCommand {

	private static final String PASSWORD = "pwd1";
	private static final String LOGIN = "usrname";
	private static final String EMAIL = "email";
	private static final String USER = "user";
	private static final String LOCAL = "local";
	private static final String PATH_PAGE_MAIN = "path.page.main";

	public String execute(HttpServletRequest request) {
		
		String page = null;	
		HttpSession session = request.getSession(true);
		String login = request.getParameter(LOGIN);
		String password = request.getParameter(PASSWORD);
		String email = request.getParameter(EMAIL);
		User user = new User();
		List<Movie> movies = null;
		RegistrationDAO registrationDAO = new RegistrationDAO();
		user = registrationDAO.register(login, email, password);
		session.setAttribute(USER, user);
		String language = (String) session.getAttribute(LOCAL);
		MovieCatalogDAO catalogDAO = new MovieCatalogDAO();
		movies = catalogDAO.getMovies(language);
		String sessionAttribute = "movies"; 
	    session.setAttribute(sessionAttribute, movies);
		page = ConfigurationManager.getProperty(PATH_PAGE_MAIN);
		return page;
		
	}

}
