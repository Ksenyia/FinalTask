package by.tr.web.controller.command;

import java.util.List;

import by.tr.web.controller.ActionCommand;
import by.tr.web.controller.ConfigurationManager;
import by.tr.web.controller.SessionRequestContent;
import by.tr.web.dao.RegistrationDAO;
import by.tr.web.entity.Movie;
import by.tr.web.entity.User;
import by.tr.web.service.MovieService;

public class RegisterCommand implements ActionCommand {

	private static final String PATH_PAGE_MAIN = "path.page.main";

	public String execute(SessionRequestContent content) {
		
		String page = null;	
		content.insertLocal();
		content.extractRegisteredUser();
		User user = content.extractRegisteredUser();
		RegistrationDAO registrationDAO = new RegistrationDAO();
		user = registrationDAO.register(user.getLogin(), user.getEmail(), user.getPassword());
		content.insertUser(user);
		String language = content.extractLocal();
		
		MovieService catalog = new MovieService();
		List<Movie> movies = catalog.getMovies(language);
		content.insertMovies(movies);
	 
		page = ConfigurationManager.getProperty(PATH_PAGE_MAIN);
		return page;
		
	}

}
