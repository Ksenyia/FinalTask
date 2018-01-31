package by.tr.web.controller.command;

import java.util.ArrayList;
import java.util.HashMap;

import by.tr.web.controller.ActionCommand;
import by.tr.web.controller.ConfigurationManager;
import by.tr.web.controller.SessionRequestContent;
import by.tr.web.entity.Movie;
import by.tr.web.entity.User;
import by.tr.web.service.MovieService;
import by.tr.web.service.ReviewService;

public class SendReview implements ActionCommand {
	
	private static final String PATH_PAGE_MOVIE = "path.page.movie";

	public String execute(SessionRequestContent content) {
		String page = ConfigurationManager.getProperty(PATH_PAGE_MOVIE);
		content.insertLocal();
		
		int idMovie = content.extractMovieID();
		ArrayList<Movie> movies = content.extractMovies();
		String language = content.extractLocal();
		User user = content.extractUser();
		String review = content.extractReview();
		int userID = user.getId();
		
		ReviewService reviewService = new ReviewService();
		reviewService.addReview(review, idMovie,  userID);
		MovieService catalogService = new MovieService();
		Movie movie = catalogService.getMovie(idMovie, language, movies, false);
		catalogService.setCountry(movie, language);
		catalogService.setGenre(movie, language);
		ReviewService catalog = new ReviewService();
		HashMap<User,String> reviews = catalog.showReview(idMovie);
		
		content.insertMovie(movie);
		content.insertReview(reviews);
		return page;
	}

}
