package by.tr.web.controller.command;

import javax.servlet.http.HttpServletRequest;

import by.tr.web.controller.ActionCommand;
import by.tr.web.controller.ConfigurationManager;
import by.tr.web.controller.SessionRequestContent;

public class MoviesCommand implements ActionCommand {

	private static final String PATH_PAGE_MAIN = "path.page.main";

	public String execute(HttpServletRequest request) {
		String page = ConfigurationManager.getProperty(PATH_PAGE_MAIN);
		SessionRequestContent content = new SessionRequestContent();
		content.insertMovies(request);
		return page;
	}

}
