package by.tr.web.controller.command;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import by.tr.web.controller.ActionCommand;
import by.tr.web.controller.ConfigurationManager;
import by.tr.web.controller.SessionRequestContent;
import by.tr.web.entity.Movie;
import by.tr.web.entity.User;
import by.tr.web.service.MovieService;
import by.tr.web.service.ReviewService;

public class LocalCommand implements ActionCommand {

	public String execute(SessionRequestContent content) {
		 String page = null;
		/*
		 * String page = null;
		String pathPage = content.extractPage();
		content.insertLocal();
		page = ConfigurationManager.getProperty(pathPage);
		if(pathPage.equals("path.page.main")){
			String language = content.extractLocal();
			
			MovieService catalog = new MovieService();
			List<Movie> movies = catalog.getMovies(language);
			content.insertMovies(movies);
		}
		if(pathPage.equals("path.page.movie")){
			

			int idMovie = content.extractMovieID();
			ArrayList<Movie> movies = content.extractMovies();
			String language = content.extractLocal();
			
			MovieService catalogService = new MovieService();
			Movie movie = catalogService.getMovie(idMovie, language, movies, true);

			catalogService.setCountry(movie, language);
			catalogService.setGenre(movie, language);
			
			ReviewService catalog = new ReviewService();
			HashMap<User,String> reviews = catalog.showReview(idMovie);
			
			content.insertMovie(movie);
			content.insertReview(reviews);
			
		}*/
		return page;
	}

}
