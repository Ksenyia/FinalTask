package by.tr.web.controller.command;

import javax.servlet.http.HttpServletRequest;

import by.tr.web.controller.ActionCommand;
import by.tr.web.controller.ConfigurationManager;

public class MovieCommand implements ActionCommand {

	private static final String MOVIE_ID = "movieId";
	private static final String PATH_PAGE_MOVIE = "path.page.movie";

	public String execute(HttpServletRequest request) {
		String page = ConfigurationManager.getProperty(PATH_PAGE_MOVIE);
		int idMovie =Integer.parseInt(request.getParameter(MOVIE_ID));
		return page;
	}

}
