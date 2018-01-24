package by.tr.web.controller.command;

import javax.servlet.http.HttpServletRequest;

import by.tr.web.controller.ActionCommand;
import by.tr.web.controller.ConfigurationManager;
import by.tr.web.controller.SessionRequestContent;

public class EditMovieCommand implements ActionCommand {

	private static final String PATH_PAGE_EDIT = "path.page.edit";

	public String execute(HttpServletRequest request) {
		String page = ConfigurationManager.getProperty(PATH_PAGE_EDIT);
		SessionRequestContent content = new SessionRequestContent();
		content.insertMovie(request);
		content.inserGenres(request);
		content.insertTypes(request);
		content.insertCountries(request);
		return page;
	}

}
