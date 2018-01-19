package by.tr.web.controller.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.tr.web.controller.ActionCommand;
import by.tr.web.controller.ConfigurationManager;
import by.tr.web.controller.MessageManager;
import by.tr.web.dao.MovieCatalogDAO;
import by.tr.web.entity.Movie;
import by.tr.web.entity.User;
import by.tr.web.service.AuthorizationService;

public class LoginCommand implements ActionCommand {
	
	private static final String ERROR_LOGIN_PASS_MESSAGE = "errorLoginPassMessage";
	private static final String MESSAGE_LOGIN_ERROR = "message.loginerror";
	private static final String PATH_PAGE_REGISTER = "path.page.register";
	private static final String PATH_PAGE_LOGIN = "path.page.login";
	private static final String REGISTER_BUTTON = "Register";
	private static final String PATH_PAGE_MAIN = "path.page.main";
	private static final String USER = "user";
	private static final String LOCAL = "local";
	private static final String LOGIN_BUTTON = "login";
	private static final String BUTTON = "button";
	private static final String PARAM_NAME_LOGIN = LOGIN_BUTTON;
	private static final String PARAM_NAME_PASSWORD = "password";

	public String execute(HttpServletRequest request) {
		String page = null;
        String submitName = BUTTON;
		String button = request.getParameter(submitName);
		HttpSession session = request.getSession(true); 
		List<Movie> movies = null;
		if((button!=null)&&button.equals(LOGIN_BUTTON)){
			String name = request.getParameter(PARAM_NAME_LOGIN);
			String password =request.getParameter(PARAM_NAME_PASSWORD);
			AuthorizationService authorizationService = new AuthorizationService();
			User user = authorizationService.login(name,password);
			if(user!=null){
				String language = (String) session.getAttribute(LOCAL);
				MovieCatalogDAO catalogDAO = new MovieCatalogDAO();
				movies = catalogDAO.getMovies(language);
				session.setAttribute(USER, user);
				page = ConfigurationManager.getProperty(PATH_PAGE_MAIN);
				String sessionAttribute = "movies"; 
				session.setAttribute(sessionAttribute , movies);
			}
			else{
				request.setAttribute(ERROR_LOGIN_PASS_MESSAGE,
						MessageManager.getProperty(MESSAGE_LOGIN_ERROR));
				page = ConfigurationManager.getProperty(PATH_PAGE_LOGIN);
			}
		}
		if((button!=null)&&button.equals(REGISTER_BUTTON)){
			page = ConfigurationManager.getProperty(PATH_PAGE_REGISTER);
		}
		return page;
	}

}
