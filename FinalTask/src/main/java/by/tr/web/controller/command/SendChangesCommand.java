package by.tr.web.controller.command;

import java.util.HashMap;

import by.tr.web.controller.ActionCommand;
import by.tr.web.controller.ConfigurationManager;
import by.tr.web.controller.SessionRequestContent;
import by.tr.web.entity.Movie;
import by.tr.web.entity.User;
import by.tr.web.service.MovieService;
import by.tr.web.service.ReviewService;

public class SendChangesCommand implements ActionCommand {
	
	private static final String PATH_PAGE_MOVIE = "path.page.movie";

	public String execute(SessionRequestContent content) {
		String page = ConfigurationManager.getProperty(PATH_PAGE_MOVIE);
		content.insertLocal();
		
		Movie movieRU = content.extractMovie("ru");
		Movie movieEN = content.extractMovie("en");
		
		int[] genreIDs = content.extractGenres();
		int[] countryIDs = content.extractCountries();
		
		String language = content.extractLocal();
		int idMovie = content.extractMovieID();
		
		HashMap<String, String> newGenres = content.extractNewGenres();
		HashMap<String, String> newCountries = content.extractNewCountries();
		
		MovieService movieService = new MovieService();
		movieService.setMovie(movieRU, "ru");
		movieService.setMovie(movieEN, "en");
		movieService.updateGenre(genreIDs, idMovie);
		movieService.updateCountry(countryIDs, idMovie);
	
		movieService.addNewGenres(newGenres, idMovie);
		movieService.addNewCountries(newCountries, idMovie);
		Movie movie = new Movie();
		if("ru".equalsIgnoreCase(language)){
			movie = movieRU;
		}
		if("en".equalsIgnoreCase(language)){
			movie = movieEN;
		}
		movieService.setCountry(movie, language);
		movieService.setGenre(movie, language);
		ReviewService reviewService = new ReviewService();
		HashMap<User,String> reviews = reviewService.showReview(idMovie);
		
		content.insertMovie(movie);
		content.insertReview(reviews);
		return page;
	}

}
