package by.tr.web.controller.command;



import javax.servlet.http.HttpServletRequest;

import by.tr.web.controller.ActionCommand;
import by.tr.web.controller.ConfigurationManager;
import by.tr.web.controller.SessionRequestContent;

public class MovieCommand implements ActionCommand {

	private static final String PATH_PAGE_MOVIE = "path.page.movie";

	public String execute(HttpServletRequest request) {
		String page = ConfigurationManager.getProperty(PATH_PAGE_MOVIE);
		SessionRequestContent content = new SessionRequestContent();
		content.insertMovie(request);
		content.insertReview(request);
		return page;
	}

}
