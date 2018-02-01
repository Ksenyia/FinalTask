package by.tr.web.controller.command;

import java.util.List;

import by.tr.web.controller.ActionCommand;
import by.tr.web.controller.ConfigurationManager;
import by.tr.web.controller.SessionRequestContent;
import by.tr.web.entity.Movie;
import by.tr.web.entity.User;
import by.tr.web.service.MovieService;
import by.tr.web.service.RatingService;

public class RatingCommand implements ActionCommand {

	private static final String PATH_PAGE_MAIN = "path.page.main";

	public String execute(SessionRequestContent content) {
		String page  = ConfigurationManager.getProperty(PATH_PAGE_MAIN);
		content.insertLocal();
		User user = content.extractUser();
		if(user!=null){
			int data[] = content.extractRating();
			int rating = data[1];
			int idFilm = data[0];
			RatingService ratingService = new RatingService();
			ratingService.setRating(user.getId(), rating, idFilm);
			String language = content.extractLocal();
			
			MovieService catalog = new MovieService();
			int pageCount = catalog.getPageCount();
			
			content.setPageCount(pageCount);
			
			int pageNumber = content.extractPageNumber();
			List<Movie> movies = catalog.getMovies(language, pageNumber);
			content.insertMovies(movies);
		}
		return page;
	}

}
