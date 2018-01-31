package by.tr.web.controller.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import by.tr.web.controller.ActionCommand;
import by.tr.web.controller.ConfigurationManager;
import by.tr.web.controller.SessionRequestContent;
import by.tr.web.entity.Movie;
import by.tr.web.service.MovieService;

public class EditMovieCommand implements ActionCommand {

	private static final String PATH_PAGE_EDIT = "path.page.edit";

	public String execute(SessionRequestContent content) {
		String page = ConfigurationManager.getProperty(PATH_PAGE_EDIT);
		
		content.insertLocal();
		
		int idMovie = content.extractMovieID();
		ArrayList<Movie> movies = content.extractMovies();
		String language = content.extractLocal();
		
		MovieService catalogService = new MovieService();
		Movie movieRU = catalogService.getMovie(idMovie,"ru", movies, true);
		Movie movieEN = catalogService.getMovie(idMovie,"en", movies, true);

		catalogService.setCountry(movieRU, "ru");
		catalogService.setGenre(movieRU, "ru");
		catalogService.setCountry(movieEN, "en");
		catalogService.setGenre(movieEN, "en");
		
		HashMap<Integer, String> genres = catalogService.getGenres(language);
		List<String> typesEN = catalogService.getTypes("en");
		List<String> typesRU = catalogService.getTypes("ru");
		HashMap<Integer, String> countries = catalogService.getCountries(language);
		
		content.insertMovie(movieRU, "RU");
		content.insertMovie(movieEN, "EN");
		content.inserGenres(genres);
		content.insertENTypes(typesEN);
		content.insertRUTypes(typesRU);
		content.insertCountries(countries);
		return page;
	}

}
