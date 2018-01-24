package by.tr.web.controller.command;

import javax.servlet.http.HttpServletRequest;

import by.tr.web.controller.ActionCommand;
import by.tr.web.controller.ConfigurationManager;
import by.tr.web.controller.SessionRequestContent;

public class AddMovieCommand implements ActionCommand {
	
	private static final String PATH_PAGE_ADD= "path.page.add";

	public String execute(HttpServletRequest request) {
		String page = ConfigurationManager.getProperty(PATH_PAGE_ADD);
		SessionRequestContent content = new SessionRequestContent();
		content.insertMovie(request);
		return page;
	}

}
