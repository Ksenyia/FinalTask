package by.tr.web.controller.command;


import javax.servlet.http.HttpServletRequest;

import by.tr.web.controller.ActionCommand;
import by.tr.web.controller.ConfigurationManager;
import by.tr.web.controller.SessionRequestContent;
import by.tr.web.entity.User;

public class RegisterCommand implements ActionCommand {

	private static final String PATH_PAGE_MAIN = "path.page.main";

	public String execute(HttpServletRequest request) {
		
		String page = null;	
		
		SessionRequestContent content = new SessionRequestContent();
		
		content.extractRegisteredUser(request);
		User user = content.extractRegisteredUser(request);
		content.insertUsers(request, user);
		content.insertMovies(request);
	 
		page = ConfigurationManager.getProperty(PATH_PAGE_MAIN);
		return page;
		
	}

}
