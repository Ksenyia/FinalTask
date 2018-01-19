package by.tr.web.controller.command;

import javax.servlet.http.HttpServletRequest;

import by.tr.web.controller.ActionCommand;
import by.tr.web.controller.ConfigurationManager;

public class LogoutCommand implements ActionCommand {

	private static final String PATH_PAGE_INDEX = "path.page.index";

	public String execute(HttpServletRequest request) {
		String page = ConfigurationManager.getProperty(PATH_PAGE_INDEX);
		request.getSession().invalidate();
		return page;
	}

}
