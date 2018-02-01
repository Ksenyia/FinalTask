package by.tr.web.controller.command;

import java.util.HashMap;
import java.util.List;

import by.tr.web.controller.ActionCommand;
import by.tr.web.controller.ConfigurationManager;
import by.tr.web.controller.SessionRequestContent;
import by.tr.web.service.MovieService;

public class AddMovieCommand implements ActionCommand {
	
	private static final String PATH_PAGE_ADD= "path.page.add";

	public String execute(SessionRequestContent content) {
		String page = ConfigurationManager.getProperty(PATH_PAGE_ADD);
		content.insertLocal();
		String language = content.extractLocal();
		
		MovieService catalogService = new MovieService();
		HashMap<Integer, String> genres = catalogService.getGenres(language);
		List<String> typesEN = catalogService.getTypes("en");
		List<String> typesRU = catalogService.getTypes("ru");
		HashMap<Integer, String> countries = catalogService.getCountries(language);
		
		content.inserGenres(genres);
		content.insertTypes(typesEN, "en");
		content.insertTypes(typesRU, "ru");
		content.insertCountries(countries);
		return page;
	}

}
