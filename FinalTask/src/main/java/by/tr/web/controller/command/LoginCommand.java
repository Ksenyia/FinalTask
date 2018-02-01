package by.tr.web.controller.command;


import java.util.List;

import by.tr.web.controller.ActionCommand;
import by.tr.web.controller.ConfigurationManager;
import by.tr.web.controller.MessageManager;
import by.tr.web.controller.SessionRequestContent;
import by.tr.web.entity.Movie;
import by.tr.web.entity.User;
import by.tr.web.service.AuthorizationService;
import by.tr.web.service.MovieService;

public class LoginCommand implements ActionCommand {
	
	private static final String MESSAGE_LOGIN_ERROR = "message.loginerror";
	private static final String PATH_PAGE_REGISTER = "path.page.register";
	private static final String PATH_PAGE_LOGIN = "path.page.login";
	private static final String PATH_PAGE_MAIN = "path.page.main";
	private static final String REGISTER_BUTTON = "Register";
	private static final String LOGIN_BUTTON = "login";

	public String execute(SessionRequestContent content) {
		String page = null;
		content.insertLocal();
		String button = content.extractButton();
		if((button!=null)&&button.equals(LOGIN_BUTTON)){
			User user = content.extractAuthorizedUser();
			AuthorizationService authorizationService = new AuthorizationService();
			user = authorizationService.login(user.getLogin(),user.getPassword());	
			if(user!=null){
				String language = content.extractLocal();
				
				MovieService catalog = new MovieService();
				int pageCount = catalog.getPageCount();
				
				content.setPageCount(pageCount);
				
				int pageNumber = content.extractPageNumber();
				List<Movie> movies = catalog.getMovies(language, pageNumber);
				content.insertMovies(movies);
				
				content.insertUser(user);
				page = ConfigurationManager.getProperty(PATH_PAGE_MAIN);
			}
			else{
				content.insertMessage(MessageManager.getProperty(MESSAGE_LOGIN_ERROR));
				page = ConfigurationManager.getProperty(PATH_PAGE_LOGIN);
			}
		}
		if((button!=null)&&button.equals(REGISTER_BUTTON)){
			page = ConfigurationManager.getProperty(PATH_PAGE_REGISTER);
		}
		return page;
	} 

}
