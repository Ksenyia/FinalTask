package by.tr.web.controller.command;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import by.tr.web.controller.ActionCommand;
import by.tr.web.controller.ConfigurationManager;
import by.tr.web.controller.SessionRequestContent;
import by.tr.web.service.UsersService;

public class EditCommand implements ActionCommand {

	private static final String PATH_PAGE_MAIN = "path.page.main";

	public String execute(HttpServletRequest request) {
		
		HashMap<Integer, Integer> statuses = new HashMap<Integer, Integer>();
		HashMap<Integer, Boolean> accesses = new HashMap<Integer, Boolean>();
		SessionRequestContent content = new SessionRequestContent();
		content.extractStatuses(request, statuses);
		content.extractAccesses(request, accesses);
		UsersService usersService = new UsersService();
		usersService.updateAccesses(accesses);
		usersService.updateStatuses(statuses);
		content.insertMovies(request);
		String page  = ConfigurationManager.getProperty(PATH_PAGE_MAIN);
		return page;
	}

}
