package by.tr.web.controller.command;

import java.util.ArrayList;

import by.tr.web.controller.ActionCommand;
import by.tr.web.controller.ConfigurationManager;
import by.tr.web.controller.SessionRequestContent;
import by.tr.web.entity.Movie;
import by.tr.web.service.MovieService;

public class DeleteCommand implements ActionCommand {

	private static final String PATH_PAGE_MAIN = "path.page.main";
	
	public String execute(SessionRequestContent content) {
		String page = ConfigurationManager.getProperty(PATH_PAGE_MAIN);	
		content.insertLocal();
		String language = content.extractLocal();
		int pageNumber = content.extractPageNumber();
		
		ArrayList<Integer> movieIDs = content.extractDeleteMovied();
		
		MovieService catalog = new MovieService();
		catalog.deleteMovie(movieIDs);
		
		int pageCount = catalog.getPageCount();
		
		content.setPageCount(pageCount);
		
		ArrayList<Movie> movies = catalog.getMovies(language,pageNumber);
		content.insertMovies(movies);
		return page;
	}

}
