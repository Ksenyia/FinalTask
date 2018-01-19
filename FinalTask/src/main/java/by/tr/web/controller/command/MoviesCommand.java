package by.tr.web.controller.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.tr.web.controller.ActionCommand;
import by.tr.web.controller.ConfigurationManager;
import by.tr.web.dao.MovieCatalogDAO;
import by.tr.web.entity.Movie;

public class MoviesCommand implements ActionCommand {

	private static final String PATH_PAGE_MAIN = "path.page.main";
	private static final String LOCAL = "local";

	public String execute(HttpServletRequest request) {
		String page = ConfigurationManager.getProperty(PATH_PAGE_MAIN);
		HttpSession session = request.getSession(true); 
		List<Movie> movies = null;
		String language = (String) session.getAttribute(LOCAL);
		MovieCatalogDAO catalogDAO = new MovieCatalogDAO();
		movies = catalogDAO.getMovies(language);
		String sessionAttribute = "movies"; 
		session.setAttribute(sessionAttribute , movies);
		return page;
	}

}
