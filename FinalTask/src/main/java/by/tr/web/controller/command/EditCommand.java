package by.tr.web.controller.command;

import java.util.HashMap;
import java.util.List;

import by.tr.web.controller.ActionCommand;
import by.tr.web.controller.ConfigurationManager;
import by.tr.web.controller.SessionRequestContent;
import by.tr.web.entity.Movie;
import by.tr.web.service.MovieService;
import by.tr.web.service.UsersService;

public class EditCommand implements ActionCommand {

	private static final String PATH_PAGE_MAIN = "path.page.main";

	public String execute(SessionRequestContent content) {
		
		HashMap<Integer, Integer> statuses = new HashMap<Integer, Integer>();
		HashMap<Integer, Boolean> accesses = new HashMap<Integer, Boolean>();
		
		content.insertLocal();
		
		content.extractStatuses(statuses);
		content.extractAccesses(accesses);
		
		UsersService usersService = new UsersService();
		usersService.updateAccesses(accesses);
		usersService.updateStatuses(statuses);
		
		String language = content.extractLocal();
		
		MovieService catalog = new MovieService();
		int pageNumber = content.extractPageNumber();
		int pageCount = catalog.getPageCount();
		
		content.setPageCount(pageCount);
		
		List<Movie> movies = catalog.getMovies(language, pageNumber);
		content.insertMovies(movies);
		
		String page  = ConfigurationManager.getProperty(PATH_PAGE_MAIN);
		return page;
	}

}
