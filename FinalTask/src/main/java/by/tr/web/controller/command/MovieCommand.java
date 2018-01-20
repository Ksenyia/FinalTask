package by.tr.web.controller.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.tr.web.controller.ActionCommand;
import by.tr.web.controller.ConfigurationManager;
import by.tr.web.entity.Movie;
import by.tr.web.service.MovieCatalogService;

public class MovieCommand implements ActionCommand {

	private static final String MOVIE_ID = "movieId";
	private static final String PATH_PAGE_MOVIE = "path.page.movie";
	private static final String LOCAL = "local";

	public String execute(HttpServletRequest request) {
		String page = ConfigurationManager.getProperty(PATH_PAGE_MOVIE);
		int idMovie =Integer.parseInt(request.getParameter(MOVIE_ID));
		HttpSession session = request.getSession(true);
		MovieCatalogService catalogService = new MovieCatalogService();
		String language = (String) session.getAttribute(LOCAL);
		Movie movie = catalogService.getMovie(idMovie, language);
		catalogService.setCountry(movie, language);
		catalogService.setGenre(movie, language);
		session.setAttribute("movie", movie );
		return page;
	}

}
